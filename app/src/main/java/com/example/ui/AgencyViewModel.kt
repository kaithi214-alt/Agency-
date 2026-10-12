package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.AgencyCatalog
import com.example.data.local.AppDatabase
import com.example.data.local.LeadEntity
import com.example.data.local.LeadRepository
import com.example.model.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

sealed class ScreenDestination {
    object Home : ScreenDestination()
    object Services : ScreenDestination()
    data class ServiceDetailView(val serviceId: String) : ScreenDestination()
    object Industries : ScreenDestination()
    object GrowthAudit : ScreenDestination()
    object Portfolio : ScreenDestination()
    object Packages : ScreenDestination()
    object DiscoveryCall : ScreenDestination()
    object AdminCrm : ScreenDestination()
    object Insights : ScreenDestination()
    object Legal : ScreenDestination()
}

data class LeadFormState(
    val fullName: String = "",
    val businessName: String = "",
    val email: String = "",
    val phone: String = "",
    val serviceInterest: String = "Growth Acceleration (Ads + Funnels)",
    val budgetRange: String = "₹25,000 - ₹50,000 / mo",
    val preferredContact: String = "WhatsApp",
    val projectDescription: String = "",
    val targetDate: String = "",
    val consentAccepted: Boolean = false,
    val isSubmitting: Boolean = false,
    val submitSuccess: Boolean = false,
    val errorMessage: String? = null
)

data class AuditCalculatorState(
    val selectedIndustryId: String = "local_businesses",
    val currentMonthlyLeads: String = "Under 15 leads / month",
    val currentMonthlyAdSpend: String = "None / Under ₹15,000",
    val primaryBottleneck: String = "Inconsistent leads & slow follow-ups",
    val generatedAuditResult: String? = null
)

class AgencyViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)
    private val repository = LeadRepository(db.leadDao())

    // Navigation & UI State
    private val _currentScreen = MutableStateFlow<ScreenDestination>(ScreenDestination.Home)
    val currentScreen: StateFlow<ScreenDestination> = _currentScreen.asStateFlow()

    private val _navigationStack = mutableListOf<ScreenDestination>(ScreenDestination.Home)

    // Language toggle: English (default) vs Hindi
    private val _isHindi = MutableStateFlow(false)
    val isHindi: StateFlow<Boolean> = _isHindi.asStateFlow()

    // Lead Form State
    private val _formState = MutableStateFlow(LeadFormState())
    val formState: StateFlow<LeadFormState> = _formState.asStateFlow()

    // Interactive Growth Audit
    private val _auditState = MutableStateFlow(AuditCalculatorState())
    val auditState: StateFlow<AuditCalculatorState> = _auditState.asStateFlow()

    // Admin CRM
    val allLeads: StateFlow<List<LeadEntity>> = repository.allLeads
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _crmStatusFilter = MutableStateFlow("ALL")
    val crmStatusFilter: StateFlow<String> = _crmStatusFilter.asStateFlow()

    private val _crmSearchQuery = MutableStateFlow("")
    val crmSearchQuery: StateFlow<String> = _crmSearchQuery.asStateFlow()

    private val _isAdminAuthenticated = MutableStateFlow(false)
    val isAdminAuthenticated: StateFlow<Boolean> = _isAdminAuthenticated.asStateFlow()

    private val _adminPinInput = MutableStateFlow("")
    val adminPinInput: StateFlow<String> = _adminPinInput.asStateFlow()

    private val _adminAuthError = MutableStateFlow<String?>(null)
    val adminAuthError: StateFlow<String?> = _adminAuthError.asStateFlow()

    // Configurable Settings
    private val _settings = MutableStateFlow(AgencySettings())
    val settings: StateFlow<AgencySettings> = _settings.asStateFlow()

    // Selected Service Filter in Services View
    private val _selectedServiceCategory = MutableStateFlow("ALL")
    val selectedServiceCategory: StateFlow<String> = _selectedServiceCategory.asStateFlow()

    // Selected Portfolio Category
    private val _selectedPortfolioCategory = MutableStateFlow("ALL")
    val selectedPortfolioCategory: StateFlow<String> = _selectedPortfolioCategory.asStateFlow()

    // Navigation actions
    fun navigateTo(destination: ScreenDestination) {
        if (_currentScreen.value != destination) {
            _navigationStack.add(destination)
            _currentScreen.value = destination
        }
    }

    fun navigateBack(): Boolean {
        if (_navigationStack.size > 1) {
            _navigationStack.removeAt(_navigationStack.lastIndex)
            _currentScreen.value = _navigationStack.last()
            return true
        }
        return false
    }

    fun toggleLanguage() {
        _isHindi.value = !_isHindi.value
    }

    // Lead Form Handlers
    fun updateFormField(
        fullName: String? = null,
        businessName: String? = null,
        email: String? = null,
        phone: String? = null,
        serviceInterest: String? = null,
        budgetRange: String? = null,
        preferredContact: String? = null,
        projectDescription: String? = null,
        targetDate: String? = null,
        consentAccepted: Boolean? = null
    ) {
        _formState.update { current ->
            current.copy(
                fullName = fullName ?: current.fullName,
                businessName = businessName ?: current.businessName,
                email = email ?: current.email,
                phone = phone ?: current.phone,
                serviceInterest = serviceInterest ?: current.serviceInterest,
                budgetRange = budgetRange ?: current.budgetRange,
                preferredContact = preferredContact ?: current.preferredContact,
                projectDescription = projectDescription ?: current.projectDescription,
                targetDate = targetDate ?: current.targetDate,
                consentAccepted = consentAccepted ?: current.consentAccepted,
                errorMessage = null
            )
        }
    }

    fun submitLead(leadType: String = "DISCOVERY_CALL", sourcePage: String = "Website") {
        val current = _formState.value

        // Validation
        if (current.fullName.trim().length < 2) {
            _formState.update { it.copy(errorMessage = "Please enter your full name.") }
            return
        }
        if (current.phone.trim().length < 10) {
            _formState.update { it.copy(errorMessage = "Please enter a valid 10-digit phone number.") }
            return
        }
        if (current.email.isNotBlank() && !android.util.Patterns.EMAIL_ADDRESS.matcher(current.email.trim()).matches()) {
            _formState.update { it.copy(errorMessage = "Please enter a valid email address.") }
            return
        }
        if (!current.consentAccepted) {
            _formState.update { it.copy(errorMessage = "Please agree to receive communications regarding your inquiry.") }
            return
        }

        viewModelScope.launch {
            _formState.update { it.copy(isSubmitting = true, errorMessage = null) }
            try {
                val entity = LeadEntity(
                    leadType = leadType,
                    fullName = current.fullName.trim(),
                    businessName = current.businessName.trim().ifEmpty { "Not specified" },
                    email = current.email.trim().ifEmpty { "Not provided" },
                    phone = current.phone.trim(),
                    serviceInterest = current.serviceInterest,
                    budgetRange = current.budgetRange,
                    projectDescription = current.projectDescription.trim(),
                    preferredContact = current.preferredContact,
                    targetDate = current.targetDate,
                    sourcePage = sourcePage,
                    status = "NEW"
                )
                repository.insertLead(entity)
                _formState.update {
                    it.copy(
                        isSubmitting = false,
                        submitSuccess = true,
                        errorMessage = null
                    )
                }
            } catch (e: Exception) {
                _formState.update {
                    it.copy(
                        isSubmitting = false,
                        errorMessage = "Error saving inquiry: ${e.localizedMessage}. Please try WhatsApp directly."
                    )
                }
            }
        }
    }

    fun resetForm() {
        _formState.value = LeadFormState()
    }

    // Interactive Audit Calculator
    fun updateAudit(
        industryId: String? = null,
        leads: String? = null,
        spend: String? = null,
        bottleneck: String? = null
    ) {
        _auditState.update { current ->
            current.copy(
                selectedIndustryId = industryId ?: current.selectedIndustryId,
                currentMonthlyLeads = leads ?: current.currentMonthlyLeads,
                currentMonthlyAdSpend = spend ?: current.currentMonthlyAdSpend,
                primaryBottleneck = bottleneck ?: current.primaryBottleneck
            )
        }
    }

    fun generateAuditPlan() {
        val state = _auditState.value
        val industry = AgencyCatalog.industries.find { it.id == state.selectedIndustryId }
            ?: AgencyCatalog.industries.first()

        val planSummary = """
            Strategic Growth Architecture for ${industry.name}:
            • Primary Diagnosis: Identified bottleneck is "${state.primaryBottleneck}" with current baseline of ${state.currentMonthlyLeads}.
            • Core Pillar: ${industry.customizedGrowthStrategy}
            • Recommended System: Deploy ${industry.recommendedTechStack.joinToString(" + ")}
            • Expected Focus: ${industry.expectedImpactArea}
            • Projected First 30 Days: Audit conversion leaks, install speed-to-lead WhatsApp trigger, and initiate structured ad testing.
        """.trimIndent()

        _auditState.update { it.copy(generatedAuditResult = planSummary) }

        // Pre-fill form service interest with this tailored audit
        _formState.update {
            it.copy(
                serviceInterest = "Audit Implementation for ${industry.name}",
                projectDescription = "Audit findings: ${state.primaryBottleneck}. Baseline: ${state.currentMonthlyLeads}."
            )
        }
    }

    // Admin CRM Methods
    fun updateAdminPinInput(pin: String) {
        _adminPinInput.value = pin
        _adminAuthError.value = null
    }

    fun verifyAdminPin() {
        if (_adminPinInput.value == _settings.value.adminPin) {
            _isAdminAuthenticated.value = true
            _adminAuthError.value = null
        } else {
            _adminAuthError.value = "Invalid Master Passcode. (Default test code is 2468)"
        }
    }

    fun logoutAdmin() {
        _isAdminAuthenticated.value = false
        _adminPinInput.value = ""
    }

    fun setCrmFilter(status: String) {
        _crmStatusFilter.value = status
    }

    fun setCrmSearch(query: String) {
        _crmSearchQuery.value = query
    }

    fun updateLeadStatus(leadId: Long, newStatus: String) {
        viewModelScope.launch {
            repository.updateStatus(leadId, newStatus)
        }
    }

    fun updateLeadNotes(leadId: Long, notes: String, reminder: String) {
        viewModelScope.launch {
            repository.updateNotes(leadId, notes, reminder)
        }
    }

    fun deleteLead(leadId: Long) {
        viewModelScope.launch {
            repository.deleteLead(leadId)
        }
    }

    // Settings
    fun updateConfiguredWhatsApp(number: String) {
        _settings.update { it.copy(whatsappNumber = number.trim()) }
    }

    fun setServiceCategory(cat: String) {
        _selectedServiceCategory.value = cat
    }

    fun setPortfolioCategory(cat: String) {
        _selectedPortfolioCategory.value = cat
    }
}
