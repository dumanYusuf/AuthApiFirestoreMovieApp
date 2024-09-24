import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firestoremovieapp.R
import com.example.firestoremovieapp.Screen
import com.example.firestoremovieapp.presentation.favorites_view.view.FavoriPage
import com.example.firestoremovieapp.presentation.home_view.view.HomePage
import com.example.firestoremovieapp.presentation.login_view.view.LoginPage
import com.example.firestoremovieapp.presentation.person_view.view.PersonPage
import com.example.firestoremovieapp.presentation.watch_later_view.view.WatchLaterPage
import com.google.firebase.auth.FirebaseAuth

@Composable
fun PageController() {
    val items = listOf("Home", "Favori", "WatchLater", "Person")
    val currentIndex = remember { mutableStateOf(0) }
    val controller = rememberNavController()
    val auth = FirebaseAuth.getInstance()

    var isUserLoggedIn by remember { mutableStateOf(auth.currentUser != null) }

    LaunchedEffect(true) {
        auth.addAuthStateListener { firebaseAuth ->
            isUserLoggedIn = firebaseAuth.currentUser != null
        }
    }

    Scaffold(
        bottomBar = {
            if (isUserLoggedIn) {
                NavigationBar {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = currentIndex.value == index,
                            onClick = {
                                currentIndex.value = index
                                when (index) {
                                    0 -> controller.navigate(Screen.HomePage.route) {
                                        popUpTo(Screen.HomePage.route) { inclusive = true }
                                    }
                                    1 -> controller.navigate(Screen.FavoriPage.route) {
                                        popUpTo(Screen.FavoriPage.route) { inclusive = true }
                                    }
                                    2 -> controller.navigate(Screen.LaterPage.route) {
                                        popUpTo(Screen.LaterPage.route) { inclusive = true }
                                    }
                                    3 -> controller.navigate(Screen.PersonPage.route) {
                                        popUpTo(Screen.PersonPage.route) { inclusive = true }
                                    }
                                }
                            },
                            icon = {
                                when (item) {
                                    "Home" -> Icon(painter = painterResource(id = R.drawable.home), contentDescription = null)
                                    "Favori" -> Icon(painter = painterResource(id = R.drawable.favori), contentDescription = null)
                                    "WatchLater" -> Icon(painter = painterResource(id = R.drawable.later), contentDescription = null)
                                    "Person" -> Icon(painter = painterResource(id = R.drawable.person), contentDescription = null)
                                }
                            },
                            label = { Text(text = item) }
                        )
                    }
                }
            }

        }
    ) { innerPadding ->
        NavHost(navController = controller, startDestination = if (isUserLoggedIn) Screen.HomePage.route else Screen.RegisterPage.route, Modifier.padding(innerPadding)) {
            composable(Screen.RegisterPage.route) {
                RegisterView(navController = controller)
            }
            composable(Screen.LoginPagePage.route) {
                LoginPage(navController = controller)
            }
            composable(Screen.HomePage.route) {
                HomePage()
            }
            composable(Screen.FavoriPage.route) {
                FavoriPage()
            }
            composable(Screen.LaterPage.route) {
                WatchLaterPage()
            }
            composable(Screen.PersonPage.route) {
                PersonPage(navController = controller)
            }
        }
    }
}





