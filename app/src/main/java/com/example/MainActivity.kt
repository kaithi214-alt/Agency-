package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.AgencyViewModel
import com.example.ui.ScreenDestination
import com.example.ui.components.AgencyTopBar
import com.example.ui.components.WhatsAppFloatButton
import com.example.ui.screens.*
import com.example.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                AgencyApp()
            }
        }
    }
}

@Composable
fun AgencyApp(viewModel: AgencyViewModel = viewModel()) {
    val currentScreen by viewModel.currentScreen.collectAsState()
    val isHindi by viewModel.isHindi.collectAsState()
    val settings by viewModel.settings.collectAsState()

    // Handle system back navigation
    BackHandler(enabled = currentScreen != ScreenDestination.Home) {
        if (!viewModel.navigateBack()) {
            viewModel.navigateTo(ScreenDestination.Home)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = ObsidianNavy,
        topBar = {
            AgencyTopBar(
                isHindi = isHindi,
                onToggleLanguage = { viewModel.toggleLanguage() },
                onNavigate = { viewModel.navigateTo(it) },
                onOpenAdmin = { viewModel.navigateTo(ScreenDestination.AdminCrm) }
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MidnightNavy,
                tonalElevation = 8.dp,
                modifier = Modifier
                    .border(width = 0.5.dp, color = BorderNavy)
                    .windowInsetsPadding(WindowInsets.navigationBars)
                    .testTag("agency_bottom_nav")
            ) {
                NavigationBarItem(
                    selected = currentScreen == ScreenDestination.Home,
                    onClick = { viewModel.navigateTo(ScreenDestination.Home) },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home", fontSize = 10.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = ElectricBlue,
                        indicatorColor = ElectricBlueVariant,
                        unselectedIconColor = DarkTextSecondary,
                        unselectedTextColor = DarkTextSecondary
                    ),
                    modifier = Modifier.testTag("nav_item_home")
                )

                NavigationBarItem(
                    selected = currentScreen == ScreenDestination.Services || currentScreen is ScreenDestination.ServiceDetailView,
                    onClick = { viewModel.navigateTo(ScreenDestination.Services) },
                    icon = { Icon(Icons.Default.Build, contentDescription = "Services") },
                    label = { Text("Services", fontSize = 10.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = ElectricBlue,
                        indicatorColor = ElectricBlueVariant,
                        unselectedIconColor = DarkTextSecondary,
                        unselectedTextColor = DarkTextSecondary
                    ),
                    modifier = Modifier.testTag("nav_item_services")
                )

                NavigationBarItem(
                    selected = currentScreen == ScreenDestination.Industries,
                    onClick = { viewModel.navigateTo(ScreenDestination.Industries) },
                    icon = { Icon(Icons.Default.Domain, contentDescription = "Industries") },
                    label = { Text("Industries", fontSize = 10.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = ElectricBlue,
                        indicatorColor = ElectricBlueVariant,
                        unselectedIconColor = DarkTextSecondary,
                        unselectedTextColor = DarkTextSecondary
                    ),
                    modifier = Modifier.testTag("nav_item_industries")
                )

                NavigationBarItem(
                    selected = currentScreen == ScreenDestination.GrowthAudit,
                    onClick = { viewModel.navigateTo(ScreenDestination.GrowthAudit) },
                    icon = { Icon(Icons.Default.Analytics, contentDescription = "Audit") },
                    label = { Text("Audit", fontSize = 10.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = ElectricBlue,
                        indicatorColor = ElectricBlueVariant,
                        unselectedIconColor = DarkTextSecondary,
                        unselectedTextColor = DarkTextSecondary
                    ),
                    modifier = Modifier.testTag("nav_item_audit")
                )

                NavigationBarItem(
                    selected = currentScreen == ScreenDestination.Packages,
                    onClick = { viewModel.navigateTo(ScreenDestination.Packages) },
                    icon = { Icon(Icons.Default.Sell, contentDescription = "Packages") },
                    label = { Text("Packages", fontSize = 10.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = ElectricBlue,
                        indicatorColor = ElectricBlueVariant,
                        unselectedIconColor = DarkTextSecondary,
                        unselectedTextColor = DarkTextSecondary
                    ),
                    modifier = Modifier.testTag("nav_item_packages")
                )

                NavigationBarItem(
                    selected = currentScreen == ScreenDestination.DiscoveryCall,
                    onClick = { viewModel.navigateTo(ScreenDestination.DiscoveryCall) },
                    icon = { Icon(Icons.Default.PhoneCallback, contentDescription = "Book") },
                    label = { Text("Book Call", fontSize = 10.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = AmberGold,
                        indicatorColor = AmberGold.copy(alpha = 0.8f),
                        unselectedIconColor = DarkTextSecondary,
                        unselectedTextColor = DarkTextSecondary
                    ),
                    modifier = Modifier.testTag("nav_item_book")
                )
            }
        },
        floatingActionButton = {
            // Show WhatsApp button unless on Admin CRM or when filling the form
            if (currentScreen != ScreenDestination.AdminCrm) {
                WhatsAppFloatButton(phoneNumber = settings.whatsappNumber)
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (val screen = currentScreen) {
                is ScreenDestination.Home -> HomeScreen(viewModel)
                is ScreenDestination.Services -> ServicesScreen(viewModel)
                is ScreenDestination.ServiceDetailView -> ServicesScreen(viewModel, selectedServiceId = screen.serviceId)
                is ScreenDestination.Industries -> IndustriesScreen(viewModel)
                is ScreenDestination.GrowthAudit -> GrowthAuditScreen(viewModel)
                is ScreenDestination.Portfolio -> PortfolioScreen(viewModel)
                is ScreenDestination.Packages -> PackagesScreen(viewModel)
                is ScreenDestination.DiscoveryCall -> DiscoveryCallScreen(viewModel)
                is ScreenDestination.AdminCrm -> AdminCrmScreen(viewModel)
                is ScreenDestination.Insights -> InsightsScreen(viewModel)
                is ScreenDestination.Legal -> LegalScreen(viewModel)
            }
        }
    }
}
