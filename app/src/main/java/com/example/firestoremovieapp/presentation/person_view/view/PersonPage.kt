package com.example.firestoremovieapp.presentation.person_view.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.firestoremovieapp.R
import com.example.firestoremovieapp.Screen
import com.example.firestoremovieapp.presentation.person_view.PersonViewModel

@Composable
fun PersonPage(
    modifier: Modifier = Modifier,
    viewModel: PersonViewModel = hiltViewModel(),
    navController: NavController
) {
    LaunchedEffect(true) {
        viewModel.getUser()
    }

    val state = viewModel.state.collectAsState()

    Column(modifier.fillMaxSize()) {
        if (state.value.isLoading){
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color.Red)
            }
        }
        else{
            LazyColumn {
                items(state.value.isSucsess) { user ->
                    Card(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier.size(50.dp),
                                painter = painterResource(id = R.drawable.person),
                                contentDescription = "User Icon"
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text(text = "${user.userName} ${user.userLastName}")
                                Text(text = user.email)
                            }
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.size(20.dp))
       Column (modifier = Modifier.fillMaxSize()){
           Card(
               modifier = modifier
                   .fillMaxWidth()
                   .height(70.dp)
                   .padding(8.dp).clickable {
                       navController.navigate(Screen.FavoriPage.route)
                   }
           ) {
               Row ( modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
                   Text(
                       modifier = Modifier.padding(10.dp),
                       fontWeight = FontWeight.Bold,
                       fontSize = 20.sp,
                       text = "Favori")
                   Icon(
                       modifier = Modifier.size(30.dp),
                       painter = painterResource(id = R.drawable.next),
                       contentDescription = "User Icon"
                   )
               }
           }
           Card(
               modifier = modifier
                   .fillMaxWidth()
                   .height(70.dp)
                   .padding(8.dp).clickable {
                       navController.navigate(Screen.LaterPage.route)
                   }
           ) {
               Row ( modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
                   Text(
                       modifier = Modifier.padding(10.dp),
                       fontWeight = FontWeight.Bold,
                       fontSize = 20.sp,
                       text = "WatchLater")
                   Icon(
                       modifier = Modifier.size(30.dp),
                       painter = painterResource(id = R.drawable.next),
                       contentDescription = "User Icon"
                   )
               }
           }
           Card(
               modifier = modifier
                   .fillMaxWidth()
                   .height(70.dp)
                   .padding(8.dp)
                   .clickable {
                       navController.navigate(Screen.HomePage.route)
                   }
           ) {
               Row ( modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
                   Text(
                       modifier = Modifier.padding(10.dp),
                       fontWeight = FontWeight.Bold,
                       fontSize = 20.sp,
                       text = "Home")
                   Icon(
                       modifier = Modifier.size(30.dp),
                       painter = painterResource(id = R.drawable.next),
                       contentDescription = "User Icon"
                   )
               }
           }

           Spacer(modifier = Modifier.size(20.dp))
           Icon(
               modifier = Modifier.size(80.dp)
                   .clickable {
                       viewModel.logOut()
                   }.align(Alignment.CenterHorizontally),
               painter = painterResource(id = R.drawable.logout), contentDescription = "")
       }
    }
}
