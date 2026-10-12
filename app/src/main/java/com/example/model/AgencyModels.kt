package com.example.model

data class ProcessStep(
    val stepNumber: Int,
    val title: String,
    val description: String
)

data class FaqItem(
    val question: String,
    val questionHi: String,
    val answer: String,
    val answerHi: String
)

data class ServiceDetail(
    val id: String,
    val category: String,
    val title: String,
    val titleHi: String,
    val shortSummary: String,
    val shortSummaryHi: String,
    val customerProblem: String,
    val customerProblemHi: String,
    val whatWeDeliver: String,
    val whatWeDeliverHi: String,
    val deliverables: List<String>,
    val processSteps: List<ProcessStep>,
    val suitableBusinesses: String,
    val faqList: List<FaqItem>
)

data class IndustryVertical(
    val id: String,
    val name: String,
    val nameHi: String,
    val iconName: String,
    val primaryChallenges: List<String>,
    val customizedGrowthStrategy: String,
    val recommendedTechStack: List<String>,
    val expectedImpactArea: String
)

data class CaseStudy(
    val id: String,
    val title: String,
    val category: String,
    val industry: String,
    val isConcept: Boolean = true, // Strictly adhering to "label concept projects as Concept Project"
    val objective: String,
    val solutionArchitecture: String,
    val deliverablesProduced: List<String>,
    val outcomeFramework: String
)

data class PackagePlan(
    val id: String,
    val name: String,
    val tierBadge: String,
    val tagline: String,
    val pricingMonthly: String,
    val featuresIncluded: List<String>,
    val exclusions: List<String>,
    val recommendedFor: String,
    val revisionsPolicy: String,
    val timeline: String,
    val adSpendNote: String = "Platform advertising budget (Meta/Google) is paid directly to advertising platforms."
)

data class GrowthFrameworkStep(
    val stepIndex: Int,
    val title: String,
    val titleHi: String,
    val subtitle: String,
    val description: String,
    val deliverables: List<String>
)

data class InsightPost(
    val id: String,
    val title: String,
    val category: String,
    val readTime: String,
    val date: String,
    val summary: String,
    val fullContent: String
)

data class AgencySettings(
    val businessName: String = "AI-POWERED DIGITAL GROWTH AGENCY",
    val tagline: String = "AI-Powered Systems. Digital Marketing. Measurable Growth.",
    val whatsappNumber: String = "+919876543210", // E.164 formatted number
    val contactEmail: String = "growth@agency.ai",
    val officeLocation: String = "Bengaluru & Mumbai, India (Serving Pan-India Clients)",
    val discoveryCallDuration: String = "30-Minute Free Discovery Session",
    val adminPin: String = "2468" // Secure master pin for internal CRM
)
