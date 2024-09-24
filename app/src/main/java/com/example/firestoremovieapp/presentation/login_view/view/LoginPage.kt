package com.example.firestoremovieapp.presentation.login_view.view

import CustomText
import CustomTextField
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.firestoremovieapp.Screen
import com.example.firestoremovieapp.presentation.component.CustomAccountText
import com.example.firestoremovieapp.presentation.login_view.LoginViewModel
import okhttp3.internal.wait

@Composable
fun LoginPage(
   navController: NavController,
    viewModel: LoginViewModel= hiltViewModel()
) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    val state=viewModel.state.collectAsState().value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomAccountText(text = "Login Account")
            // Email field
            CustomTextField(
                value = email,
                onValueChange ={email=it} ,
                label = "Enter Email")

            // Password field
            CustomTextField(
                value = password,
                onValueChange ={password=it} ,
                label = "Enter Password",
                isPassword = true)

            Button(
                onClick = {
                    // Login button
                    viewModel.login(email.text,password.text)
                    navController.navigate(Screen.HomePage.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFA726),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Log in",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            TextButton(onClick = {
                navController.navigate(Screen.RegisterPage.route)
            }) {
                Text(text = "Do You have an account? Sign up")
            }
        }



        // başarılı durumu
        if (state.isSucsess){
            Text(
                text = "",
                color = Color.Green,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 10.dp)
            )
        }
        // Yüklenme durumu
        if (state.isLoading) {
            CircularProgressIndicator(
                color = Color.Red,
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.Center)
            )
        }

        // Hata mesajı
        if (state.isError.isNotBlank()) {
            Text(
                text = state.isError,
                color = Color.Red,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            )
        }

    }
}

