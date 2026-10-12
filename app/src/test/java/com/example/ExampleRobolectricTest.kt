package com.example

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.AgencyCatalog
import com.example.data.local.AppDatabase
import com.example.data.local.LeadEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("AI Growth Agency", appName)
    }

    @Test
    fun `verify catalog contains all 10 required services`() {
        assertEquals(10, AgencyCatalog.services.size)
    }

    @Test
    fun `verify catalog contains all 7 target industries`() {
        assertEquals(7, AgencyCatalog.industries.size)
    }

    @Test
    fun `verify catalog contains 4 engagement packages`() {
        assertEquals(4, AgencyCatalog.packages.size)
    }

    @Test
    fun `insert and retrieve lead in Room database`() = runBlocking {
        val leadDao = db.leadDao()
        val lead = LeadEntity(
            leadType = "DISCOVERY_CALL",
            fullName = "Vikram Mehta",
            businessName = "Mehta Diagnostics",
            email = "vikram@mehtadiag.in",
            phone = "+919876543210",
            serviceInterest = "Local SEO & Google Maps",
            budgetRange = "₹25,000 - ₹50,000 / mo",
            projectDescription = "Need local map pack rankings for 3 clinic locations",
            preferredContact = "WhatsApp"
        )

        val id = leadDao.insertLead(lead)
        assertNotNull(id)

        val allLeads = leadDao.getAllLeads().first()
        assertEquals(1, allLeads.size)
        assertEquals("Vikram Mehta", allLeads[0].fullName)
        assertEquals("NEW", allLeads[0].status)

        // Test status update
        leadDao.updateLeadStatus(id, "QUALIFIED")
        val updatedLeads = leadDao.getAllLeads().first()
        assertEquals("QUALIFIED", updatedLeads[0].status)
    }
}
