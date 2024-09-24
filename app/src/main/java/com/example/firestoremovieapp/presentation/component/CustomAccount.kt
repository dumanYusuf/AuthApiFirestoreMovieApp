package com.example.firestoremovieapp.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@Composable
fun CustomAccountText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        modifier = modifier.padding(bottom = 32.dp),
        textAlign = TextAlign.Center
    )
}

