package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "leads")
data class LeadEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val leadType: String, // "DISCOVERY_CALL", "AUDIT_REQUEST", "PROJECT_QUOTE", "CONTACT_INQUIRY"
    val fullName: String,
    val businessName: String,
    val email: String,
    val phone: String,
    val serviceInterest: String,
    val budgetRange: String,
    val projectDescription: String,
    val preferredContact: String, // "WhatsApp", "Phone Call", "Email"
    val targetDate: String = "",
    val sourcePage: String = "Website Home",
    val status: String = "NEW", // "NEW", "CONTACTED", "QUALIFIED", "PROPOSAL_SENT", "WON", "LOST"
    val adminNotes: String = "",
    val followUpReminder: String = "",
    val createdAt: Long = System.currentTimeMillis()
)
