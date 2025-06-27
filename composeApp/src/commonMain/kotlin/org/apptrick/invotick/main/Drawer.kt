package org.apptrick.invotick.main//package org.apptrick.invotick.main
//
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import invotick.composeapp.generated.resources.Res
//import invotick.composeapp.generated.resources.compose_multiplatform
//import org.jetbrains.compose.resources.DrawableResource
//import org.jetbrains.compose.resources.painterResource
//import org.jetbrains.compose.resources.stringResource
//
//@Composable
//fun MyDrawerContent(navController: NavController) {
//    Column {
//        Column (
//            modifier = Modifier
//                .fillMaxHeight(0.3f)
//                .fillMaxWidth()
//        ) {
//
//        }
//        DrawerItem(Res.drawable.compose_multiplatform, "Invotic")
//    }
//}
//
//
//Invotic
//@Composable
//fun DrawerItem(icon: DrawableResource, string: String) {
//    Row (
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(10.dp), verticalAlignment = Alignment.CenterVertically
//    ) {
//        Icon(modifier = Modifier.size(40.dp), painter = painterResource(icon), contentDescription = null)
//        Spacer(modifier = Modifier.width(20.dp))
//        Text(text = string,  style = MaterialTheme.typography.bodyMedium)
//    }
//}
//
