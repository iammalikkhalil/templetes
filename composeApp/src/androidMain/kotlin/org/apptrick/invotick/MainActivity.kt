package org.apptrick.invotick

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun AppAndroidPreview() {
//    App()
//}

//
//@Preview(showSystemUi = true, showBackground = true, backgroundColor = 0xffffffff)
//@Composable
//fun MyBottomNavigationBarPreview() {
////    DashboardScreen(rememberNavController())
//    InvoiceScreen(rememberNavController())
//}
