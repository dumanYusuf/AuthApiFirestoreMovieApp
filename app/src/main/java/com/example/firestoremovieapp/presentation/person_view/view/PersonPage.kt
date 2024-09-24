package com.example.firestoremovieapp.presentation.person_view.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.firestoremovieapp.Screen
import com.example.firestoremovieapp.presentation.person_view.PersonViewModel

@Composable
fun PersonPage(
    modifier: Modifier = Modifier,
    viewModel: PersonViewModel= hiltViewModel(),
    navController: NavController

) {
    Column (modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "PersonPage")
        Button(onClick = {
            viewModel.logOut()
            navController.navigate(Screen.LoginPagePage.route)

        }) {
            Text(text = "LogOut")
        }
    }
}