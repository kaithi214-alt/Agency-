package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.model.PackagePlan
import com.example.ui.AgencyViewModel
import com.example.ui.ScreenDestination
import com.example.ui.components.SectionBadge
import com.example.ui.components.SectionHeader
import com.example.ui.theme.*

@Composable
fun PackagesScreen(
    viewModel: AgencyViewModel,
    modifier: Modifier = Modifier
) {
    val isHindi by viewModel.isHindi.collectAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(ObsidianNavy),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 20.dp)
    ) {
        item {
            SectionHeader(
                badge = if (isHindi) "पैकेज और मूल्य" else "Pricing & Packages",
                title = if (isHindi) "पारदर्शी साझेदारी मॉडल" else "Engagement Models",
                subtitle = "No hidden surcharges. All ad spend is paid directly to advertising platforms for 100% financial clarity."
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Policy Notice
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, BorderNavy, RoundedCornerShape(10.dp)),
                colors = CardDefaults.cardColors(containerColor = MidnightNavy)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "Important Pricing & Scope Transparency:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = AmberGold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "• Platform ad spend (Meta & Google) is separate and paid directly from your credit card.\n• We do not make arbitrary ranking or sales guarantees, but maintain rigorous weekly optimization.\n• Custom project scopes available upon discovery call review.",
                        fontSize = 11.sp,
                        color = DarkTextSecondary,
                        lineHeight = 18.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        items(AgencyCatalog.packages) { pkg ->
            PackageDetailedCard(
                pkg = pkg,
                onSelect = {
                    viewModel.updateFormField(
                        serviceInterest = "${pkg.name} Package",
                        budgetRange = pkg.pricingMonthly
                    )
                    viewModel.navigateTo(ScreenDestination.DiscoveryCall)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun PackageDetailedCard(
    pkg: PackagePlan,
    onSelect: () -> Unit
) {
    val isHighlighted = pkg.id == "growth"

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                if (isHighlighted) 1.5.dp else 1.dp,
                if (isHighlighted) ElectricBlue else BorderNavy,
                RoundedCornerShape(14.dp)
            ),
        colors = CardDefaults.cardColors(containerColor = if (isHighlighted) CardNavy else MidnightNavy),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = pkg.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                SectionBadge(
                    text = pkg.tierBadge,
                    color = if (isHighlighted) ElectricBlue else AmberGold
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = pkg.pricingMonthly,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.ExtraBold,
                color = AmberGold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = pkg.tagline,
                style = MaterialTheme.typography.bodyMedium,
                color = DarkTextSecondary
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "What is Included:",
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = ElectricBlue
            )
            Spacer(modifier = Modifier.height(4.dp))
            pkg.featuresIncluded.forEach { feat ->
                Row(modifier = Modifier.padding(vertical = 2.dp), verticalAlignment = Alignment.Top) {
                    Text("✓ ", color = ElectricBlue, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    Text(feat, fontSize = 12.sp, color = DarkTextPrimary, lineHeight = 18.sp)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Scope Exclusions:",
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = DarkTextMuted
            )
            Spacer(modifier = Modifier.height(2.dp))
            pkg.exclusions.forEach { excl ->
                Row(modifier = Modifier.padding(vertical = 1.dp), verticalAlignment = Alignment.Top) {
                    Text("✕ ", color = DarkTextMuted, fontSize = 11.sp)
                    Text(excl, fontSize = 11.sp, color = DarkTextSecondary)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Best For: ${pkg.recommendedFor}",
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = LightGold
            )
            Text(
                text = "Timeline: ${pkg.timeline} • Revisions: ${pkg.revisionsPolicy}",
                fontSize = 10.sp,
                color = DarkTextMuted
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onSelect,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isHighlighted) ElectricBlueVariant else DeepNavy
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, if (isHighlighted) ElectricBlue else BorderNavy, RoundedCornerShape(8.dp))
            ) {
                Text(
                    text = "Request Custom Quote for ${pkg.name}",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isHighlighted) Color.White else ElectricBlue
                )
            }
        }
    }
}
