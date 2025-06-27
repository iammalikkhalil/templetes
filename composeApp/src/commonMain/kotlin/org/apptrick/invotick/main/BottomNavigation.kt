package org.apptrick.invotick.main


//package org.apptrick.invotick.main
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.size
//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.NavigationBar
//import androidx.compose.material3.NavigationBarItem
//import androidx.compose.material3.NavigationBarItemDefaults
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import androidx.navigation.compose.currentBackStackEntryAsState
//import androidx.navigation.compose.rememberNavController
//import invotick.composeapp.generated.resources.Res
//import invotick.composeapp.generated.resources.ic_nav_client
//import invotick.composeapp.generated.resources.ic_nav_dashboard
//import invotick.composeapp.generated.resources.ic_nav_estimate
//import invotick.composeapp.generated.resources.ic_nav_invoice
//import invotick.composeapp.generated.resources.ic_nav_tools
////import org.apptrick.invotick.presentation.navigation.NavigationScreen
//import org.jetbrains.compose.resources.DrawableResource
//import org.jetbrains.compose.resources.painterResource
//import org.jetbrains.compose.resources.stringResource
//import org.jetbrains.compose.ui.tooling.preview.Preview
//
//data class BottomNavigation(
//    val string: String,
//    val icon: DrawableResource
//)
//
//val bottomNavigation = listOf(
////    BottomNavigation(NavigationScreen.Dashboard.route, Res.drawable.ic_nav_dashboard),
////    BottomNavigation(NavigationScreen.Invoice.route, Res.drawable.ic_nav_invoice),
////    BottomNavigation(NavigationScreen.Estimate.route, Res.drawable.ic_nav_estimate),
////    BottomNavigation(NavigationScreen.Client.route, Res.drawable.ic_nav_client),
////    BottomNavigation(NavigationScreen.Tools.route, Res.drawable.ic_nav_tools),
//)
//
//@Composable
//fun BottomNavigationBar(navController: NavController) {
//
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentDestination = navBackStackEntry?.destination
//
//    NavigationBar(
//        modifier = Modifier.background(MaterialTheme.colorScheme.surface),
//        containerColor = MaterialTheme.colorScheme.surface,
//    ) {
//        bottomNavigation.forEach { item ->
//            val isSelected = currentDestinationtination?.route == item.string
//            NavigationBarItem(
//                icon = {
//                    Icon(
//                        painter = painterResource(resource = item.icon), // Use placeholder if icon is null
//                        contentDescription = null,
//                        modifier = Modifier.size(size = 15.dp),
//                        tint = MaterialTheme.colorScheme.onSecondaryContainer
//                    )
//                },
//                label = {
//                    Text(
//                        text = item.string,  // Handle null label safely
//                        style = MaterialTheme.typography.labelSmall
//                    )
//                },
//                selected = isSelected,
//                colors = NavigationBarItemDefaults.colors(
//                    selectedIconColor = MaterialTheme.colorScheme.secondary,
//                    selectedTextColor = MaterialTheme.colorScheme.secondary,
//                    indicatorColor = MaterialTheme.colorScheme.secondaryContainer,
//                    unselectedIconColor = MaterialTheme.colorScheme.secondary,
//                    unselectedTextColor = MaterialTheme.colorScheme.secondary
//                ),
//                onClick = {
//                    if (!isSelected) {
//                        navController.navigate(item.string) {
//                            navController.graph.startDestinationRoute?.let {
//                                popUpTo(it) {
//                                    saveState = true
//                                }
//                            }
//                            launchSingleTop = true
//                            restoreState = true
//                        }
//                    }
//                }
//            )
//        }
//    }
//}
//
////fun <T> NavController.navigateTo(screen: NavigationScreen<T>) {
////    navigate(screen.route)
////}
//
//@Preview
//@Composable
//fun MyBottomNavigationBarPreview() {
//    BottomNavigationBar(rememberNavController())
//}