package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.AgencyCatalog
import com.example.ui.AgencyViewModel
import com.example.ui.ScreenDestination
import com.example.ui.components.SectionBadge
import com.example.ui.components.SectionHeader
import com.example.ui.theme.*

@Composable
fun GrowthAuditScreen(
    viewModel: AgencyViewModel,
    modifier: Modifier = Modifier
) {
    val isHindi by viewModel.isHindi.collectAsState()
    val auditState by viewModel.auditState.collectAsState()

    val leadsOptions = listOf(
        "Under 15 leads / month",
        "15 - 50 leads / month",
        "50 - 150 leads / month",
        "150+ leads / month"
    )

    val spendOptions = listOf(
        "None / Under ₹15,000",
        "₹15,000 - ₹50,000",
        "₹50,000 - ₹1,50,000",
        "₹1,50,000+"
    )

    val bottleneckOptions = listOf(
        "Inconsistent leads & dry spells",
        "High ad cost per lead & low quality inquiries",
        "Slow follow-ups (Leads going cold before contact)",
        "Outdated website leaking mobile traffic",
        "Staff overwhelmed by manual repetitive tasks"
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(ObsidianNavy),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 20.dp)
    ) {
        item {
            SectionHeader(
                badge = if (isHindi) "ऑडिट टूल" else "Interactive Growth Audit",
                title = if (isHindi) "2-मिनट डिजिटल ग्रोथ डायग्नोस्टिक" else "2-Minute Growth Diagnostic",
                subtitle = if (isHindi) "अपनी विकास बाधाओं को पहचानें और एक व्यावहारिक खाका प्राप्त करें।" else "Analyze your unit economics, acquisition bottlenecks, and receive an instant strategic architecture."
            )

            Spacer(modifier = Modifier.height(18.dp))
        }

        // Step 1: Industry Selection
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, BorderNavy, RoundedCornerShape(12.dp)),
                colors = CardDefaults.cardColors(containerColor = MidnightNavy)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "1. Select Your Business Industry:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    AgencyCatalog.industries.forEach { ind ->
                        val isSelected = auditState.selectedIndustryId == ind.id
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .border(
                                    1.dp,
                                    if (isSelected) ElectricBlue else BorderNavy.copy(alpha = 0.5f),
                                    RoundedCornerShape(8.dp)
                                )
                                .background(
                                    if (isSelected) ElectricBlue.copy(alpha = 0.12f) else ObsidianNavy,
                                    RoundedCornerShape(8.dp)
                                )
                                .clickable { viewModel.updateAudit(industryId = ind.id) }
                                .padding(horizontal = 12.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = isSelected,
                                onClick = { viewModel.updateAudit(industryId = ind.id) },
                                colors = RadioButtonDefaults.colors(selectedColor = ElectricBlue)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (isHindi) ind.nameHi else ind.name,
                                fontSize = 13.sp,
                                color = if (isSelected) DarkTextPrimary else DarkTextSecondary,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        // Step 2: Current Monthly Volume
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, BorderNavy, RoundedCornerShape(12.dp)),
                colors = CardDefaults.cardColors(containerColor = MidnightNavy)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "2. Current Monthly Lead Inquiries:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    leadsOptions.forEach { opt ->
                        val isSelected = auditState.currentMonthlyLeads == opt
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 3.dp)
                                .clickable { viewModel.updateAudit(leads = opt) }
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = isSelected,
                                onClick = { viewModel.updateAudit(leads = opt) },
                                colors = RadioButtonDefaults.colors(selectedColor = AmberGold)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(opt, fontSize = 13.sp, color = DarkTextPrimary)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        // Step 3: Current Ad Spend
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, BorderNavy, RoundedCornerShape(12.dp)),
                colors = CardDefaults.cardColors(containerColor = MidnightNavy)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "3. Monthly Paid Ad Spend (Meta/Google):",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    spendOptions.forEach { opt ->
                        val isSelected = auditState.currentMonthlyAdSpend == opt
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 3.dp)
                                .clickable { viewModel.updateAudit(spend = opt) }
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = isSelected,
                                onClick = { viewModel.updateAudit(spend = opt) },
                                colors = RadioButtonDefaults.colors(selectedColor = ElectricBlue)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(opt, fontSize = 13.sp, color = DarkTextPrimary)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        // Step 4: Primary Bottleneck
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, BorderNavy, RoundedCornerShape(12.dp)),
                colors = CardDefaults.cardColors(containerColor = MidnightNavy)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "4. Single Biggest Growth Bottleneck:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    bottleneckOptions.forEach { opt ->
                        val isSelected = auditState.primaryBottleneck == opt
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 3.dp)
                                .clickable { viewModel.updateAudit(bottleneck = opt) }
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = isSelected,
                                onClick = { viewModel.updateAudit(bottleneck = opt) },
                                colors = RadioButtonDefaults.colors(selectedColor = AmberGold)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(opt, fontSize = 13.sp, color = DarkTextPrimary)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }

        // Generate Audit Button
        item {
            Button(
                onClick = { viewModel.generateAuditPlan() },
                colors = ButtonDefaults.buttonColors(containerColor = ElectricBlueVariant),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .testTag("btn_run_audit_calc")
            ) {
                Icon(Icons.Default.Bolt, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Generate Growth Architecture Plan",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }

        // Generated Result Display
        item {
            AnimatedVisibility(visible = auditState.generatedAuditResult != null) {
                auditState.generatedAuditResult?.let { result ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.5.dp, ElectricBlue, RoundedCornerShape(14.dp)),
                        colors = CardDefaults.cardColors(containerColor = CardNavy),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Column(modifier = Modifier.padding(18.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                SectionBadge("DIAGNOSTIC REPORT", AmberGold)
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = result,
                                fontSize = 13.sp,
                                color = Color.White,
                                lineHeight = 21.sp
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(
                                onClick = { viewModel.navigateTo(ScreenDestination.DiscoveryCall) },
                                colors = ButtonDefaults.buttonColors(containerColor = AmberGold),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("btn_audit_book_call")
                            ) {
                                Text(
                                    text = "Book Discovery Call with this Audit Pre-Filled",
                                    color = ObsidianNavy,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
