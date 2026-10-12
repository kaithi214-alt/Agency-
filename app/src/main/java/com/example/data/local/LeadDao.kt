package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface LeadDao {
    @Query("SELECT * FROM leads ORDER BY createdAt DESC")
    fun getAllLeads(): Flow<List<LeadEntity>>

    @Query("SELECT * FROM leads WHERE status = :status ORDER BY createdAt DESC")
    fun getLeadsByStatus(status: String): Flow<List<LeadEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLead(lead: LeadEntity): Long

    @Update
    suspend fun updateLead(lead: LeadEntity)

    @Query("UPDATE leads SET status = :newStatus WHERE id = :leadId")
    suspend fun updateLeadStatus(leadId: Long, newStatus: String)

    @Query("UPDATE leads SET adminNotes = :notes, followUpReminder = :reminder WHERE id = :leadId")
    suspend fun updateLeadNotes(leadId: Long, notes: String, reminder: String)

    @Query("DELETE FROM leads WHERE id = :leadId")
    suspend fun deleteLead(leadId: Long)

    @Query("DELETE FROM leads")
    suspend fun clearAllLeads()
}
