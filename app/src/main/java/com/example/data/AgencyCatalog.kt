package com.example.data

import com.example.model.*

object AgencyCatalog {

    val businessName = "AI-POWERED DIGITAL GROWTH AGENCY"
    val tagline = "AI-Powered Systems. Digital Marketing. Measurable Growth."
    val marketNotice = "Primary Focus: Indian SMBs, local enterprises, high-growth startups & service firms."

    val growthFramework = listOf(
        GrowthFrameworkStep(
            stepIndex = 1,
            title = "Research",
            titleHi = "अनुसंधान (Research)",
            subtitle = "Deep Market & Competitor Audit",
            description = "We dissect your unit economics, customer ICP, competitors' active ad sets, search demand, and current conversion leakages.",
            deliverables = listOf("Competitor ad intelligence dossier", "Customer objection map", "Keyword & search intent index")
        ),
        GrowthFrameworkStep(
            stepIndex = 2,
            title = "Strategy",
            titleHi = "रणनीति (Strategy)",
            subtitle = "Positioning & Funnel Blueprint",
            description = "We engineer irresistible high-ticket or retail offers, map your end-to-end customer journey, and architect your digital growth roadmap.",
            deliverables = listOf("Core value proposition matrix", "Multi-stage funnel map", "90-day growth roadmap")
        ),
        GrowthFrameworkStep(
            stepIndex = 3,
            title = "Create",
            titleHi = "सृजन (Create)",
            subtitle = "High-Converting Assets & Identity",
            description = "We craft responsive landing pages, hook-driven social creatives, high-converting video scripts, and sharp brand identity assets.",
            deliverables = listOf("Speed-optimized landing pages", "Ad creative suites (static + video)", "Persuasive sales copywriting")
        ),
        GrowthFrameworkStep(
            stepIndex = 4,
            title = "Attract",
            titleHi = "आकर्षण (Attract)",
            subtitle = "Multi-Channel Targeted Traffic",
            description = "We deploy targeted Meta & Google ad campaigns combined with local SEO and strategic organic distribution to capture qualified intent.",
            deliverables = listOf("Google Search & Meta Ad campaigns", "Google Business Profile optimization", "Custom audiences & pixel tracking")
        ),
        GrowthFrameworkStep(
            stepIndex = 5,
            title = "Convert",
            titleHi = "रूपांतरण (Convert)",
            subtitle = "Frictionless Lead Capture & Qualification",
            description = "We install verified WhatsApp CTAs, interactive qualification forms, booking engines, and conversion-rate optimized user experiences.",
            deliverables = listOf("Dynamic multi-step lead forms", "Direct WhatsApp click-to-chat triggers", "Calendar appointment booking integration")
        ),
        GrowthFrameworkStep(
            stepIndex = 6,
            title = "Automate",
            titleHi = "स्वचालन (Automate)",
            subtitle = "AI Bots & Instant Follow-Up Systems",
            description = "We connect incoming leads to automated CRM notifications, instant WhatsApp welcome messages, lead scoring, and appointment reminders.",
            deliverables = listOf("Instant WhatsApp & Email notification workflows", "Lead scoring & CRM pipeline setup", "24/7 AI FAQ assistant integration")
        ),
        GrowthFrameworkStep(
            stepIndex = 7,
            title = "Optimize",
            titleHi = "अनुकूलन (Optimize)",
            subtitle = "Data-Driven Iteration & CAC Reduction",
            description = "Continuous testing of creative variants, audience angles, landing page copy, and funnel bottlenecks to drive down Customer Acquisition Cost.",
            deliverables = listOf("Bi-weekly performance review", "A/B landing page test reports", "Cost-per-acquisition optimization adjustments")
        )
    )

    val services: List<ServiceDetail> = listOf(
        ServiceDetail(
            id = "strategy",
            category = "Growth Foundations",
            title = "Strategy & Growth Planning",
            titleHi = "रणनीति और विकास योजना",
            shortSummary = "Data-backed market research, positioning, customer journey mapping, and 90-day execution roadmaps.",
            shortSummaryHi = "डेटा-आधारित बाज़ार अनुसंधान, स्थिति और निष्पादन रोडमैप।",
            customerProblem = "Businesses burn cash on disconnected marketing tactics without a cohesive value proposition, resulting in high CAC and low deal velocity.",
            customerProblemHi = "व्यवसाय बिना किसी स्पष्ट रणनीति के मार्केटिंग पर पैसा खर्च करते हैं, जिससे उच्च ग्राहक अधिग्रहण लागत होती है।",
            whatWeDeliver = "A comprehensive audit of your market landscape, distinct brand positioning, and an actionable funnel blueprint mapped to unit economics.",
            whatWeDeliverHi = "आपके बाज़ार परिदृश्य का गहन ऑडिट, विशिष्ट ब्रांड स्थिति और कार्रवाई योग्य फ़नल ब्लूप्रिंट।",
            deliverables = listOf(
                "Market & competitor analysis report",
                "Ideal Customer Profile (ICP) & audience research",
                "Unique Value Proposition & offer engineering",
                "Complete 90-day digital growth execution roadmap",
                "Full-funnel marketing architecture"
            ),
            processSteps = listOf(
                ProcessStep(1, "Discovery Audit", "We inspect your historical revenue, past campaigns, and target audience."),
                ProcessStep(2, "Competitor Intelligence", "We analyze current market competitors, pricing models, and active advertising hooks."),
                ProcessStep(3, "Offer Engineering", "We refine your core offering to maximize perceived value and conversion rate."),
                ProcessStep(4, "Roadmap Hand-off", "We deliver your structured 90-day action plan with measurable milestones.")
            ),
            suitableBusinesses = "B2B firms, service providers, local business owners launching new offerings, and startups preparing to scale ad spend.",
            faqList = listOf(
                FaqItem(
                    question = "How long does the initial strategy blueprint take?",
                    questionHi = "शुरुआती रणनीति ब्लूप्रिंट में कितना समय लगता है?",
                    answer = "Typically 7 to 10 business days from the initial discovery interview and access handover.",
                    answerHi = "शुरुआती साक्षात्कार के बाद आमतौर पर 7 से 10 कार्यदिवस लगते हैं।"
                ),
                FaqItem(
                    question = "Do you guarantee specific revenue numbers?",
                    questionHi = "क्या आप विशिष्ट राजस्व की गारंटी देते हैं?",
                    answer = "No ethical agency guarantees revenue, as sales depend on closing rates and fulfillment. We engineer high-probability conversion architectures.",
                    answerHi = "हम झूठी गारंटी नहीं देते; हम उच्चतम गुणवत्ता वाली रणनीतिक प्रणाली प्रदान करते हैं।"
                )
            )
        ),
        ServiceDetail(
            id = "branding",
            category = "Creative & Identity",
            title = "Branding & Visual Design",
            titleHi = "ब्रांडिंग और विज़ुअल डिज़ाइन",
            shortSummary = "Cohesive brand identities, high-converting social media creatives, brochures, pitch decks, and advertising graphics.",
            shortSummaryHi = "आकर्षक ब्रांड पहचान, सोशल मीडिया क्रिएटिव्स और विज्ञापन ग्राफिक्स।",
            customerProblem = "Inconsistent or amateur visual appearance erodes consumer trust before the sales conversation even begins.",
            customerProblemHi = "असंगत या अव्यवसायिक डिज़ाइन पहली नज़र में ही ग्राहकों का विश्वास तोड़ देता है।",
            whatWeDeliver = "A cohesive, premium brand design language across digital and print touchpoints that commands respect and justifies higher pricing.",
            whatWeDeliverHi = "एक सुसंगत और प्रीमियम डिज़ाइन शैली जो ग्राहकों में तुरंत विश्वास जगाती है।",
            deliverables = listOf(
                "Brand identity guide (Color palette, typography, tone)",
                "High-converting social media creative suites",
                "Digital & print marketing collateral (brochures, cards)",
                "Investor pitch deck & presentation redesign",
                "Static ad creative variants for paid social"
            ),
            processSteps = listOf(
                ProcessStep(1, "Brand Positioning", "Define mood, brand voice, and industry perception objectives."),
                ProcessStep(2, "Design Concepts", "Present two distinct aesthetic directions for review."),
                ProcessStep(3, "Refinement", "Fine-tune selected design based on real-world application tests."),
                ProcessStep(4, "Asset Library Delivery", "Package all production files with usage guidelines.")
            ),
            suitableBusinesses = "Growing companies looking to elevate credibility, rebrand, or launch marketing campaigns with professional visual assets.",
            faqList = listOf(
                FaqItem(
                    question = "How many revision rounds are included?",
                    questionHi = "कितने संशोधन शामिल हैं?",
                    answer = "Standard creative packages include up to two rounds of revisions per milestone.",
                    answerHi = "मानक पैकेज में प्रत्येक मील के पत्थर पर 2 संशोधन शामिल हैं।"
                )
            )
        ),
        ServiceDetail(
            id = "social_media",
            category = "Organic Growth",
            title = "Social Media Management",
            titleHi = "सोशल मीडिया प्रबंधन",
            shortSummary = "Strategic content calendars, short-form reel production, publishing support, and engagement workflows.",
            shortSummaryHi = "रणनीतिक सामग्री कैलेंडर, रील्स निर्माण और कम्युनिटी प्रबंधन।",
            customerProblem = "Businesses post inconsistently or produce random posts that fail to build authority or drive inbound inquiries.",
            customerProblemHi = "अनियमित पोस्टिंग और बिना रणनीति की सामग्री से कोई नए ग्राहक नहीं मिलते।",
            whatWeDeliver = "A structured, authority-building content engine engineered to nurture warm prospects into booked consultations.",
            whatWeDeliverHi = "एक संगठित सामग्री प्रणाली जो दर्शकों को संभावित ग्राहकों में बदलती है।",
            deliverables = listOf(
                "Monthly content strategy & calendar",
                "Reel / short-form video hooks & scripts",
                "Platform-tailored graphic carousels & banners",
                "SEO-friendly captions & hashtag frameworks",
                "Monthly growth & engagement analytics reports"
            ),
            processSteps = listOf(
                ProcessStep(1, "Audience Hook Analysis", "Identify top burning questions in your niche."),
                ProcessStep(2, "Batch Production", "Create 30-day content calendar with visuals and captions."),
                ProcessStep(3, "Review & Scheduling", "Client review followed by scheduled posting cadence."),
                ProcessStep(4, "Insight Reporting", "Review monthly engagement metrics and top performing hooks.")
            ),
            suitableBusinesses = "Coaching academies, healthcare professionals, local brands, consultants, and founders building personal authority.",
            faqList = listOf(
                FaqItem(
                    question = "Do I have to appear on camera?",
                    questionHi = "क्या मुझे कैमरे के सामने आना होगा?",
                    answer = "While founder-led video builds fastest trust, we also engineer high-impact text, carousel, and faceless visual formats.",
                    answerHi = "फाउंडर वीडियो सबसे प्रभावशाली होते हैं, लेकिन हम कैरोसेल और टेक्स्ट प्रारूप भी बनाते हैं।"
                )
            )
        ),
        ServiceDetail(
            id = "content_video",
            category = "Creative & Identity",
            title = "Content & Video Production",
            titleHi = "कंटेंट और वीडियो प्रोडक्शन",
            shortSummary = "Persuasive copywriting, SEO blog posts, landing page copy, short-form reel editing, and explainer videos.",
            shortSummaryHi = "आकर्षक कॉपी राइटिंग, एसईओ ब्लॉग, शॉर्ट-फॉर्म वीडियो एडिटिंग।",
            customerProblem = "Dull copy and raw unedited videos fail to hold user attention beyond the critical first 3 seconds.",
            customerProblemHi = "बिना हुक और खराब एडिटिंग के वीडियो पर कोई ध्यान नहीं देता।",
            whatWeDeliver = "Retention-focused video editing with dynamic captions, sound design, and persuasive sales copywriting crafted for conversion.",
            whatWeDeliverHi = "डायनामिक कैप्शन और आकर्षक सेल्स कॉपी के साथ तैयार किए गए पेशेवर वीडियो।",
            deliverables = listOf(
                "Conversion-optimized landing page copy",
                "Short-form video editing (Reels / Shorts) with dynamic captions",
                "Educational pillar blog articles",
                "Promotional and service explainer video scripts",
                "Long-form content repurposed into micro-assets"
            ),
            processSteps = listOf(
                ProcessStep(1, "Hook & Storyboarding", "Structure compelling introductions and clear call-to-actions."),
                ProcessStep(2, "Production & Editing", "Pacing cuts, sound effects, subtitles, and branding overlays."),
                ProcessStep(3, "Quality QA", "Review against mobile viewing dimensions and contrast standards."),
                ProcessStep(4, "Distribution Ready", "Export in optimized formats for all platforms.")
            ),
            suitableBusinesses = "Businesses investing in Reels, YouTube, newsletters, or performance ad campaigns.",
            faqList = listOf(
                FaqItem(
                    question = "What raw footage do we need to provide?",
                    questionHi = "हमें क्या वीडियो फुटेज देनी होगी?",
                    answer = "We provide clear recording briefs with shot checklists; you record on phone/camera and upload to our secure portal.",
                    answerHi = "हम रिकॉर्डिंग चेकलिस्ट देते हैं; आप फ़ोन से रिकॉर्ड करके अपलोड कर सकते हैं।"
                )
            )
        ),
        ServiceDetail(
            id = "web_dev",
            category = "Digital Infrastructure",
            title = "High-Converting Websites",
            titleHi = "उच्च-रूपांतरण वेबसाइट विकास",
            shortSummary = "Fast, mobile-first business websites and landing page funnels with validated lead forms and WhatsApp integration.",
            shortSummaryHi = "तेज़ गति, मोबाइल-फ़र्स्ट वेबसाइट और व्हाट्सएप इंटीग्रेशन।",
            customerProblem = "Slow, outdated websites that look clunky on mobile devices and leak 90% of paid and organic visitors.",
            customerProblemHi = "धीमी और पुरानी वेबसाइटें जो मोबाइल पर खराब दिखती हैं और विज़िटर्स को खो देती हैं।",
            whatWeDeliver = "Lightweight, ultra-fast websites designed strictly around conversion pathways, speed optimization, and direct lead capture.",
            whatWeDeliverHi = "अल्ट्रा-फास्ट और आधुनिक वेबसाइट्स जो विज़िटर्स को सीधे लीड्स में बदलने के लिए बनाई गई हैं।",
            deliverables = listOf(
                "Modern, responsive responsive web architecture",
                "High-speed landing pages with <2s load times",
                "One-click WhatsApp chat and call integration",
                "Dynamic multi-step lead capture forms with validation",
                "On-page technical SEO & OpenGraph metadata setup",
                "CMS / maintenance support and security hardening"
            ),
            processSteps = listOf(
                ProcessStep(1, "Information Architecture", "Wireframe page structure for friction-free reading flow."),
                ProcessStep(2, "UI/UX Design", "Custom high-contrast mobile-first responsive layout."),
                ProcessStep(3, "Development & Integration", "Clean code, form validation, WhatsApp routing, analytics."),
                ProcessStep(4, "Speed & Security Audit", "Core Web Vitals testing and deployment verification.")
            ),
            suitableBusinesses = "Any business needing a credible digital storefront that converts clicks into conversations.",
            faqList = listOf(
                FaqItem(
                    question = "Will the website work smoothly on mobile phones?",
                    questionHi = "क्या वेबसाइट मोबाइल पर सुचारू रूप से काम करेगी?",
                    answer = "Yes, we build mobile-first since over 80% of Indian internet users browse on smartphones.",
                    answerHi = "हाँ, हम मोबाइल-फ़र्स्ट अप्रोच अपनाते हैं क्योंकि 80% से अधिक ट्रैफ़िक मोबाइल से आता है।"
                )
            )
        ),
        ServiceDetail(
            id = "seo",
            category = "Organic Growth",
            title = "SEO & Local Google Maps",
            titleHi = "एसईओ और लोकल गूगल मैप्स",
            shortSummary = "Comprehensive keyword research, technical SEO, content optimization, and Google Business Profile ranking.",
            shortSummaryHi = "कीवर्ड रिसर्च, टेक्निकल एसईओ और गूगल बिज़नेस प्रोफ़ाइल रैंकिंग।",
            customerProblem = "Invisible to high-intent local buyers searching 'near me' on Google Maps and organic search.",
            customerProblemHi = "गूगल पर 'नियर मी' सर्च करने वाले ग्राहकों को आपका व्यवसाय दिखाई नहीं देता।",
            whatWeDeliver = "Systematic on-page technical optimization and local map pack optimization to attract local buyers without continuous ad spend.",
            whatWeDeliverHi = "व्यवस्थित एसईओ और गूगल मैप्स अनुकूलन ताकि स्थानीय ग्राहक आसानी से आपसे संपर्क कर सकें।",
            deliverables = listOf(
                "Targeted local & commercial intent keyword research",
                "Google Business Profile (GBP) complete optimization",
                "Technical SEO audit (Core Web Vitals, Schema markup)",
                "Local citation architecture and NAP consistency",
                "Search Console indexing and ranking trajectory reporting"
            ),
            processSteps = listOf(
                ProcessStep(1, "Baseline Audit", "Inspect current rankings, indexing errors, and local visibility score."),
                ProcessStep(2, "GBP Overhaul", "Optimize categories, geocoded media, service menus, and review strategies."),
                ProcessStep(3, "On-Page Optimization", "Implement location pages, schema markup, and metadata."),
                ProcessStep(4, "Monthly Tracking", "Monitor search impressions, phone call clicks, and map directions.")
            ),
            suitableBusinesses = "Clinics, coaching institutes, restaurants, retail shops, legal/accounting firms, and local service providers.",
            faqList = listOf(
                FaqItem(
                    question = "Can you guarantee #1 ranking on Google?",
                    questionHi = "क्या आप गूगल पर #1 रैंकिंग की गारंटी दे सकते हैं?",
                    answer = "No. Google strictly advises against agencies that guarantee rankings. We employ legitimate white-hat strategies that compound over time.",
                    answerHi = "गूगल किसी को रैंकिंग गारंटी देने की अनुमति नहीं देता; हम सुरक्षित और प्रभावी तकनीकों का उपयोग करते हैं।"
                )
            )
        ),
        ServiceDetail(
            id = "performance_marketing",
            category = "Paid Acquisition",
            title = "Performance Marketing (Ads)",
            titleHi = "परफॉरमेंस मार्केटिंग (पेड ऐड्स)",
            shortSummary = "Targeted Meta Ads (Instagram/Facebook), Google Search Ads, and YouTube campaigns focused on qualified inquiries.",
            shortSummaryHi = "मेटा ऐड्स और गूगल ऐड्स अभियान लक्षित लीड्स के लिए।",
            customerProblem = "Wasted budget on boosted posts that generate random likes and unverified spam leads.",
            customerProblemHi = "बूस्टेड पोस्ट्स पर बजट बर्बाद होना जिससे केवल लाइक्स मिलते हैं, वास्तविक ग्राहक नहीं।",
            whatWeDeliver = "Engineered paid acquisition campaigns with strict conversion tracking, negative keyword filtering, and qualified lead forms.",
            whatWeDeliverHi = "सटीक ट्रैकिंग और उच्च-गुणवत्ता वाले लीड फॉर्म के साथ लक्षित विज्ञापन अभियान।",
            deliverables = listOf(
                "Full account structure setup (Meta Ads Manager & Google Ads)",
                "Conversion API & Google tag tracking installation",
                "Audience targeting (Custom audiences, lookalikes, intent keywords)",
                "Ad creative production (hook copies & graphic variations)",
                "Weekly bid optimization and negative keyword maintenance"
            ),
            processSteps = listOf(
                ProcessStep(1, "Tracking Verification", "Ensure conversion events and pixel fires are 100% accurate."),
                ProcessStep(2, "Creative Sprint", "Produce 4 to 6 hook and visual variations to test."),
                ProcessStep(3, "Campaign Launch", "Launch structured testing campaigns across chosen platforms."),
                ProcessStep(4, "Scale & Cull", "Kill underperforming ad sets and scale winning combinations.")
            ),
            suitableBusinesses = "Businesses with proven offers ready to invest minimum INR 25,000+ monthly platform ad budget.",
            faqList = listOf(
                FaqItem(
                    question = "Is the platform ad budget included in agency fees?",
                    questionHi = "क्या विज्ञापन बजट एजेंसी शुल्क में शामिल है?",
                    answer = "No. Advertising spend is billed directly to your own credit card on Meta/Google for total financial transparency.",
                    answerHi = "नहीं, विज्ञापन बजट सीधे मेटा/गूगल को आपके खाते से भुगतान किया जाता है।"
                )
            )
        ),
        ServiceDetail(
            id = "lead_gen",
            category = "Sales Systems",
            title = "Lead Generation & Funnels",
            titleHi = "लीड जनरेशन और फ़नल सिस्टम",
            shortSummary = "End-to-end lead qualification funnels, CRM pipelines, calendar booking workflows, and instant response systems.",
            shortSummaryHi = "लीड क्वालिफिकेशन फ़नल, सीआरएम पाइपलाइन और त्वरित प्रतिक्रिया सिस्टम।",
            customerProblem = "Leads turn cold because the sales team takes hours to respond, or sales reps waste time on unqualified tire-kickers.",
            customerProblemHi = "देर से संपर्क करने के कारण संभावित ग्राहक हाथ से निकल जाते हैं।",
            whatWeDeliver = "Automated qualification systems that filter prospects before they book, triggering instant WhatsApp confirmation to increase show-up rates.",
            whatWeDeliverHi = "स्वचालित फ़िल्टर जो अयोग्य लीड्स को हटाते हैं और योग्य ग्राहकों के साथ तुरंत मीटिंग तय करते हैं।",
            deliverables = listOf(
                "Interactive qualifying question forms",
                "Automated booking calendar sync (Google / Calendly)",
                "CRM setup & pipeline staging (New, Contacted, Qualified, Closed)",
                "Speed-to-lead instant WhatsApp notifications",
                "Multi-touch follow-up sequence templates"
            ),
            processSteps = listOf(
                ProcessStep(1, "Friction Audit", "Identify where prospects currently drop off in your sales cycle."),
                ProcessStep(2, "Funnel Architecture", "Build multi-step qualification questions."),
                ProcessStep(3, "CRM Integration", "Route incoming data instantly to team mobile phones."),
                ProcessStep(4, "Follow-up Automation", "Trigger automated WhatsApp & SMS reminder cadence.")
            ),
            suitableBusinesses = "Real estate brokers, coaching institutes, B2B agencies, and consultative service providers.",
            faqList = listOf(
                FaqItem(
                    question = "How quickly does the system notify our team?",
                    questionHi = "सिस्टम हमारी टीम को कितनी जल्दी सूचित करता है?",
                    answer = "Within seconds via automated WhatsApp and email webhooks.",
                    answerHi = "व्हाट्सएप और ईमेल के माध्यम से कुछ ही सेकंड में।"
                )
            )
        ),
        ServiceDetail(
            id = "ai_automation",
            category = "AI & Systems",
            title = "AI Systems & Automation",
            titleHi = "एआई सिस्टम्स और ऑटोमेशन",
            shortSummary = "Automated customer support bots, AI-assisted content workflows, CRM data enrichment, and automated reporting.",
            shortSummaryHi = "24/7 एआई चैटबॉट्स, सीआरएम ऑटोमेशन और स्वचालित रिपोर्टिंग।",
            customerProblem = "Business owners and staff waste 15+ hours weekly on repetitive administrative tasks, copy-pasting data, and answering repeat FAQs.",
            customerProblemHi = "बार-बार पूछे जाने वाले सवालों और डेटा एंट्री में हर हफ़्ते घंटों का समय बर्बाद होना।",
            whatWeDeliver = "Custom automated workflows and smart AI assistants that handle repetitive customer inquiries and sync business operations 24/7.",
            whatWeDeliverHi = "कस्टम ऑटोमेशन और स्मार्ट एआई असिस्टेंट्स जो बिना थके 24/7 आपके काम को आसान बनाते हैं।",
            deliverables = listOf(
                "24/7 Knowledge-base AI assistant for website & WhatsApp",
                "Automated lead qualification and routing flows",
                "Automated weekly client reporting pipelines",
                "Multi-app integration connecting Forms, CRM, and Sheets",
                "Standard operating procedure (SOP) documentation"
            ),
            processSteps = listOf(
                ProcessStep(1, "Workflow Mapping", "Audit repetitive manual bottlenecks in your daily operations."),
                ProcessStep(2, "System Blueprint", "Design trigger-action automation sequences."),
                ProcessStep(3, "AI Prompt Tuning", "Train assistants on your accurate service policies and FAQs."),
                ProcessStep(4, "Testing & Safeguards", "Extensive testing to prevent hallucinations or erroneous outputs.")
            ),
            suitableBusinesses = "Busy owners, client service teams, and growing businesses wanting to scale without hiring immediate administrative overhead.",
            faqList = listOf(
                FaqItem(
                    question = "Can the AI give wrong information to customers?",
                    questionHi = "क्या एआई ग्राहकों को गलत जानकारी दे सकता है?",
                    answer = "We ground the assistant strictly within your approved documentation and add human fallback escalations.",
                    answerHi = "हम एआई को केवल आपके अनुमोदित दस्तावेज़ों के आधार पर ही उत्तर देने के लिए प्रशिक्षित करते हैं।"
                )
            )
        ),
        ServiceDetail(
            id = "analytics",
            category = "Digital Infrastructure",
            title = "Analytics & Conversion Tracking",
            titleHi = "एनालिटिक्स और रूपांतरण ट्रैकिंग",
            shortSummary = "Google Analytics 4 setup, server-side conversion tracking, Search Console configuration, and ROI dashboards.",
            shortSummaryHi = "गूगल एनालिटिक्स (GA4), सर्च कंसोल और सटीक रिपोर्टिंग डैशबोर्ड।",
            customerProblem = "Marketing blind spots where owners don't know which channel generated which paying customer.",
            customerProblemHi = "यह पता न होना कि किस विज्ञापन या चैनल से वास्तविक ग्राहक आ रहे हैं।",
            whatWeDeliver = "Rock-solid conversion attribution tracking connecting every lead back to its originating campaign, ad, and keyword.",
            whatWeDeliverHi = "स्पष्ट ट्रैकिंग प्रणाली जिससे हर लीड और ग्राहक के वास्तविक स्रोत का पता चलता है।",
            deliverables = listOf(
                "Google Analytics 4 (GA4) custom event tracking setup",
                "Google Tag Manager clean container architecture",
                "Lead form submission and phone click tracking",
                "Search Console indexing and technical health setup",
                "Executive monthly ROI and acquisition summary"
            ),
            processSteps = listOf(
                ProcessStep(1, "Tagging Audit", "Verify existing tags and remove broken legacy scripts."),
                ProcessStep(2, "Event Taxonomy", "Define key conversion actions (Form submit, WhatsApp click, Call)."),
                ProcessStep(3, "Implementation", "Install tags via GTM with rigorous preview debugging."),
                ProcessStep(4, "Dashboard Setup", "Provide clear visibility into traffic sources and conversion rates.")
            ),
            suitableBusinesses = "Any business spending money on ads or optimizing a web funnel that demands financial clarity.",
            faqList = listOf(
                FaqItem(
                    question = "Why is GA4 setup so critical?",
                    questionHi = "GA4 सेटअप इतना महत्वपूर्ण क्यों है?",
                    answer = "Without accurate event tracking, ad algorithms (Meta/Google) cannot optimize towards your highest-value clients.",
                    answerHi = "सटीक ट्रैकिंग के बिना विज्ञापन प्लेटफॉर्म सही ग्राहकों को लक्षित नहीं कर पाते हैं।"
                )
            )
        )
    )

    val industries: List<IndustryVertical> = listOf(
        IndustryVertical(
            id = "coaching_education",
            name = "Coaching & Education",
            nameHi = "कोचिंग सेंटर और शिक्षा",
            iconName = "School",
            primaryChallenges = listOf(
                "Seasonal admission peaks with high drop-off between inquiry and visit",
                "Parents comparing multiple institutes on fees without understanding teaching quality",
                "High reliance on local pamphlet distribution that lacks measurable ROI"
            ),
            customizedGrowthStrategy = "Hyper-local Google Business Profile dominance, targeted Meta lead ads targeting parents within 5-10km, interactive demo class booking funnels, and WhatsApp follow-up automation.",
            recommendedTechStack = listOf("Meta Ads", "Google Maps Local Pack", "WhatsApp Business API", "Demo Booking Funnel"),
            expectedImpactArea = "Reduced cost per qualified admission inquiry and higher demo session attendance."
        ),
        IndustryVertical(
            id = "local_businesses",
            name = "Local Retail & Services",
            nameHi = "स्थानीय व्यवसाय और सेवाएँ",
            iconName = "Store",
            primaryChallenges = listOf(
                "Losing foot traffic to quick-commerce and online marketplaces",
                "Outdated listings with inaccurate phone numbers and low review counts",
                "Inconsistent customer retention after first purchase"
            ),
            customizedGrowthStrategy = "Google Maps 3-pack optimization, QR-based review generation workflows, local catalog showcases on WhatsApp, and localized Google search ads for emergency services.",
            recommendedTechStack = listOf("Local SEO", "Review Automation", "WhatsApp Catalog", "Hyper-local Search Ads"),
            expectedImpactArea = "Increased high-intent inbound phone calls and local store foot traffic."
        ),
        IndustryVertical(
            id = "hospitality_restaurants",
            name = "Restaurants & Hospitality",
            nameHi = "रेस्तरां और आतिथ्य सत्कार",
            iconName = "Restaurant",
            primaryChallenges = listOf(
                "Aggressive commission cuts (25-35%) from third-party delivery aggregators",
                "Empty weekday tables and unpredictable weekend rushes",
                "Under-utilized visual appeal on Instagram and short-form video"
            ),
            customizedGrowthStrategy = "High-aesthetic food reel production, direct table booking landing pages, geo-fenced Instagram ads for weekend specials, and direct WhatsApp re-ordering funnels.",
            recommendedTechStack = listOf("Short-form Food Reels", "Direct Ordering WhatsApp Funnel", "Instagram Geo-ads", "Table Reservation Bot"),
            expectedImpactArea = "Higher direct repeat orders and steady weekday table reservations."
        ),
        IndustryVertical(
            id = "ecommerce",
            name = "E-Commerce & D2C",
            nameHi = "ई-कॉमर्स और डायरेक्ट-टू-कंज्यूमर",
            iconName = "ShoppingCart",
            primaryChallenges = listOf(
                "High Customer Acquisition Cost (CAC) eating up gross margins",
                "High abandoned cart rates on mobile checkout",
                "High Cash-on-Delivery (COD) Return-to-Origin (RTO) rates in India"
            ),
            customizedGrowthStrategy = "Creative-led Meta ad testing, speed-optimized product landing pages, automated WhatsApp cart recovery with pre-paid discount incentives, and COD confirmation workflows.",
            recommendedTechStack = listOf("Meta Advantage+ Campaigns", "WhatsApp RTO Prevention Bot", "Fast Mobile Checkout", "GA4 E-commerce Tracking"),
            expectedImpactArea = "Lower blended CAC, recovered abandoned carts, and reduced COD returns."
        ),
        IndustryVertical(
            id = "real_estate",
            name = "Real Estate & Developers",
            nameHi = "रियल एस्टेट और डेवलपर्स",
            iconName = "Apartment",
            primaryChallenges = listOf(
                "Flooded with fake or unqualified phone numbers from low-friction ad forms",
                "High site-visit no-show rates",
                "Long sales cycles requiring months of consistent follow-up"
            ),
            customizedGrowthStrategy = "Strict multi-step qualification funnels (budget & possession timeline verification), 3D walk-through video ads, automated site visit booking, and CRM lead nurture sequences.",
            recommendedTechStack = listOf("Google Search Intent Ads", "Video Walkthrough Reels", "Automated Site Visit Scheduling", "Broker/Agent CRM Pipeline"),
            expectedImpactArea = "Higher site-visit conversion and elimination of junk ad leads."
        ),
        IndustryVertical(
            id = "professional_services",
            name = "Professional Services (B2B)",
            nameHi = "व्यावसायिक सेवाएँ (B2B / CA / Legal)",
            iconName = "BusinessCenter",
            primaryChallenges = listOf(
                "Heavy dependence on word-of-mouth that fluctuates unpredictably",
                "Difficulty explaining complex consultative services concisely",
                "Uncertainty around positioning against cut-price competitors"
            ),
            customizedGrowthStrategy = "High-authority case study funnels, LinkedIn thought leadership, Google Search ads capturing high-intent commercial keywords, and automated discovery call scheduling.",
            recommendedTechStack = listOf("Google High-Intent Search", "Authority Landing Pages", "Discovery Call Booking", "Client Case Studies"),
            expectedImpactArea = "Predictable inbound corporate inquiries with higher average deal sizes."
        ),
        IndustryVertical(
            id = "startups_personal_brands",
            name = "Startups & Personal Brands",
            nameHi = "स्टार्टअप्स और पर्सनल ब्रांड्स",
            iconName = "RocketLaunch",
            primaryChallenges = listOf(
                "Limited initial marketing runway requiring rapid go-to-market testing",
                "Difficulty standing out in crowded digital feeds",
                "Unclear messaging that confuses early adopters"
            ),
            customizedGrowthStrategy = "Lean growth sprint: rapid landing page iteration, hook-driven short-form video production, targeted early adopter acquisition campaigns, and automated feedback loops.",
            recommendedTechStack = listOf("Rapid Web Funnel", "Founder Reel Engine", "Targeted Paid Traffic", "Lead Qualification Workflow"),
            expectedImpactArea = "Rapid validation of product-market messaging and qualified waitlist traction."
        )
    )

    val packages: List<PackagePlan> = listOf(
        PackagePlan(
            id = "starter",
            name = "Starter Growth",
            tierBadge = "Essential Foundation",
            tagline = "For local businesses and single-location firms establishing a clean, credible digital presence.",
            pricingMonthly = "₹24,999 / mo (or Request Custom Scope)",
            featuresIncluded = listOf(
                "Google Business Profile (GBP) complete setup & optimization",
                "1 High-converting responsive landing page / micro-site",
                "Local SEO keyword & citation baseline audit",
                "12 Designed social media graphics + captions monthly",
                "Direct WhatsApp Click-to-Chat button & inquiry routing",
                "Monthly performance summary report"
            ),
            exclusions = listOf(
                "Paid ad management (Meta/Google Ads)",
                "Custom video shooting or on-site filming",
                "Complex custom backend software development"
            ),
            recommendedFor = "Single-clinic doctors, local retail stores, coaching institutes, and boutique consultants.",
            revisionsPolicy = "Up to 2 revision rounds per content milestone within 3 business days.",
            timeline = "Initial setup completed in 7-10 business days."
        ),
        PackagePlan(
            id = "growth",
            name = "Growth Acceleration",
            tierBadge = "Most Popular",
            tagline = "For established businesses ready to systematically scale customer inquiries through ads and content.",
            pricingMonthly = "₹49,999 / mo (or Request Custom Scope)",
            featuresIncluded = listOf(
                "Everything in Starter Growth",
                "Performance Marketing: Meta Ads (FB & Instagram) management",
                "Google Ads (Search & Maps) campaign management",
                "Ad creative production: 6 static creatives + 4 short-form video edits/mo",
                "Multi-step lead capture form with qualification questions",
                "Automated instant WhatsApp notification to sales reps",
                "GA4 conversion event tracking & bi-weekly optimization calls"
            ),
            exclusions = listOf(
                "Platform ad spend (Paid directly to Meta/Google by client)",
                "Cold telecalling or dedicated human sales closing staff"
            ),
            recommendedFor = "Growing firms, coaching academies, clinics, regional real estate firms, and e-commerce stores.",
            revisionsPolicy = "Up to 2 revision rounds on ad creatives; continuous weekly ad bid adjustments.",
            timeline = "Campaigns launched within 10-14 business days of onboarding."
        ),
        PackagePlan(
            id = "performance",
            name = "Performance Scale",
            tierBadge = "High Velocity",
            tagline = "For competitive brands scaling monthly ad budgets with multi-channel funnels and video.",
            pricingMonthly = "₹89,999 / mo (or Request Custom Scope)",
            featuresIncluded = listOf(
                "Everything in Growth Acceleration",
                "YouTube Ads & Meta Advantage+ retargeting funnels",
                "8 High-retention short-form video reels produced monthly",
                "A/B split-testing on landing pages for conversion rate lift",
                "Dedicated CRM pipeline integration & stage tracking",
                "Automated review collection & reputation management system",
                "Weekly live strategy & ROAS review reviews"
            ),
            exclusions = listOf(
                "Direct platform advertising spend (Meta, Google, YouTube)",
                "Physical print distribution fees"
            ),
            recommendedFor = "Multi-location businesses, high-ticket B2B service firms, and established D2C brands.",
            revisionsPolicy = "Priority 24-48h revision turnaround on marketing creative sprints.",
            timeline = "Complete full-funnel deployment within 14 business days."
        ),
        PackagePlan(
            id = "full_partner",
            name = "Full Growth Partner",
            tierBadge = "End-to-End Retainer",
            tagline = "An embedded full-stack growth team: AI automations, bespoke funnels, creative, and analytics.",
            pricingMonthly = "₹1,49,999 / mo (or Bespoke Enterprise Scope)",
            featuresIncluded = listOf(
                "Complete custom web platform development & continuous iteration",
                "Full-stack AI automation: 24/7 custom chatbot & CRM workflows",
                "Omnichannel paid acquisition (Meta, Google Search, Display, YouTube)",
                "16 Short-form video edits + 16 branded carousel graphics monthly",
                "Complete Local SEO & national organic search ranking campaign",
                "Dedicated Senior Growth Strategist & Slack/WhatsApp VIP channel",
                "Real-time analytics dashboard & weekly strategic alignment"
            ),
            exclusions = listOf(
                "Direct ad platform spend billed to client billing profile",
                "Third-party software SaaS subscriptions (e.g. CRM / WhatsApp API fees)"
            ),
            recommendedFor = "Enterprises, multi-city educational chains, established real estate developers, and funded startups.",
            revisionsPolicy = "Iterative agile sprints with rapid weekly deployments.",
            timeline = "Immediate priority onboarding with ongoing weekly execution."
        )
    )

    val caseStudies: List<CaseStudy> = listOf(
        CaseStudy(
            id = "cs_coaching",
            title = "Apex IIT-JEE Academy: Local Maps & Multi-Step Admission Funnel",
            category = "Websites & Campaigns",
            industry = "Coaching & Education",
            isConcept = true, // Transparently labeled as required
            objective = "Eliminate wasted spend on print newspaper flyers and build a predictable digital inquiry pipeline for academic admissions.",
            solutionArchitecture = "Transformed Google Business Profile, engineered a 3-step scholarship test registration funnel, and deployed localized Meta lead campaigns with instant WhatsApp confirmation.",
            deliverablesProduced = listOf(
                "Mobile-first scholarship test landing page",
                "Google Maps 3-pack profile optimization",
                "Targeted Meta ad sets targeting parents within 8km",
                "Automated WhatsApp notification to admissions counselor"
            ),
            outcomeFramework = "Framework designed to increase verified parent inquiries by 40% while reducing cost per qualified lead by filtering out non-serious inquiries through step-based validation."
        ),
        CaseStudy(
            id = "cs_clinic",
            title = "Aura Dental Care: High-Intent Google Search & WhatsApp Booking",
            category = "SEO & Lead Gen",
            industry = "Healthcare / Local Services",
            isConcept = true,
            objective = "Capture patients actively searching for dental implants and aligners while reducing no-show rates for consultations.",
            solutionArchitecture = "Built high-speed mobile landing pages for key procedures, integrated one-click WhatsApp appointment booking, and ran geo-targeted Google search ads with negative keyword filters.",
            deliverablesProduced = listOf(
                "Fast landing page with verified doctor credentials",
                "One-click WhatsApp chat link with pre-filled service inquiry",
                "Google Ads search campaign targeting high-value procedure keywords",
                "Automated appointment reminder message template"
            ),
            outcomeFramework = "Framework structured to drive patient consultations directly via WhatsApp, slashing response time from 4 hours to under 2 minutes."
        ),
        CaseStudy(
            id = "cs_d2c",
            title = "Veda Organics: D2C WhatsApp Cart Recovery & Meta Ad Testing",
            category = "Campaigns & Automation",
            industry = "E-Commerce",
            isConcept = true,
            objective = "Improve profitability by recovering high abandoned cart volume and reducing Cash-on-Delivery (COD) returns.",
            solutionArchitecture = "Deployed creative hook variations on Meta Advantage+ shopping campaigns, paired with automated WhatsApp abandoned cart recovery offering instant pre-paid discounts.",
            deliverablesProduced = listOf(
                "8 Visual ad hook variations for Instagram Reels",
                "Automated WhatsApp cart abandonment sequence",
                "COD order verification confirmation flow",
                "GA4 enhanced e-commerce conversion tracking"
            ),
            outcomeFramework = "Designed to recover 12-18% of abandoned checkouts and reduce Return-to-Origin rates through automated phone verification."
        ),
        CaseStudy(
            id = "cs_b2b",
            title = "FinEdge Advisory: Corporate B2B Lead Engine & Thought Leadership",
            category = "Strategy & Branding",
            industry = "Professional Services",
            isConcept = true,
            objective = "Transition from unpredictable word-of-mouth referrals to a steady stream of qualified corporate tax and audit inquiries.",
            solutionArchitecture = "Developed a premium corporate brand identity, published deep-dive tax compliance playbooks, and deployed focused Google Search ads targeting CFOs and founders.",
            deliverablesProduced = listOf(
                "Executive brand design guidelines and pitch collateral",
                "Gated corporate tax strategy guide with qualification form",
                "Google Search ad campaign for corporate compliance keywords",
                "Discovery call scheduling funnel with calendar sync"
            ),
            outcomeFramework = "Engineered to attract pre-qualified enterprise decision makers ready for structured retainer contracts."
        )
    )

    val faqs: List<FaqItem> = listOf(
        FaqItem(
            question = "How quickly do we see results after launching?",
            questionHi = "शुरू करने के बाद हमें कितनी जल्दी परिणाम दिखेंगे?",
            answer = "Paid search and social ads typically generate incoming inquiries within the first 5 to 7 days of campaign launch. Organic SEO and authority content compound over 3 to 6 months.",
            answerHi = "पेड विज्ञापन पहले 5-7 दिनों में परिणाम देना शुरू कर देते हैं, जबकि एसईओ 3 से 6 महीनों में निरंतर बढ़ता है।"
        ),
        FaqItem(
            question = "Do you guarantee a specific return on investment (ROI)?",
            questionHi = "क्या आप निश्चित आरओआई की गारंटी देते हैं?",
            answer = "No authentic agency can guarantee sales or rankings, as final business conversion depends on pricing, sales closing, and fulfillment. We engineer battle-tested systems that minimize CAC and maximize lead quality.",
            answerHi = "कोई भी जिम्मेदार एजेंसी बिक्री की झूठी गारंटी नहीं दे सकती। हम आपके विज्ञापनों और फ़नल की दक्षता को अधिकतम करते हैं।"
        ),
        FaqItem(
            question = "How is platform ad budget handled?",
            questionHi = "विज्ञापन बजट का प्रबंधन कैसे किया जाता है?",
            answer = "You pay the ad spend directly to Meta or Google using your own company credit card or billing profile. Our agency fee covers strategy, creative production, campaign management, and optimization.",
            answerHi = "विज्ञापन का बजट सीधे आपके कार्ड से मेटा/गूगल को जाता है। हमारा शुल्क रणनीति, डिज़ाइन और प्रबंधन के लिए होता है।"
        ),
        FaqItem(
            question = "Why is WhatsApp integration so important for Indian businesses?",
            questionHi = "भारतीय व्यवसायों के लिए व्हाट्सएप इतना महत्वपूर्ण क्यों है?",
            answer = "In India, email open rates are typically below 15%, whereas WhatsApp messages see open rates over 85%. Capturing leads directly into WhatsApp speeds up the conversation and prevents lost deals.",
            answerHi = "भारत में व्हाट्सएप पर 85% से अधिक ओपन रेट होता है। सीधे व्हाट्सएप पर बात करने से ग्राहक तेज़ी से जुड़ते हैं।"
        ),
        FaqItem(
            question = "What makes AI automation useful for a small business?",
            questionHi = "छोटे व्यवसायों के लिए एआई ऑटोमेशन क्यों उपयोगी है?",
            answer = "AI assistants handle repetitive inquiries 24/7 (such as operating hours, basic pricing, address), while automation routes inquiries instantly to your phone so you never miss a hot customer.",
            answerHi = "एआई 24 घंटे बुनियादी सवालों के जवाब देता है और नई लीड्स को सीधे आपके फ़ोन पर भेजता है ताकि कोई ग्राहक न छूटे।"
        )
    )

    val insights: List<InsightPost> = listOf(
        InsightPost(
            id = "post_1",
            title = "Why Indian SMBs Lose 70% of Paid Ad Leads (And The 5-Minute Fix)",
            category = "Lead Generation",
            readTime = "4 min read",
            date = "Oct 2026",
            summary = "Speed-to-lead is the single biggest determinant of conversion in the Indian digital ecosystem. Here is why routing leads to WhatsApp within 5 minutes doubles closing rates.",
            fullContent = "Studies across Indian retail and professional services reveal that response times over 30 minutes reduce lead conversion probability by over 80%. When a prospect fills a form on Instagram, they are in an active buying mindset. If you wait until the next morning to call, they have already spoken to your competitor. Installing an automated WhatsApp click-to-chat trigger or instant API webhook bridges the gap instantly."
        ),
        InsightPost(
            id = "post_2",
            title = "Google Business Profile 2026: Winning Local Search Without Burning Ad Cash",
            category = "Local SEO",
            readTime = "5 min read",
            date = "Oct 2026",
            summary = "A practical guide to dominating the Google Maps 3-Pack for local clinics, institutes, and stores using geo-tagged media and structured review generation.",
            fullContent = "Ranking in the top 3 of Google Maps is the most valuable free traffic source for any physical business in India. The three pillars that move the needle in 2026 are: 1) Precise primary and secondary category selection, 2) Continuous stream of customer reviews mentioning specific service keywords, and 3) Consistent weekly Google Posts highlighting current offers with clear WhatsApp action buttons."
        ),
        InsightPost(
            id = "post_3",
            title = "Meta Ads vs Google Ads: Where Should You Spend Your First ₹30,000?",
            category = "Performance Marketing",
            readTime = "6 min read",
            date = "Oct 2026",
            summary = "A clear breakdown of intent-based search vs discovery-based social advertising to determine where your initial growth budget generates fastest cashflow.",
            fullContent = "If people already know they need your service (e.g. 'root canal near me', 'emergency plumber', 'commercial CA in Bengaluru'), Google Search Ads are unbeatable because the buyer already has high intent. Conversely, if your product is visually engaging or requires educating the customer (e.g. specialized coaching programs, D2C apparel, unique consulting), Meta Ads provide much cheaper reach and superior visual storytelling."
        )
    )
}
