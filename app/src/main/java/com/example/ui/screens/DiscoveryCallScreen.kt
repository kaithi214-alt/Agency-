package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.AgencyViewModel
import com.example.ui.ScreenDestination
import com.example.ui.components.SectionBadge
import com.example.ui.components.SectionHeader
import com.example.ui.components.launchWhatsApp
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoveryCallScreen(
    viewModel: AgencyViewModel,
    modifier: Modifier = Modifier
) {
    val isHindi by viewModel.isHindi.collectAsState()
    val formState by viewModel.formState.collectAsState()
    val settings by viewModel.settings.collectAsState()
    val context = LocalContext.current

    val serviceOptions = listOf(
        "Growth Acceleration (Ads + Funnels)",
        "Strategy & Growth Planning",
        "High-Converting Websites & Funnels",
        "SEO & Google Maps Local 3-Pack",
        "Social Media & Content Engine",
        "AI Chatbot & Workflow Automation",
        "Starter Growth Package",
        "Full Growth Partner Retainer"
    )

    val budgetOptions = listOf(
        "Under ₹25,000 / mo",
        "₹25,000 - ₹50,000 / mo",
        "₹50,000 - ₹1,00,000 / mo",
        "₹1,00,000+ / mo",
        "One-Time Project Quotation"
    )

    val contactMethods = listOf("WhatsApp", "Phone Call", "Email")

    var expandedServiceDropdown by remember { mutableStateOf(false) }
    var expandedBudgetDropdown by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(ObsidianNavy),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 20.dp)
    ) {
        item {
            SectionHeader(
                badge = if (isHindi) "डिस्कवरी कॉल" else "Book a Consultation",
                title = if (isHindi) "रणनीतिक डिस्कवरी सत्र बुक करें" else "Book a Free Discovery Call",
                subtitle = if (isHindi) "हम आपकी वर्तमान अधिग्रहण प्रणाली की समीक्षा करेंगे और ठोस कदम सुझाएंगे।" else "A candid 30-minute review of your marketing funnels, unit economics, and AI automation opportunities."
            )

            Spacer(modifier = Modifier.height(18.dp))
        }

        if (formState.submitSuccess) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.5.dp, AccentSuccess, RoundedCornerShape(14.dp)),
                    colors = CardDefaults.cardColors(containerColor = CardNavy),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = AccentSuccess,
                            modifier = Modifier.size(54.dp)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Inquiry Successfully Saved!",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Thank you, ${formState.fullName}. Your request has been securely persisted in our system. A Senior Growth Strategist will connect via ${formState.preferredContact} within 4 hours during business days.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = DarkTextSecondary,
                            lineHeight = 22.sp
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        // WhatsApp Fast Connect Option
                        Button(
                            onClick = {
                                launchWhatsApp(
                                    context = context,
                                    phoneNumber = settings.whatsappNumber,
                                    message = "Hello! I just submitted an inquiry for ${formState.serviceInterest} under business name '${formState.businessName}'. Looking forward to speaking!"
                                )
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF25D366)),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(Icons.Default.Chat, contentDescription = null, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Fast-Track on WhatsApp Now", fontWeight = FontWeight.Bold)
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedButton(
                            onClick = {
                                viewModel.resetForm()
                                viewModel.navigateTo(ScreenDestination.Home)
                            },
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Return to Home", color = DarkTextPrimary)
                        }
                    }
                }
            }
        } else {
            // Form Fields
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, BorderNavy, RoundedCornerShape(14.dp)),
                    colors = CardDefaults.cardColors(containerColor = MidnightNavy),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        // Error banner if any
                        formState.errorMessage?.let { err ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 14.dp),
                                colors = CardDefaults.cardColors(containerColor = AccentError.copy(alpha = 0.15f)),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Row(
                                    modifier = Modifier.padding(10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(Icons.Default.Error, contentDescription = null, tint = AccentError, modifier = Modifier.size(18.dp))
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(err, color = AccentError, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                                }
                            }
                        }

                        // Full Name
                        OutlinedTextField(
                            value = formState.fullName,
                            onValueChange = { viewModel.updateFormField(fullName = it) },
                            label = { Text("Your Full Name *", color = DarkTextSecondary) },
                            placeholder = { Text("e.g. Rahul Sharma") },
                            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = ElectricBlue) },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = DarkTextPrimary,
                                unfocusedTextColor = DarkTextPrimary,
                                focusedBorderColor = ElectricBlue,
                                unfocusedBorderColor = BorderNavy
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("input_full_name")
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Business Name
                        OutlinedTextField(
                            value = formState.businessName,
                            onValueChange = { viewModel.updateFormField(businessName = it) },
                            label = { Text("Company / Business Name", color = DarkTextSecondary) },
                            placeholder = { Text("e.g. Apex Learning Academy") },
                            leadingIcon = { Icon(Icons.Default.Business, contentDescription = null, tint = ElectricBlue) },
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = DarkTextPrimary,
                                unfocusedTextColor = DarkTextPrimary,
                                focusedBorderColor = ElectricBlue,
                                unfocusedBorderColor = BorderNavy
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("input_business_name")
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Phone Number
                        OutlinedTextField(
                            value = formState.phone,
                            onValueChange = { viewModel.updateFormField(phone = it) },
                            label = { Text("Phone / WhatsApp Number *", color = DarkTextSecondary) },
                            placeholder = { Text("e.g. 9876543210") },
                            leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null, tint = ElectricBlue) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = DarkTextPrimary,
                                unfocusedTextColor = DarkTextPrimary,
                                focusedBorderColor = ElectricBlue,
                                unfocusedBorderColor = BorderNavy
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("input_phone")
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Work Email
                        OutlinedTextField(
                            value = formState.email,
                            onValueChange = { viewModel.updateFormField(email = it) },
                            label = { Text("Work Email (Optional)", color = DarkTextSecondary) },
                            placeholder = { Text("e.g. rahul@company.com") },
                            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = ElectricBlue) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = DarkTextPrimary,
                                unfocusedTextColor = DarkTextPrimary,
                                focusedBorderColor = ElectricBlue,
                                unfocusedBorderColor = BorderNavy
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("input_email")
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        // Service Interest
                        Text("Service of Interest:", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = LightGold)
                        Spacer(modifier = Modifier.height(4.dp))
                        ExposedDropdownMenuBox(
                            expanded = expandedServiceDropdown,
                            onExpandedChange = { expandedServiceDropdown = !expandedServiceDropdown }
                        ) {
                            OutlinedTextField(
                                value = formState.serviceInterest,
                                onValueChange = {},
                                readOnly = true,
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedServiceDropdown) },
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedTextColor = DarkTextPrimary,
                                    unfocusedTextColor = DarkTextPrimary,
                                    focusedBorderColor = ElectricBlue,
                                    unfocusedBorderColor = BorderNavy
                                ),
                                modifier = Modifier
                                    .menuAnchor()
                                    .fillMaxWidth()
                            )
                            ExposedDropdownMenu(
                                expanded = expandedServiceDropdown,
                                onDismissRequest = { expandedServiceDropdown = false },
                                modifier = Modifier.background(MidnightNavy)
                            ) {
                                serviceOptions.forEach { opt ->
                                    DropdownMenuItem(
                                        text = { Text(opt, color = DarkTextPrimary, fontSize = 13.sp) },
                                        onClick = {
                                            viewModel.updateFormField(serviceInterest = opt)
                                            expandedServiceDropdown = false
                                        }
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // Budget Range
                        Text("Approximate Monthly Marketing Budget:", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = LightGold)
                        Spacer(modifier = Modifier.height(4.dp))
                        ExposedDropdownMenuBox(
                            expanded = expandedBudgetDropdown,
                            onExpandedChange = { expandedBudgetDropdown = !expandedBudgetDropdown }
                        ) {
                            OutlinedTextField(
                                value = formState.budgetRange,
                                onValueChange = {},
                                readOnly = true,
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedBudgetDropdown) },
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedTextColor = DarkTextPrimary,
                                    unfocusedTextColor = DarkTextPrimary,
                                    focusedBorderColor = ElectricBlue,
                                    unfocusedBorderColor = BorderNavy
                                ),
                                modifier = Modifier
                                    .menuAnchor()
                                    .fillMaxWidth()
                            )
                            ExposedDropdownMenu(
                                expanded = expandedBudgetDropdown,
                                onDismissRequest = { expandedBudgetDropdown = false },
                                modifier = Modifier.background(MidnightNavy)
                            ) {
                                budgetOptions.forEach { opt ->
                                    DropdownMenuItem(
                                        text = { Text(opt, color = DarkTextPrimary, fontSize = 13.sp) },
                                        onClick = {
                                            viewModel.updateFormField(budgetRange = opt)
                                            expandedBudgetDropdown = false
                                        }
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // Preferred Contact Method
                        Text("Preferred Channel for Follow-Up:", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = LightGold)
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            contactMethods.forEach { method ->
                                val isSelected = formState.preferredContact == method
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .border(
                                            1.dp,
                                            if (isSelected) ElectricBlue else BorderNavy,
                                            RoundedCornerShape(8.dp)
                                        )
                                        .background(
                                            if (isSelected) ElectricBlue.copy(alpha = 0.15f) else ObsidianNavy,
                                            RoundedCornerShape(8.dp)
                                        )
                                        .clickable { viewModel.updateFormField(preferredContact = method) }
                                        .padding(vertical = 10.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = method,
                                        fontSize = 12.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                        color = if (isSelected) ElectricBlue else DarkTextSecondary
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // Project Description
                        OutlinedTextField(
                            value = formState.projectDescription,
                            onValueChange = { viewModel.updateFormField(projectDescription = it) },
                            label = { Text("Current Challenges or Goals", color = DarkTextSecondary) },
                            placeholder = { Text("Briefly describe what you would like to achieve or fix...") },
                            minLines = 3,
                            maxLines = 5,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = DarkTextPrimary,
                                unfocusedTextColor = DarkTextPrimary,
                                focusedBorderColor = ElectricBlue,
                                unfocusedBorderColor = BorderNavy
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("input_project_desc")
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        // Consent Checkbox
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { viewModel.updateFormField(consentAccepted = !formState.consentAccepted) }
                                .padding(vertical = 4.dp)
                        ) {
                            Checkbox(
                                checked = formState.consentAccepted,
                                onCheckedChange = { viewModel.updateFormField(consentAccepted = it) },
                                colors = CheckboxDefaults.colors(checkedColor = ElectricBlue)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "I agree to be contacted regarding this growth inquiry. We respect privacy and never send spam.",
                                fontSize = 11.sp,
                                color = DarkTextSecondary,
                                lineHeight = 16.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(18.dp))

                        // Submit Button
                        Button(
                            onClick = { viewModel.submitLead(leadType = "DISCOVERY_CALL", sourcePage = "Discovery Call Form") },
                            enabled = !formState.isSubmitting,
                            colors = ButtonDefaults.buttonColors(containerColor = ElectricBlueVariant),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .testTag("btn_submit_lead")
                        ) {
                            if (formState.isSubmitting) {
                                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(20.dp))
                            } else {
                                Icon(Icons.Default.Send, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Submit & Request Discovery Call", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}
