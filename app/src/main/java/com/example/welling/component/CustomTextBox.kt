package com.example.welling.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

@Composable
fun CustomTextBox(
    text: String,
    colorId: Int,
    fontSize: TextUnit,
    fontFamily: FontFamily,
    textOnClick: (() -> Unit)?,
    spacerHeight: Dp,
) {
    Text(
        text = text,
        color = colorResource(id = colorId),
        fontSize = fontSize,
        fontFamily = fontFamily,
        modifier = if (textOnClick != null) Modifier.clickable(onClick = textOnClick) else Modifier
    )
    Spacer(modifier = Modifier.height(spacerHeight))
}