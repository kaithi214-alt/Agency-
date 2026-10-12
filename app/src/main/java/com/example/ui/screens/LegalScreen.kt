package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.ui.AgencyViewModel
import com.example.ui.components.SectionBadge
import com.example.ui.components.SectionHeader
import com.example.ui.theme.*

@Composable
fun LegalScreen(
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
                badge = "LEGAL & COMPLIANCE",
                title = "Privacy Policy & Terms",
                subtitle = "Transparency, data handling practices, and service agreements."
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, BorderNavy, RoundedCornerShape(12.dp)),
                colors = CardDefaults.cardColors(containerColor = MidnightNavy)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    SectionBadge("PRIVACY POLICY", ElectricBlue)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "1. Information Collection & Storage",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "We collect inquiry data (including name, company name, phone/WhatsApp number, email, and stated marketing requirements) solely to respond to consultations and provide growth proposals. Data is stored securely and never sold, rented, or distributed to third-party telemarketers.",
                        fontSize = 12.sp,
                        color = DarkTextSecondary,
                        lineHeight = 18.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "2. Communication Consent",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "By submitting an inquiry or contacting us via WhatsApp, you consent to receive direct business communication from our team regarding your growth strategy. You may opt out at any time by requesting discontinuation on WhatsApp or email.",
                        fontSize = 12.sp,
                        color = DarkTextSecondary,
                        lineHeight = 18.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "3. Platform Advertising Spend",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "All platform ad spend on Meta (Facebook/Instagram), Google Ads, and YouTube is paid directly by the client to the advertising platforms using their own verified billing methods. Agency retainers cover strategy, creative production, tracking setup, and management.",
                        fontSize = 12.sp,
                        color = DarkTextSecondary,
                        lineHeight = 18.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "4. Ethical Performance Disclaimer",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = AmberGold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "While we employ battle-tested growth frameworks and rigorous conversion optimization, we do not guarantee specific monetary sales figures or search engine rankings, as final conversions depend on sales closing, market demand, and business fulfillment. Guarantees of #1 rankings violate official search platform guidelines.",
                        fontSize = 12.sp,
                        color = DarkTextSecondary,
                        lineHeight = 18.sp
                    )
                }
            }
        }
    }
}
