package com.example.testonlinetoursapp.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HelpScreen() {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Text("Помощь")
    }

}