package com.example.ui.screens

import android.content.Context
import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.LeadEntity
import com.example.ui.AgencyViewModel
import com.example.ui.components.SectionBadge
import com.example.ui.components.SectionHeader
import com.example.ui.components.launchPhoneCall
import com.example.ui.components.launchWhatsApp
import com.example.ui.theme.*
import java.text.SimpleDateFormat
import java.util.*

fun exportLeadsToCsv(context: Context, leads: List<LeadEntity>) {
    val sb = StringBuilder()
    sb.append("ID,Type,Full Name,Business,Phone,Email,Service Interest,Budget,Status,Created At,Notes\n")
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    leads.forEach { l ->
        val dateStr = sdf.format(Date(l.createdAt))
        sb.append("${l.id},\"${l.leadType}\",\"${l.fullName}\",\"${l.businessName}\",\"${l.phone}\",\"${l.email}\",\"${l.serviceInterest}\",\"${l.budgetRange}\",\"${l.status}\",\"$dateStr\",\"${l.adminNotes.replace("\"", "\"\"")}\"\n")
    }

    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, sb.toString())
        putExtra(Intent.EXTRA_SUBJECT, "Growth Agency Exported Leads - ${SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())}")
        type = "text/plain"
    }
    context.startActivity(Intent.createChooser(sendIntent, "Export Leads CSV"))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminCrmScreen(
    viewModel: AgencyViewModel,
    modifier: Modifier = Modifier
) {
    val isAuthenticated by viewModel.isAdminAuthenticated.collectAsState()
    val pinInput by viewModel.adminPinInput.collectAsState()
    val authError by viewModel.adminAuthError.collectAsState()
    val allLeads by viewModel.allLeads.collectAsState()
    val statusFilter by viewModel.crmStatusFilter.collectAsState()
    val searchQuery by viewModel.crmSearchQuery.collectAsState()
    val settings by viewModel.settings.collectAsState()
    val context = LocalContext.current

    var activeTab by remember { mutableStateOf("LEADS") } // "LEADS" or "SETTINGS"
    var editableWhatsApp by remember { mutableStateOf(settings.whatsappNumber) }

    val filteredLeads = allLeads.filter { lead ->
        val matchesStatus = if (statusFilter == "ALL") true else lead.status.equals(statusFilter, ignoreCase = true)
        val matchesSearch = if (searchQuery.isBlank()) true else {
            lead.fullName.contains(searchQuery, ignoreCase = true) ||
                    lead.businessName.contains(searchQuery, ignoreCase = true) ||
                    lead.phone.contains(searchQuery)
        }
        matchesStatus && matchesSearch
    }

    val totalCount = allLeads.size
    val newCount = allLeads.count { it.status == "NEW" }
    val qualifiedCount = allLeads.count { it.status == "QUALIFIED" }
    val wonCount = allLeads.count { it.status == "WON" }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(ObsidianNavy),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 20.dp)
    ) {
        if (!isAuthenticated) {
            // Authentication Gate
            item {
                SectionHeader(
                    badge = "SECURITY ACCESS",
                    title = "Admin CRM Portal",
                    subtitle = "Authorized agency managers only. Enter the master access pin to view real-time leads and configurations."
                )

                Spacer(modifier = Modifier.height(20.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, BorderNavy, RoundedCornerShape(14.dp)),
                    colors = CardDefaults.cardColors(containerColor = MidnightNavy)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = "Enter Master PIN (Default: 2468)",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            value = pinInput,
                            onValueChange = { viewModel.updateAdminPinInput(it) },
                            label = { Text("4-Digit PIN", color = DarkTextSecondary) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                            visualTransformation = PasswordVisualTransformation(),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = DarkTextPrimary,
                                unfocusedTextColor = DarkTextPrimary,
                                focusedBorderColor = ElectricBlue,
                                unfocusedBorderColor = BorderNavy
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("input_admin_pin")
                        )

                        authError?.let { err ->
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(err, color = AccentError, fontSize = 12.sp)
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = { viewModel.verifyAdminPin() },
                            colors = ButtonDefaults.buttonColors(containerColor = ElectricBlueVariant),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .testTag("btn_verify_pin")
                        ) {
                            Icon(Icons.Default.LockOpen, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Unlock CRM Dashboard", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        } else {
            // Authenticated Dashboard
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SectionHeader(
                        badge = "INTERNAL CRM",
                        title = "Lead Pipeline",
                        subtitle = "Track inbound inquiries, schedule follow-ups, and manage agency settings.",
                        modifier = Modifier.weight(1f)
                    )

                    IconButton(
                        onClick = { viewModel.logoutAdmin() },
                        modifier = Modifier.testTag("btn_admin_logout")
                    ) {
                        Icon(Icons.Default.Logout, contentDescription = "Logout", tint = AccentError)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // KPI Counter Cards
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    CrmKpiCard("TOTAL", "$totalCount", ElectricBlue, Modifier.weight(1f))
                    CrmKpiCard("NEW", "$newCount", AmberGold, Modifier.weight(1f))
                    CrmKpiCard("QUALIFIED", "$qualifiedCount", Color(0xFF38BDF8), Modifier.weight(1f))
                    CrmKpiCard("WON", "$wonCount", AccentSuccess, Modifier.weight(1f))
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Navigation Tabs between Leads and Settings
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Button(
                        onClick = { activeTab = "LEADS" },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (activeTab == "LEADS") ElectricBlueVariant else MidnightNavy
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Leads Pipeline ($totalCount)", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }

                    Button(
                        onClick = { activeTab = "SETTINGS" },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (activeTab == "SETTINGS") ElectricBlueVariant else MidnightNavy
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Agency Settings", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            if (activeTab == "LEADS") {
                // Filter & Search Controls
                item {
                    Column {
                        OutlinedTextField(
                            value = searchQuery,
                            onValueChange = { viewModel.setCrmSearch(it) },
                            placeholder = { Text("Search by name, company, or phone...", fontSize = 12.sp) },
                            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = DarkTextSecondary) },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = DarkTextPrimary,
                                unfocusedTextColor = DarkTextPrimary,
                                focusedBorderColor = ElectricBlue,
                                unfocusedBorderColor = BorderNavy
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("crm_search_bar")
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        // Status Filter Chips
                        val statuses = listOf("ALL", "NEW", "CONTACTED", "QUALIFIED", "PROPOSAL_SENT", "WON", "LOST")
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            items(statuses) { st ->
                                val isSelected = statusFilter == st
                                Box(
                                    modifier = Modifier
                                        .border(
                                            1.dp,
                                            if (isSelected) ElectricBlue else BorderNavy,
                                            RoundedCornerShape(16.dp)
                                        )
                                        .background(
                                            if (isSelected) ElectricBlue.copy(alpha = 0.2f) else MidnightNavy,
                                            RoundedCornerShape(16.dp)
                                        )
                                        .clickable { viewModel.setCrmFilter(st) }
                                        .padding(horizontal = 10.dp, vertical = 5.dp)
                                ) {
                                    Text(
                                        text = st,
                                        fontSize = 11.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                        color = if (isSelected) ElectricBlue else DarkTextSecondary
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // Export CSV Action
                        Button(
                            onClick = { exportLeadsToCsv(context, filteredLeads) },
                            colors = ButtonDefaults.buttonColors(containerColor = DeepNavy),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, BorderNavy, RoundedCornerShape(8.dp))
                                .testTag("btn_export_csv")
                        ) {
                            Icon(Icons.Default.Download, contentDescription = null, tint = AmberGold, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Export Filtered Leads (${filteredLeads.size}) to CSV", color = AmberGold, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

                if (filteredLeads.isEmpty()) {
                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, BorderNavy, RoundedCornerShape(12.dp)),
                            colors = CardDefaults.cardColors(containerColor = MidnightNavy)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(32.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(Icons.Default.Inbox, contentDescription = null, tint = DarkTextMuted, modifier = Modifier.size(40.dp))
                                Spacer(modifier = Modifier.height(8.dp))
                                Text("No inquiries matching this filter.", color = DarkTextSecondary, fontSize = 13.sp)
                            }
                        }
                    }
                } else {
                    items(filteredLeads, key = { it.id }) { lead ->
                        LeadItemCard(
                            lead = lead,
                            onUpdateStatus = { newSt -> viewModel.updateLeadStatus(lead.id, newSt) },
                            onSaveNotes = { notes, reminder -> viewModel.updateLeadNotes(lead.id, notes, reminder) },
                            onDelete = { viewModel.deleteLead(lead.id) }
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            } else {
                // SETTINGS TAB
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, BorderNavy, RoundedCornerShape(14.dp)),
                        colors = CardDefaults.cardColors(containerColor = MidnightNavy)
                    ) {
                        Column(modifier = Modifier.padding(18.dp)) {
                            Text(
                                text = "Configurable Agency Contacts",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = Color.White
                            )

                            Spacer(modifier = Modifier.height(14.dp))

                            Text("WhatsApp Business Number (E.164 format):", fontSize = 12.sp, color = AmberGold, fontWeight = FontWeight.SemiBold)
                            Spacer(modifier = Modifier.height(4.dp))
                            OutlinedTextField(
                                value = editableWhatsApp,
                                onValueChange = { editableWhatsApp = it },
                                placeholder = { Text("+919876543210") },
                                singleLine = true,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedTextColor = DarkTextPrimary,
                                    unfocusedTextColor = DarkTextPrimary,
                                    focusedBorderColor = ElectricBlue,
                                    unfocusedBorderColor = BorderNavy
                                ),
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Button(
                                onClick = { viewModel.updateConfiguredWhatsApp(editableWhatsApp) },
                                colors = ButtonDefaults.buttonColors(containerColor = ElectricBlueVariant),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Save WhatsApp Number", fontWeight = FontWeight.Bold)
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Text("Current Agency Profile:", fontSize = 12.sp, color = AmberGold, fontWeight = FontWeight.SemiBold)
                            Text("Business: ${settings.businessName}", fontSize = 11.sp, color = DarkTextSecondary)
                            Text("Email: ${settings.contactEmail}", fontSize = 11.sp, color = DarkTextSecondary)
                            Text("HQ: ${settings.officeLocation}", fontSize = 11.sp, color = DarkTextSecondary)
                            Text("Master Admin PIN: ${settings.adminPin}", fontSize = 11.sp, color = DarkTextSecondary)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CrmKpiCard(label: String, value: String, color: Color, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.border(1.dp, BorderNavy, RoundedCornerShape(10.dp)),
        colors = CardDefaults.cardColors(containerColor = MidnightNavy),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(value, fontSize = 18.sp, fontWeight = FontWeight.ExtraBold, color = color)
            Spacer(modifier = Modifier.height(2.dp))
            Text(label, fontSize = 9.sp, fontWeight = FontWeight.Bold, color = DarkTextSecondary)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeadItemCard(
    lead: LeadEntity,
    onUpdateStatus: (String) -> Unit,
    onSaveNotes: (String, String) -> Unit,
    onDelete: () -> Unit
) {
    val context = LocalContext.current
    var expandedStatus by remember { mutableStateOf(false) }
    var notesText by remember { mutableStateOf(lead.adminNotes) }
    var reminderText by remember { mutableStateOf(lead.followUpReminder) }
    var showNotesEditor by remember { mutableStateOf(false) }

    val statusColor = when (lead.status) {
        "NEW" -> AmberGold
        "CONTACTED" -> ElectricBlue
        "QUALIFIED" -> Color(0xFF60A5FA)
        "PROPOSAL_SENT" -> Color(0xFFC084FC)
        "WON" -> AccentSuccess
        "LOST" -> AccentError
        else -> DarkTextSecondary
    }

    val sdf = SimpleDateFormat("dd MMM, hh:mm a", Locale.getDefault())
    val formattedDate = sdf.format(Date(lead.createdAt))

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, BorderNavy, RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = MidnightNavy),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header Row: Name, Business, and Status Pill
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = lead.fullName,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "${lead.businessName} • $formattedDate",
                        style = MaterialTheme.typography.bodySmall,
                        color = DarkTextSecondary
                    )
                }

                // Status Badge with Dropdown
                Box {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(statusColor.copy(alpha = 0.2f))
                            .border(1.dp, statusColor, RoundedCornerShape(6.dp))
                            .clickable { expandedStatus = true }
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(lead.status, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = statusColor)
                            Icon(Icons.Default.ArrowDropDown, contentDescription = null, tint = statusColor, modifier = Modifier.size(16.dp))
                        }
                    }

                    DropdownMenu(
                        expanded = expandedStatus,
                        onDismissRequest = { expandedStatus = false },
                        modifier = Modifier.background(MidnightNavy)
                    ) {
                        listOf("NEW", "CONTACTED", "QUALIFIED", "PROPOSAL_SENT", "WON", "LOST").forEach { st ->
                            DropdownMenuItem(
                                text = { Text(st, color = Color.White, fontSize = 12.sp) },
                                onClick = {
                                    onUpdateStatus(st)
                                    expandedStatus = false
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Details
            Text("Service: ${lead.serviceInterest}", fontSize = 12.sp, color = ElectricBlue, fontWeight = FontWeight.SemiBold)
            Text("Budget: ${lead.budgetRange} • Pref: ${lead.preferredContact}", fontSize = 11.sp, color = DarkTextSecondary)

            if (lead.projectDescription.isNotBlank()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text("Notes: \"${lead.projectDescription}\"", fontSize = 11.sp, color = LightGold)
            }

            if (lead.adminNotes.isNotBlank()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text("Internal Admin Note: ${lead.adminNotes}", fontSize = 11.sp, color = AmberGold)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Action Buttons: Call, WhatsApp, Notes, Delete
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // WhatsApp Button
                Button(
                    onClick = {
                        launchWhatsApp(
                            context = context,
                            phoneNumber = lead.phone,
                            message = "Hello ${lead.fullName}, this is from the AI Growth Agency following up on your inquiry for ${lead.serviceInterest}."
                        )
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF25D366)),
                    shape = RoundedCornerShape(6.dp),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.Chat, contentDescription = null, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("WhatsApp", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }

                // Phone Call Button
                OutlinedButton(
                    onClick = { launchPhoneCall(context, lead.phone) },
                    shape = RoundedCornerShape(6.dp),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.Phone, contentDescription = null, tint = ElectricBlue, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Call", fontSize = 11.sp, color = DarkTextPrimary)
                }

                // Toggle Notes
                OutlinedButton(
                    onClick = { showNotesEditor = !showNotesEditor },
                    shape = RoundedCornerShape(6.dp),
                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.EditNote, contentDescription = null, tint = AmberGold, modifier = Modifier.size(14.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Note", fontSize = 11.sp, color = DarkTextPrimary)
                }

                // Delete Button
                IconButton(
                    onClick = onDelete,
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(Icons.Default.DeleteOutline, contentDescription = "Delete", tint = AccentError.copy(alpha = 0.7f), modifier = Modifier.size(18.dp))
                }
            }

            // Expandable Notes Editor
            AnimatedVisibility(visible = showNotesEditor) {
                Column(modifier = Modifier.padding(top = 10.dp)) {
                    OutlinedTextField(
                        value = notesText,
                        onValueChange = { notesText = it },
                        label = { Text("Internal CRM Notes", fontSize = 11.sp) },
                        placeholder = { Text("e.g. Rahul spoke on call; proposal due Tuesday") },
                        minLines = 2,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = DarkTextPrimary,
                            unfocusedTextColor = DarkTextPrimary,
                            focusedBorderColor = ElectricBlue,
                            unfocusedBorderColor = BorderNavy
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Button(
                        onClick = {
                            onSaveNotes(notesText, reminderText)
                            showNotesEditor = false
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = ElectricBlueVariant),
                        shape = RoundedCornerShape(6.dp),
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text("Save Note", fontSize = 11.sp)
                    }
                }
            }
        }
    }
}
