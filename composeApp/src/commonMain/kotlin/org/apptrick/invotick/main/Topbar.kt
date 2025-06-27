package org.apptrick.invotick.main//package org.apptrick.invotick.main
//
//import androidx.compose.foundation.background
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.filled.ArrowBack
//import androidx.compose.material.icons.filled.Menu
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.material3.TopAppBarDefaults
//import androidx.compose.material3.rememberTopAppBarState
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.CompositionLocalContext
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.navigation.NavController
//import org.jetbrains.compose.resources.stringResource
//import org.jetbrains.compose.ui.tooling.preview.Preview
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MyTopAppBar(navController: NavController) {
//
//    val currentRoute by navController.currentBackStackEntryFlow.collectAsState(initial = navController.currentBackStackEntry?.destination?.route)
////    val isOnScanScreen = remember(currentRoute) {
////        currentRoute == NavigationScreen.Invoice.route
////    }
//
//    TopAppBar(
//        modifier = Modifier.background(MaterialTheme.colorScheme.surface),
//        title = {
//            Text(
//                text = "Invotick",
//                style = MaterialTheme.typography.titleMedium,
//                color = MaterialTheme.colorScheme.onSecondaryContainer
//            )
//        },
//        colors = TopAppBarDefaults.mediumTopAppBarColors(
//            containerColor = MaterialTheme.colorScheme.surface,
//            scrolledContainerColor = MaterialTheme.colorScheme.primary,
//            navigationIconContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
//            titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
//            actionIconContentColor = MaterialTheme.colorScheme.onSecondaryContainer
//        ),
//        navigationIcon = {
//            IconButton(
//                onClick = {
//
//                }
//            ) {
//                Icon(
//                    imageVector = Icons.Filled.Menu,
//                    contentDescription = "Menu"
//                )
//            }
//        },
//        actions = {},
//        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
//    )
//}
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Preview()
//@Composable
//fun MyTopAppBarPreview() {
//}