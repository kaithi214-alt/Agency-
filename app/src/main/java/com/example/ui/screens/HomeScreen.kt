package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.AgencyCatalog
import com.example.model.*
import com.example.ui.AgencyViewModel
import com.example.ui.ScreenDestination
import com.example.ui.components.*
import com.example.ui.theme.*

@Composable
fun HomeScreen(
    viewModel: AgencyViewModel,
    modifier: Modifier = Modifier
) {
    val isHindi by viewModel.isHindi.collectAsState()
    val settings by viewModel.settings.collectAsState()
    val context = LocalContext.current

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(ObsidianNavy),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        // 1. HERO SECTION
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                MidnightNavy,
                                ObsidianNavy
                            )
                        )
                    )
                    .padding(horizontal = 20.dp, vertical = 28.dp)
            ) {
                SectionBadge(
                    text = if (isHindi) "एआई-पावर्ड ग्रोथ एजेंसी" else "AI-POWERED GROWTH SYSTEMS",
                    color = AmberGold
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = if (isHindi)
                        "स्मार्ट बनाएं। तेज़ी से बढ़ें। AI के साथ स्केल करें।"
                    else
                        "Build Smarter.\nGrow Faster.\nScale with AI.",
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Black,
                    color = Color.White,
                    lineHeight = 40.sp,
                    modifier = Modifier.testTag("hero_headline")
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = if (isHindi)
                        "हम डिजिटल मार्केटिंग, क्रिएटिव सर्विसेज, हाई-स्पीड वेबसाइट्स, एनालिटिक्स और एआई ऑटोमेशन को जोड़कर आपके व्यवसाय के लिए एक संपूर्ण ग्रोथ सिस्टम बनाते हैं।"
                    else
                        "We combine digital marketing, creative services, high-converting websites, analytics, and AI automation to help businesses engineer reliable digital growth systems.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = DarkTextSecondary,
                    lineHeight = 24.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                // CTA Buttons
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = { viewModel.navigateTo(ScreenDestination.DiscoveryCall) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ElectricBlueVariant,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .testTag("btn_hero_discovery_call")
                    ) {
                        Icon(Icons.Default.PhoneCallback, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (isHindi) "डिस्कवरी कॉल बुक करें" else "Book a Discovery Call",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        OutlinedButton(
                            onClick = { viewModel.navigateTo(ScreenDestination.Services) },
                            shape = RoundedCornerShape(12.dp),
                            border = ButtonDefaults.outlinedButtonBorder.copy(
                                brush = Brush.horizontalGradient(listOf(BorderNavy, ElectricBlue))
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp)
                                .testTag("btn_hero_services")
                        ) {
                            Text(
                                text = if (isHindi) "सेवाएं देखें" else "Explore Services",
                                color = DarkTextPrimary,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 13.sp
                            )
                        }

                        Button(
                            onClick = {
                                launchWhatsApp(
                                    context,
                                    settings.whatsappNumber,
                                    "Hello! I saw your agency website and would like to understand how you can help scale my business."
                                )
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF25D366)),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp)
                                .testTag("btn_hero_whatsapp")
                        ) {
                            Icon(Icons.Default.Chat, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("WhatsApp", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                        }
                    }
                }
            }
        }

        // 2. TRUST / ETHICAL PRINCIPLES (No Fake Client Logos or Metrics)
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, BorderNavy, RoundedCornerShape(14.dp)),
                    colors = CardDefaults.cardColors(containerColor = MidnightNavy)
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.VerifiedUser,
                                contentDescription = null,
                                tint = AmberGold,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (isHindi) "हमारे मूलभूत सिद्धांत" else "Our Core Principles",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = AmberGold
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "• 100% Transparent Direct Ad Spend (Paid directly to Meta/Google accounts)\n• No Arbitrary Fake Ranking or ROI Guarantees\n• Sub-5 Minute WhatsApp Speed-to-Lead Response Architecture\n• Transparent Deliverables with Zero Long-Term Hostage Retainers",
                            color = DarkTextSecondary,
                            fontSize = 12.sp,
                            lineHeight = 20.sp
                        )
                    }
                }
            }
        }

        // 3. BUSINESS CHALLENGES WE SOLVE
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            ) {
                SectionHeader(
                    badge = if (isHindi) "समस्याएं" else "Bottlenecks",
                    title = if (isHindi) "वे चुनौतियाँ जिनका हम समाधान करते हैं" else "Business Challenges We Solve",
                    subtitle = if (isHindi) "अधिकांश भारतीय व्यवसायों में डिजिटल मार्केटिंग क्यों असफल होती है" else "Why traditional digital marketing breaks down for modern businesses"
                )

                Spacer(modifier = Modifier.height(16.dp))

                val challenges = listOf(
                    Triple(
                        Icons.Default.TrendingDown,
                        "Inconsistent Inbound Leads",
                        "Relying on random referrals creates revenue peaks and dry spells with no predictable forecast."
                    ),
                    Triple(
                        Icons.Default.MonetizationOn,
                        "Wasted Ad Budgets",
                        "Boosted posts and untracked ad clicks generate likes and fake comments rather than serious buyer inquiries."
                    ),
                    Triple(
                        Icons.Default.Timer,
                        "Slow Speed-to-Lead",
                        "Taking hours to call back leads causes 70%+ of prospects to engage with competitors who respond in minutes."
                    ),
                    Triple(
                        Icons.Default.Devices,
                        "Leaky Websites",
                        "Slow, cluttered websites that fail to articulate value and lack clear WhatsApp or booking pathways."
                    )
                )

                challenges.forEach { (icon, title, desc) ->
                    TrustPillarCard(icon = icon, title = title, description = desc)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }

        // 4. INTERACTIVE GROWTH AUDIT CALLOUT
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 12.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        Brush.linearGradient(
                            listOf(
                                CardNavy,
                                DeepNavy
                            )
                        )
                    )
                    .border(1.dp, ElectricBlue.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
                    .padding(20.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SectionBadge("INTERACTIVE TOOL", ElectricBlue)
                        Icon(
                            imageVector = Icons.Default.Analytics,
                            contentDescription = null,
                            tint = ElectricBlue
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = if (isHindi) "2-मिनट डिजिटल ग्रोथ ऑडिट टूल" else "2-Minute Digital Growth Audit",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = if (isHindi)
                            "अपने उद्योग और वर्तमान स्थिति का चयन करें और एक अनुकूलित विकास योजना प्राप्त करें।"
                        else
                            "Select your industry and current bottlenecks to get an immediate custom digital growth architecture.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = DarkTextSecondary
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { viewModel.navigateTo(ScreenDestination.GrowthAudit) },
                        colors = ButtonDefaults.buttonColors(containerColor = ElectricBlue),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.testTag("btn_open_growth_audit")
                    ) {
                        Text(
                            text = if (isHindi) "ऑडिट शुरू करें" else "Launch Growth Audit",
                            color = ObsidianNavy,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(
                            Icons.Default.ArrowForward,
                            contentDescription = null,
                            tint = ObsidianNavy,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }

        // 5. SERVICES OVERVIEW (10 Core Services)
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SectionHeader(
                        badge = if (isHindi) "हमारी सेवाएं" else "Services",
                        title = if (isHindi) "सम्पूर्ण डिजिटल सेवाएँ" else "Complete Services",
                        subtitle = if (isHindi) "व्यावसायिक विकास के सभी 10 स्तंभ" else "All 10 foundational pillars of digital growth",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                AgencyCatalog.services.forEach { service ->
                    ServiceSummaryCard(
                        service = service,
                        isHindi = isHindi,
                        onViewDetail = {
                            viewModel.navigateTo(ScreenDestination.ServiceDetailView(service.id))
                        }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }

                Button(
                    onClick = { viewModel.navigateTo(ScreenDestination.Services) },
                    colors = ButtonDefaults.buttonColors(containerColor = DeepNavy),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, BorderNavy, RoundedCornerShape(10.dp))
                ) {
                    Text("View Full Services Catalog & Deliverables", color = ElectricBlue)
                }
            }
        }

        // 6. OUR GROWTH FRAMEWORK (Research -> Strategy -> Create -> Attract -> Convert -> Automate -> Optimize)
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MidnightNavy)
                    .padding(horizontal = 20.dp, vertical = 24.dp)
            ) {
                SectionHeader(
                    badge = if (isHindi) "ग्रोथ फ्रेमवर्क" else "Growth Framework",
                    title = if (isHindi) "हमारा 7-चरणीय विकास मॉडल" else "Our 7-Step Growth Framework",
                    subtitle = "Research → Strategy → Create → Attract → Convert → Automate → Optimize"
                )

                Spacer(modifier = Modifier.height(16.dp))

                AgencyCatalog.growthFramework.forEach { step ->
                    FrameworkStepCard(step = step, isHindi = isHindi)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }

        // 7. INDUSTRIES SERVED
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 24.dp)
            ) {
                SectionHeader(
                    badge = if (isHindi) "उद्योग" else "Industries",
                    title = if (isHindi) "हम किसकी मदद करते हैं" else "Who We Help",
                    subtitle = if (isHindi) "प्रत्येक व्यवसाय मॉडल के लिए अनुकूलित रणनीति" else "Tailored growth playbooks designed for specific business dynamics"
                )

                Spacer(modifier = Modifier.height(14.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(horizontal = 4.dp)
                ) {
                    items(AgencyCatalog.industries) { ind ->
                        IndustryMiniCard(
                            industry = ind,
                            isHindi = isHindi,
                            onClick = { viewModel.navigateTo(ScreenDestination.Industries) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedButton(
                    onClick = { viewModel.navigateTo(ScreenDestination.Industries) },
                    shape = RoundedCornerShape(10.dp),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = Brush.horizontalGradient(listOf(BorderNavy, ElectricBlue))
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Explore All 7 Industry Growth Playbooks", color = DarkTextPrimary)
                }
            }
        }

        // 8. CASE STUDIES / PORTFOLIO PREVIEW (Clearly Labeled Concept Projects)
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            ) {
                SectionHeader(
                    badge = if (isHindi) "केस स्टडीज" else "Case Studies",
                    title = if (isHindi) "सत्यापित कार्य और अवधारणाएं" else "Portfolio & Frameworks",
                    subtitle = "Transparently labeled concept projects demonstrating our problem-solving methodology"
                )

                Spacer(modifier = Modifier.height(16.dp))

                AgencyCatalog.caseStudies.forEach { cs ->
                    CaseStudyCard(caseStudy = cs)
                    Spacer(modifier = Modifier.height(12.dp))
                }

                Button(
                    onClick = { viewModel.navigateTo(ScreenDestination.Portfolio) },
                    colors = ButtonDefaults.buttonColors(containerColor = DeepNavy),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, BorderNavy, RoundedCornerShape(10.dp))
                ) {
                    Text("View Full Filterable Portfolio", color = ElectricBlue)
                }
            }
        }

        // 9. ENGAGEMENT MODELS & PACKAGES
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MidnightNavy)
                    .padding(horizontal = 20.dp, vertical = 24.dp)
            ) {
                SectionHeader(
                    badge = if (isHindi) "पैकेज" else "Packages",
                    title = if (isHindi) "पारदर्शी साझेदारी मॉडल" else "Engagement Models",
                    subtitle = "No hidden fees. Platform ad budgets billed directly to you."
                )

                Spacer(modifier = Modifier.height(16.dp))

                AgencyCatalog.packages.forEach { pkg ->
                    PackagePreviewCard(
                        pkg = pkg,
                        onSelect = {
                            viewModel.updateFormField(
                                serviceInterest = "${pkg.name} Package",
                                budgetRange = pkg.pricingMonthly
                            )
                            viewModel.navigateTo(ScreenDestination.DiscoveryCall)
                        }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }

                OutlinedButton(
                    onClick = { viewModel.navigateTo(ScreenDestination.Packages) },
                    shape = RoundedCornerShape(10.dp),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = Brush.horizontalGradient(listOf(BorderNavy, ElectricBlue))
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Compare All Package Inclusions & Exclusions", color = DarkTextPrimary)
                }
            }
        }

        // 10. COMPREHENSIVE AGENCY FAQ
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 24.dp)
            ) {
                SectionHeader(
                    badge = if (isHindi) "प्रश्नोत्तरी" else "FAQ",
                    title = if (isHindi) "अक्सर पूछे जाने वाले सवाल" else "Frequently Asked Questions",
                    subtitle = "Clear answers about contracts, ad budgets, and timelines"
                )

                Spacer(modifier = Modifier.height(16.dp))

                AgencyCatalog.faqs.forEach { faq ->
                    FaqAccordionItem(faq = faq, isHindi = isHindi)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }

        // 11. FINAL HIGH IMPACT CTA
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                CardNavy,
                                MidnightNavy
                            )
                        )
                    )
                    .border(1.dp, BorderNavy, RoundedCornerShape(16.dp))
                    .padding(24.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    SectionBadge("READY TO SCALE?", AmberGold)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = if (isHindi)
                            "अपने व्यवसाय के लिए सही विकास प्रणाली बनाएं"
                        else
                            "Engineer Your Next Level of Digital Growth",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = if (isHindi)
                            "कोई बिक्री का दबाव नहीं। 30-मिनट का रणनीतिक सत्र आपके व्यवसाय की वर्तमान स्थिति की समीक्षा के लिए।"
                        else
                            "No high-pressure sales pitch. A candid 30-minute review of your current customer acquisition channels and leakages.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = DarkTextSecondary,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = { viewModel.navigateTo(ScreenDestination.DiscoveryCall) },
                        colors = ButtonDefaults.buttonColors(containerColor = ElectricBlueVariant),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Icon(Icons.Default.CalendarToday, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (isHindi) "फ्री डिस्कवरी कॉल बुक करें" else "Book Free Discovery Call",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        // 12. PROFESSIONAL FOOTER
        item {
            ProfessionalFooter(
                onNavigate = { viewModel.navigateTo(it) },
                whatsappNumber = settings.whatsappNumber,
                contactEmail = settings.contactEmail,
                isHindi = isHindi
            )
        }
    }
}

@Composable
fun ServiceSummaryCard(
    service: ServiceDetail,
    isHindi: Boolean,
    onViewDetail: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, BorderNavy, RoundedCornerShape(12.dp))
            .clickable { onViewDetail() },
        colors = CardDefaults.cardColors(containerColor = MidnightNavy),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = service.category.uppercase(),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = ElectricBlue,
                    letterSpacing = 0.5.sp
                )
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    tint = DarkTextSecondary,
                    modifier = Modifier.size(16.dp)
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = if (isHindi) service.titleHi else service.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = DarkTextPrimary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = if (isHindi) service.shortSummaryHi else service.shortSummary,
                style = MaterialTheme.typography.bodySmall,
                color = DarkTextSecondary,
                lineHeight = 18.sp
            )
        }
    }
}

@Composable
fun FrameworkStepCard(
    step: GrowthFrameworkStep,
    isHindi: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, BorderNavy, RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = ObsidianNavy),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(ElectricBlueVariant.copy(alpha = 0.25f))
                    .border(1.dp, ElectricBlue, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${step.stepIndex}",
                    fontWeight = FontWeight.Bold,
                    color = ElectricBlue,
                    fontSize = 14.sp
                )
            }
            Spacer(modifier = Modifier.width(14.dp))
            Column {
                Text(
                    text = if (isHindi) step.titleHi else step.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = DarkTextPrimary
                )
                Text(
                    text = step.subtitle,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = AmberGold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = step.description,
                    fontSize = 12.sp,
                    color = DarkTextSecondary,
                    lineHeight = 18.sp
                )
            }
        }
    }
}

@Composable
fun IndustryMiniCard(
    industry: IndustryVertical,
    isHindi: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(220.dp)
            .height(130.dp)
            .border(1.dp, BorderNavy, RoundedCornerShape(12.dp))
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = MidnightNavy),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = if (isHindi) industry.nameHi else industry.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = industry.expectedImpactArea,
                    fontSize = 11.sp,
                    color = DarkTextSecondary,
                    maxLines = 2
                )
            }
            Text(
                text = "View Strategy →",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = ElectricBlue
            )
        }
    }
}

@Composable
fun CaseStudyCard(caseStudy: CaseStudy) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, BorderNavy, RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = MidnightNavy),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = caseStudy.industry.uppercase(),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = ElectricBlue
                )
                if (caseStudy.isConcept) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(BorderNavy)
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
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = caseStudy.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = DarkTextPrimary
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Objective: ${caseStudy.objective}",
                style = MaterialTheme.typography.bodySmall,
                color = DarkTextSecondary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = caseStudy.outcomeFramework,
                style = MaterialTheme.typography.bodySmall,
                color = AmberGold,
                lineHeight = 16.sp
            )
        }
    }
}

@Composable
fun PackagePreviewCard(
    pkg: PackagePlan,
    onSelect: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, BorderNavy, RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = ObsidianNavy),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = pkg.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White
                )
                Text(
                    text = pkg.tierBadge,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = ElectricBlue
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = pkg.pricingMonthly,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = AmberGold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = pkg.tagline,
                fontSize = 12.sp,
                color = DarkTextSecondary
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onSelect,
                colors = ButtonDefaults.buttonColors(containerColor = DeepNavy),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, BorderNavy, RoundedCornerShape(8.dp))
            ) {
                Text("Select & Request Consultation", fontSize = 12.sp, color = ElectricBlue)
            }
        }
    }
}

@Composable
fun FaqAccordionItem(
    faq: FaqItem,
    isHindi: Boolean
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, BorderNavy, RoundedCornerShape(10.dp))
            .clickable { expanded = !expanded },
        colors = CardDefaults.cardColors(containerColor = MidnightNavy),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (isHindi) faq.questionHi else faq.question,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = DarkTextPrimary,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = null,
                    tint = ElectricBlue
                )
            }
            AnimatedVisibility(visible = expanded) {
                Column {
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(color = BorderNavy.copy(alpha = 0.5f))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = if (isHindi) faq.answerHi else faq.answer,
                        fontSize = 13.sp,
                        color = DarkTextSecondary,
                        lineHeight = 20.sp
                    )
                }
            }
        }
    }
}
