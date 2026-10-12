package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.AgencyCatalog
import com.example.model.IndustryVertical
import com.example.ui.AgencyViewModel
import com.example.ui.ScreenDestination
import com.example.ui.components.SectionBadge
import com.example.ui.components.SectionHeader
import com.example.ui.theme.*

@Composable
fun IndustriesScreen(
    viewModel: AgencyViewModel,
    modifier: Modifier = Modifier
) {
    val isHindi by viewModel.isHindi.collectAsState()

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
                    badge = if (isHindi) "लक्षित उद्योग" else "Target Verticals",
                    title = if (isHindi) "उद्योग-विशिष्ट विकास प्रणाली" else "Industry-Tailored Growth Systems",
                    subtitle = if (isHindi) "हर व्यवसाय की गतिशीलता अलग होती है; हम आपके मॉडल के अनुसार काम करते हैं।" else "We do not use generic cookie-cutter marketing. Every strategy is tailored to your unit economics and buyer journey."
                )
            }
        }

        items(AgencyCatalog.industries) { ind ->
            Box(modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)) {
                IndustryFullCard(
                    industry = ind,
                    isHindi = isHindi,
                    onConsult = {
                        viewModel.updateFormField(
                            serviceInterest = "${ind.name} Growth Strategy",
                            projectDescription = "Industry: ${ind.name}. Focus: ${ind.expectedImpactArea}"
                        )
                        viewModel.navigateTo(ScreenDestination.DiscoveryCall)
                    }
                )
            }
        }
    }
}

@Composable
fun IndustryFullCard(
    industry: IndustryVertical,
    isHindi: Boolean,
    onConsult: () -> Unit
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
                Text(
                    text = if (isHindi) industry.nameHi else industry.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                SectionBadge("TAILORED", AmberGold)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Key Industry Challenges:",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = ElectricBlue
            )
            Spacer(modifier = Modifier.height(4.dp))
            industry.primaryChallenges.forEach { challenge ->
                Row(modifier = Modifier.padding(vertical = 2.dp), verticalAlignment = Alignment.Top) {
                    Text("• ", color = AmberGold, fontWeight = FontWeight.Bold)
                    Text(challenge, fontSize = 12.sp, color = DarkTextSecondary, lineHeight = 18.sp)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Our Tailored Growth Architecture:",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = LightGold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = industry.customizedGrowthStrategy,
                fontSize = 12.sp,
                color = DarkTextPrimary,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Recommended Stack: ${industry.recommendedTechStack.joinToString(" • ")}",
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = ElectricBlue
            )

            Spacer(modifier = Modifier.height(14.dp))

            Button(
                onClick = onConsult,
                colors = ButtonDefaults.buttonColors(containerColor = DeepNavy),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, BorderNavy, RoundedCornerShape(8.dp))
            ) {
                Text(
                    text = "Request ${industry.name} Growth Consultation",
                    fontSize = 12.sp,
                    color = ElectricBlue,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
