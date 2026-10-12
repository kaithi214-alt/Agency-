package com.example.data.local

import kotlinx.coroutines.flow.Flow

class LeadRepository(private val leadDao: LeadDao) {
    val allLeads: Flow<List<LeadEntity>> = leadDao.getAllLeads()

    fun getLeadsByStatus(status: String): Flow<List<LeadEntity>> = leadDao.getLeadsByStatus(status)

    suspend fun insertLead(lead: LeadEntity): Long = leadDao.insertLead(lead)

    suspend fun updateStatus(leadId: Long, status: String) = leadDao.updateLeadStatus(leadId, status)

    suspend fun updateNotes(leadId: Long, notes: String, reminder: String) =
        leadDao.updateLeadNotes(leadId, notes, reminder)

    suspend fun deleteLead(leadId: Long) = leadDao.deleteLead(leadId)
}
