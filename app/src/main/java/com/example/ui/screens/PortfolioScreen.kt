package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.AgencyCatalog
import com.example.model.CaseStudy
import com.example.ui.AgencyViewModel
import com.example.ui.ScreenDestination
import com.example.ui.components.SectionBadge
import com.example.ui.components.SectionHeader
import com.example.ui.theme.*

@Composable
fun PortfolioScreen(
    viewModel: AgencyViewModel,
    modifier: Modifier = Modifier
) {
    val isHindi by viewModel.isHindi.collectAsState()
    val selectedCategory by viewModel.selectedPortfolioCategory.collectAsState()

    val categories = listOf("ALL", "Websites & Campaigns", "SEO & Lead Gen", "Campaigns & Automation", "Strategy & Branding")

    val filteredCases = if (selectedCategory == "ALL") {
        AgencyCatalog.caseStudies
    } else {
        AgencyCatalog.caseStudies.filter { it.category.equals(selectedCategory, ignoreCase = true) }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(ObsidianNavy),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 20.dp)
    ) {
        item {
            SectionHeader(
                badge = if (isHindi) "केस स्टडीज" else "Case Studies & Work",
                title = if (isHindi) "समाधान और विकास मॉडल" else "Proven Problem-Solving Frameworks",
                subtitle = "Transparently labeled concept architectures demonstrating how we reverse-engineer growth for diverse sectors."
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Filter Tabs
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
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
                            .clickable { viewModel.setPortfolioCategory(cat) }
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

            Spacer(modifier = Modifier.height(16.dp))
        }

        items(filteredCases) { cs ->
            CaseStudyDetailCard(
                caseStudy = cs,
                onRequestSimilar = {
                    viewModel.updateFormField(
                        serviceInterest = "Similar Solution to: ${cs.title}",
                        projectDescription = "Interested in framework used in ${cs.title}. Target: ${cs.objective}"
                    )
                    viewModel.navigateTo(ScreenDestination.DiscoveryCall)
                }
            )
            Spacer(modifier = Modifier.height(14.dp))
        }
    }
}

@Composable
fun CaseStudyDetailCard(
    caseStudy: CaseStudy,
    onRequestSimilar: () -> Unit
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
                SectionBadge(caseStudy.industry, ElectricBlue)
                Box(
                    modifier = Modifier
                        .background(BorderNavy, RoundedCornerShape(6.dp))
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "Concept Project",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = LightGold
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = caseStudy.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Challenge & Objective:",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = AmberGold
            )
            Text(
                text = caseStudy.objective,
                fontSize = 12.sp,
                color = DarkTextSecondary,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Solution Architecture:",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = ElectricBlue
            )
            Text(
                text = caseStudy.solutionArchitecture,
                fontSize = 12.sp,
                color = DarkTextPrimary,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Deliverables Created:",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = LightGold
            )
            caseStudy.deliverablesProduced.forEach { d ->
                Row(modifier = Modifier.padding(vertical = 2.dp)) {
                    Text("• ", color = AmberGold, fontWeight = FontWeight.Bold)
                    Text(d, fontSize = 12.sp, color = DarkTextSecondary)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Performance Metric Framework:",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = ElectricBlue
            )
            Text(
                text = caseStudy.outcomeFramework,
                fontSize = 12.sp,
                color = DarkTextSecondary,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            Button(
                onClick = onRequestSimilar,
                colors = ButtonDefaults.buttonColors(containerColor = DeepNavy),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, BorderNavy, RoundedCornerShape(8.dp))
            ) {
                Text("Request Similar Growth Architecture", fontSize = 12.sp, color = ElectricBlue)
            }
        }
    }
}
