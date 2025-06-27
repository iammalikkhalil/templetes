package org.apptrick.invotick

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() {

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Invotick",
        ) {
            App()
        }



    }

}

//@Preview
//@Composable
//fun MyBottomNavigationBarPreview() {
//    BottomNavigationBar(rememberNavController())
//}