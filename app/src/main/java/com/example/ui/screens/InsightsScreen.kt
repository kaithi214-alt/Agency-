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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.AgencyCatalog
import com.example.model.InsightPost
import com.example.ui.AgencyViewModel
import com.example.ui.ScreenDestination
import com.example.ui.components.SectionBadge
import com.example.ui.components.SectionHeader
import com.example.ui.theme.*

@Composable
fun InsightsScreen(
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
                badge = if (isHindi) "ज्ञान केंद्र" else "Knowledge & Insights",
                title = if (isHindi) "डिजिटल विकास अंतर्दृष्टि" else "Growth Insights & Playbooks",
                subtitle = "Original tactical breakdowns on speed-to-lead, Google Maps dominance, and ad spend allocation."
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        items(AgencyCatalog.insights) { post ->
            InsightPostCard(
                post = post,
                onApply = {
                    viewModel.updateFormField(
                        serviceInterest = "Strategy Implementation from: ${post.title}",
                        projectDescription = "Read insight: ${post.title}. Looking to implement this system."
                    )
                    viewModel.navigateTo(ScreenDestination.DiscoveryCall)
                }
            )
            Spacer(modifier = Modifier.height(14.dp))
        }
    }
}

@Composable
fun InsightPostCard(
    post: InsightPost,
    onApply: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, BorderNavy, RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = MidnightNavy),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SectionBadge(post.category, ElectricBlue)
                Text(
                    text = "${post.date} • ${post.readTime}",
                    fontSize = 11.sp,
                    color = DarkTextMuted
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = post.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = post.summary,
                style = MaterialTheme.typography.bodyMedium,
                color = LightGold,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = post.fullContent,
                style = MaterialTheme.typography.bodySmall,
                color = DarkTextSecondary,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            Button(
                onClick = onApply,
                colors = ButtonDefaults.buttonColors(containerColor = DeepNavy),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, BorderNavy, RoundedCornerShape(8.dp))
            ) {
                Text("Implement This System for Your Business", fontSize = 12.sp, color = ElectricBlue)
            }
        }
    }
}
