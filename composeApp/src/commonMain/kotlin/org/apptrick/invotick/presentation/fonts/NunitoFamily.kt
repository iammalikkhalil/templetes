package org.apptrick.invotick.presentation.fonts

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import invotick.composeapp.generated.resources.Res
import invotick.composeapp.generated.resources.nunito_black
import invotick.composeapp.generated.resources.nunito_bold
import invotick.composeapp.generated.resources.nunito_extra_bold
import invotick.composeapp.generated.resources.nunito_extra_light
import invotick.composeapp.generated.resources.nunito_light
import invotick.composeapp.generated.resources.nunito_medium
import invotick.composeapp.generated.resources.nunito_regular
import invotick.composeapp.generated.resources.nunito_semi_bold
import org.jetbrains.compose.resources.Font



@Composable
fun NunitoFamily() = FontFamily(
    Font(Res.font.nunito_extra_light, weight = FontWeight.ExtraLight),
    Font(Res.font.nunito_light, weight = FontWeight.Light),
    Font(Res.font.nunito_regular, weight = FontWeight.Normal),
    Font(Res.font.nunito_medium, weight = FontWeight.Medium),
    Font(Res.font.nunito_semi_bold, weight = FontWeight.SemiBold),
    Font(Res.font.nunito_bold, weight = FontWeight.Bold),
    Font(Res.font.nunito_extra_bold, weight = FontWeight.ExtraBold),
    Font(Res.font.nunito_black, weight = FontWeight.Black),
)
