package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.AgencyCatalog
import com.example.model.ServiceDetail
import com.example.ui.AgencyViewModel
import com.example.ui.ScreenDestination
import com.example.ui.components.SectionBadge
import com.example.ui.components.SectionHeader
import com.example.ui.theme.*

@Composable
fun ServicesScreen(
    viewModel: AgencyViewModel,
    selectedServiceId: String? = null,
    modifier: Modifier = Modifier
) {
    val isHindi by viewModel.isHindi.collectAsState()
    val selectedCategory by viewModel.selectedServiceCategory.collectAsState()
    var activeDetailService by remember {
        mutableStateOf(AgencyCatalog.services.find { it.id == selectedServiceId })
    }

    // Keep active detail service in sync if route passed a serviceId
    LaunchedEffect(selectedServiceId) {
        if (selectedServiceId != null) {
            activeDetailService = AgencyCatalog.services.find { it.id == selectedServiceId }
        }
    }

    val categories = listOf("ALL", "Growth Foundations", "Creative & Identity", "Digital Infrastructure", "Paid Acquisition", "Sales Systems", "AI & Systems")

    val filteredServices = if (selectedCategory == "ALL") {
        AgencyCatalog.services
    } else {
        AgencyCatalog.services.filter { it.category.equals(selectedCategory, ignoreCase = true) }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(ObsidianNavy),
        contentPadding = PaddingValues(bottom = 32.dp)
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            ) {
                SectionHeader(
                    badge = if (isHindi) "सेवा सूची" else "Full Services Catalog",
                    title = if (isHindi) "10 मुख्य विकास समाधान" else "Complete Digital Growth Services",
                    subtitle = if (isHindi) "रणनीति, डिजाइन, विज्ञापन, फनल और एआई ऑटोमेशन" else "Engineered systems for sustainable client acquisition and operational efficiency"
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Category Filter Pills
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(horizontal = 2.dp)
                ) {
                    items(categories) { cat ->
                        val isSelected = selectedCategory == cat
                        Box(
                            modifier = Modifier
                                .border(
                                    1.dp,
                                    if (isSelected) ElectricBlue else BorderNavy,
                                    RoundedCornerShape(20.dp)
                                )
                                .background(
                                    if (isSelected) ElectricBlue.copy(alpha = 0.15f) else MidnightNavy,
                                    RoundedCornerShape(20.dp)
                                )
                                .clickable { viewModel.setServiceCategory(cat) }
                                .padding(horizontal = 14.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = cat,
                                fontSize = 12.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = if (isSelected) ElectricBlue else DarkTextSecondary
                            )
                        }
                    }
                }
            }
        }

        items(filteredServices) { service ->
            Box(modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)) {
                ServiceFullCard(
                    service = service,
                    isHindi = isHindi,
                    onSelectQuote = {
                        viewModel.updateFormField(
                            serviceInterest = service.title,
                            projectDescription = "Interested in ${service.title}. Primary requirement: ${service.whatWeDeliver}"
                        )
                        viewModel.navigateTo(ScreenDestination.DiscoveryCall)
                    },
                    onOpenDetail = {
                        activeDetailService = service
                    }
                )
            }
        }
    }

    // Modal Sheet / Dialog for in-depth service breakdown
    if (activeDetailService != null) {
        ServiceDetailDialog(
            service = activeDetailService!!,
            isHindi = isHindi,
            onDismiss = { activeDetailService = null },
            onRequestProposal = {
                val s = activeDetailService!!
                viewModel.updateFormField(
                    serviceInterest = s.title,
                    projectDescription = "Inquiry regarding ${s.title}: What We Deliver - ${s.whatWeDeliver}"
                )
                activeDetailService = null
                viewModel.navigateTo(ScreenDestination.DiscoveryCall)
            }
        )
    }
}

@Composable
fun ServiceFullCard(
    service: ServiceDetail,
    isHindi: Boolean,
    onSelectQuote: () -> Unit,
    onOpenDetail: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, BorderNavy, RoundedCornerShape(14.dp)),
        colors = CardDefaults.cardColors(containerColor = MidnightNavy),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SectionBadge(service.category, ElectricBlue)
                IconButton(
                    onClick = onOpenDetail,
                    modifier = Modifier.size(28.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "View Details",
                        tint = DarkTextSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = if (isHindi) service.titleHi else service.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = DarkTextPrimary
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = if (isHindi) service.shortSummaryHi else service.shortSummary,
                style = MaterialTheme.typography.bodyMedium,
                color = DarkTextSecondary,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // What We Deliver Preview
            Text(
                text = "Key Deliverables:",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = AmberGold
            )
            Spacer(modifier = Modifier.height(4.dp))
            service.deliverables.take(3).forEach { deliverable ->
                Row(modifier = Modifier.padding(vertical = 2.dp), verticalAlignment = Alignment.Top) {
                    Text("✓ ", color = ElectricBlue, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Text(deliverable, color = DarkTextSecondary, fontSize = 12.sp)
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedButton(
                    onClick = onOpenDetail,
                    shape = RoundedCornerShape(8.dp),
                    border = ButtonDefaults.outlinedButtonBorder.copy(brush = Brush.horizontalGradient(listOf(BorderNavy, ElectricBlue))),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Deep Dive", fontSize = 12.sp, color = DarkTextPrimary)
                }

                Button(
                    onClick = onSelectQuote,
                    colors = ButtonDefaults.buttonColors(containerColor = ElectricBlueVariant),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1.2f)
                ) {
                    Text("Request Quote", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun ServiceDetailDialog(
    service: ServiceDetail,
    isHindi: Boolean,
    onDismiss: () -> Unit,
    onRequestProposal: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = MidnightNavy,
        shape = RoundedCornerShape(16.dp),
        title = {
            Column {
                SectionBadge(service.category, ElectricBlue)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (isHindi) service.titleHi else service.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White
                )
            }
        },
        text = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 420.dp)
            ) {
                item {
                    Text(
                        text = "The Business Problem:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = AmberGold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = if (isHindi) service.customerProblemHi else service.customerProblem,
                        fontSize = 12.sp,
                        color = DarkTextSecondary,
                        lineHeight = 18.sp
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = "What We Deliver:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = AmberGold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = if (isHindi) service.whatWeDeliverHi else service.whatWeDeliver,
                        fontSize = 12.sp,
                        color = DarkTextSecondary,
                        lineHeight = 18.sp
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = "Full Deliverables Scope:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = ElectricBlue
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    service.deliverables.forEach { item ->
                        Row(modifier = Modifier.padding(vertical = 2.dp)) {
                            Text("• ", color = ElectricBlue, fontWeight = FontWeight.Bold)
                            Text(item, fontSize = 12.sp, color = DarkTextPrimary)
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = "Execution Process:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = ElectricBlue
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    service.processSteps.forEach { step ->
                        Column(modifier = Modifier.padding(vertical = 4.dp)) {
                            Text(
                                text = "Step ${step.stepNumber}: ${step.title}",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp,
                                color = LightGold
                            )
                            Text(
                                text = step.description,
                                fontSize = 11.sp,
                                color = DarkTextSecondary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = "Ideal For:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = AmberGold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = service.suitableBusinesses,
                        fontSize = 12.sp,
                        color = DarkTextSecondary
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onRequestProposal,
                colors = ButtonDefaults.buttonColors(containerColor = ElectricBlueVariant),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Request Scope & Quote", fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Close", color = DarkTextSecondary)
            }
        }
    )
}
