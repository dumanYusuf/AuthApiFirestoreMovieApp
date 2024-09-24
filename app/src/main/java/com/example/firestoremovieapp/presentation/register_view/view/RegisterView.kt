import androidx.compose.foundation.background
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
import com.example.firestoremovieapp.presentation.register_view.RegisterViewModel
import okhttp3.internal.wait

@Composable
fun RegisterView(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel(),
) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var userName by remember { mutableStateOf(TextFieldValue("")) }
    var userLastName by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var confirmPassword by remember { mutableStateOf(TextFieldValue("")) }
    var errorMessage by remember { mutableStateOf("") }

    val state = viewModel.state.collectAsState().value

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
            CustomAccountText(text = "Create Account")

            // Email field
            CustomTextField(
                value = email,
                onValueChange = { email = it },
                label = "Enter Email"
            )

            // Username field
            CustomTextField(
                value = userName,
                onValueChange = { userName = it },
                label = "Enter User Name"
            )

            // User last name field
            CustomTextField(
                value = userLastName,
                onValueChange = { userLastName = it },
                label = "Enter User Last Name"
            )

            // Password field
            CustomTextField(
                value = password,
                onValueChange = { password = it },
                label = "Enter Password",
                isPassword = true
            )

            // Confirm password field
            CustomTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = "Enter Confirm Password",
                isPassword = true
            )

            Button(
                onClick = {
                    if (email.text.isEmpty() || !email.text.contains("@")) {
                        errorMessage = "Please enter a valid email equls @."
                    }
                    else if (password.text.length < 6) {
                        errorMessage = "Password must be at least 6 characters."
                    } else if (password.text != confirmPassword.text) {
                        errorMessage = "Passwords do not match."
                    } else {
                        errorMessage = ""

                        // Register button
                        val newUserModel = UserModel(
                            id = "",
                            email = email.text,
                            userName = userName.text,
                            userLastName = userLastName.text,
                            profilUrl = ""
                        )
                        viewModel.register(newUserModel, password.text)
                        navController.navigate(Screen.HomePage.route)
                    }
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
                    text = "Sign Up",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            TextButton(onClick = {
                // Go to login
                navController.navigate(Screen.LoginPagePage.route)
            }) {
                Text(text = "Already have an account? Sign in")
            }

            // Display error message
            if (errorMessage.isNotBlank()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                )
            }
        }

        // Successful registration state
        if (state.isSucsess) {
            Text(
                text = "Registration successful!",
                color = Color.Green,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 10.dp)
            )
        }

        // Loading state
        if (state.isLoading) {
            CircularProgressIndicator(
                color = Color.Red,
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.Center)
            )
        }

        // Error message state
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




