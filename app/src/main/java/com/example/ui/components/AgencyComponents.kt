package com.example.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.ScreenDestination
import com.example.ui.theme.*
import java.net.URLEncoder

fun launchWhatsApp(context: Context, phoneNumber: String, message: String) {
    try {
        val cleanNumber = phoneNumber.replace("+", "").replace(" ", "").replace("-", "")
        val encodedMsg = URLEncoder.encode(message, "UTF-8")
        val url = "https://wa.me/$cleanNumber?text=$encodedMsg"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(context, "Could not open WhatsApp. Please check if installed.", Toast.LENGTH_SHORT).show()
    }
}

fun launchEmail(context: Context, email: String, subject: String) {
    try {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$email?subject=${Uri.encode(subject)}")
        }
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(context, "Could not open Email app.", Toast.LENGTH_SHORT).show()
    }
}

fun launchPhoneCall(context: Context, phone: String) {
    try {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(context, "Could not open Phone dialer.", Toast.LENGTH_SHORT).show()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgencyTopBar(
    isHindi: Boolean,
    onToggleLanguage: () -> Unit,
    onNavigate: (ScreenDestination) -> Unit,
    onOpenAdmin: () -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = ObsidianNavy,
            titleContentColor = DarkTextPrimary,
            actionIconContentColor = DarkTextPrimary
        ),
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable { onNavigate(ScreenDestination.Home) }
                    .padding(vertical = 4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            Brush.linearGradient(
                                listOf(ElectricBlueVariant, ElectricBlue)
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.TrendingUp,
                        contentDescription = "Agency Logo",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "GROWTH AGENCY",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 1.sp,
                        color = Color.White
                    )
                    Text(
                        text = if (isHindi) "एआई-संचालित विकास प्रणाली" else "AI-POWERED SYSTEMS",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = ElectricBlue,
                        letterSpacing = 0.5.sp
                    )
                }
            }
        },
        actions = {
            // Language Toggle
            OutlinedButton(
                onClick = onToggleLanguage,
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                shape = RoundedCornerShape(16.dp),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    brush = Brush.horizontalGradient(listOf(BorderNavy, ElectricBlue))
                ),
                modifier = Modifier
                    .height(32.dp)
                    .testTag("btn_lang_toggle")
            ) {
                Text(
                    text = if (isHindi) "EN" else "हिंदी",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = AmberGold
                )
            }

            Spacer(modifier = Modifier.width(6.dp))

            // Admin CRM Icon
            IconButton(
                onClick = onOpenAdmin,
                modifier = Modifier.testTag("btn_admin_crm")
            ) {
                Icon(
                    imageVector = Icons.Default.AdminPanelSettings,
                    contentDescription = "Admin CRM Portal",
                    tint = DarkTextSecondary
                )
            }
        }
    )
}

@Composable
fun SectionBadge(text: String, color: Color = ElectricBlue) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(color.copy(alpha = 0.12f))
            .border(1.dp, color.copy(alpha = 0.4f), RoundedCornerShape(20.dp))
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Text(
            text = text.uppercase(),
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = color,
            letterSpacing = 1.sp
        )
    }
}

@Composable
fun SectionHeader(
    badge: String,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        SectionBadge(badge)
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = DarkTextPrimary,
            lineHeight = 32.sp
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium,
            color = DarkTextSecondary,
            lineHeight = 22.sp
        )
    }
}

@Composable
fun WhatsAppFloatButton(
    phoneNumber: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    FloatingActionButton(
        onClick = {
            launchWhatsApp(
                context = context,
                phoneNumber = phoneNumber,
                message = "Hello! I am interested in accelerating my digital growth with your agency. I would like to explore options."
            )
        },
        containerColor = Color(0xFF25D366),
        contentColor = Color.White,
        shape = CircleShape,
        modifier = modifier.testTag("fab_whatsapp")
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Chat,
                contentDescription = "Chat on WhatsApp"
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "WhatsApp",
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp
            )
        }
    }
}

@Composable
fun TrustPillarCard(
    icon: ImageVector,
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, BorderNavy, RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = MidnightNavy),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(ElectricBlueVariant.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = ElectricBlue,
                    modifier = Modifier.size(22.dp)
                )
            }
            Spacer(modifier = Modifier.width(14.dp))
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = DarkTextPrimary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = DarkTextSecondary,
                    lineHeight = 18.sp
                )
            }
        }
    }
}

@Composable
fun ProfessionalFooter(
    onNavigate: (ScreenDestination) -> Unit,
    whatsappNumber: String,
    contactEmail: String,
    isHindi: Boolean
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF03070E))
            .border(width = 1.dp, color = BorderNavy.copy(alpha = 0.5f))
            .padding(24.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(ElectricBlueVariant),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.TrendingUp,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "AI-POWERED DIGITAL GROWTH AGENCY",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 13.sp,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "AI-Powered Systems. Digital Marketing. Measurable Growth.",
            fontSize = 12.sp,
            color = DarkTextSecondary
        )
        Text(
            text = "Headquarters: Bengaluru & Mumbai, India • Serving Clients Nationwide",
            fontSize = 11.sp,
            color = DarkTextMuted
        )

        Spacer(modifier = Modifier.height(16.dp))
        Divider(color = BorderNavy)
        Spacer(modifier = Modifier.height(16.dp))

        // Direct Action Buttons in Footer
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                onClick = {
                    launchWhatsApp(
                        context,
                        whatsappNumber,
                        "Hello! I am reviewing your website and would like to speak regarding growth services."
                    )
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF25D366)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Icon(Icons.Default.Chat, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("WhatsApp", fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }

            OutlinedButton(
                onClick = {
                    launchEmail(
                        context,
                        contactEmail,
                        "Inquiry: Growth Agency Consultation"
                    )
                },
                shape = RoundedCornerShape(8.dp),
                border = ButtonDefaults.outlinedButtonBorder.copy(brush = Brush.horizontalGradient(listOf(BorderNavy, ElectricBlue))),
                modifier = Modifier.weight(1f)
            ) {
                Icon(Icons.Default.Email, contentDescription = null, tint = ElectricBlue, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("Email Us", fontSize = 12.sp, color = DarkTextPrimary)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Quick Navigation Links
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Services",
                color = ElectricBlue,
                fontSize = 12.sp,
                modifier = Modifier.clickable { onNavigate(ScreenDestination.Services) }
            )
            Text(
                text = "Industries",
                color = ElectricBlue,
                fontSize = 12.sp,
                modifier = Modifier.clickable { onNavigate(ScreenDestination.Industries) }
            )
            Text(
                text = "Growth Audit",
                color = ElectricBlue,
                fontSize = 12.sp,
                modifier = Modifier.clickable { onNavigate(ScreenDestination.GrowthAudit) }
            )
            Text(
                text = "Packages",
                color = ElectricBlue,
                fontSize = 12.sp,
                modifier = Modifier.clickable { onNavigate(ScreenDestination.Packages) }
            )
            Text(
                text = "Legal & Privacy",
                color = ElectricBlue,
                fontSize = 12.sp,
                modifier = Modifier.clickable { onNavigate(ScreenDestination.Legal) }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "© 2026 AI-Powered Digital Growth Agency. All rights reserved. Advertising platform spend billed directly to clients. No arbitrary rankings or ROI guaranteed.",
            fontSize = 10.sp,
            color = DarkTextMuted,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
