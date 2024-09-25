package com.example.firestoremovieapp.presentation.home_view.view


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.firestoremovieapp.presentation.home_view.HomePageViewModel

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    viewModel: HomePageViewModel= hiltViewModel()
) {

    val state=viewModel.state.value


    LaunchedEffect(key1 = true) {
        viewModel.getCategoryMovies()
    }


    Column (modifier.fillMaxSize()){

        Text(
            modifier = Modifier.padding(10.dp),
            fontSize = 24.sp,
            text = "Movies")
        LazyRow(modifier.padding(10.dp)) {
            items(viewModel.state.value.categoryMoviesList) { categoryMovie ->
                Row(
                    modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black
                        ),
                        onClick = {
                            // go to categoryFilterPage
                        }) {
                        Text(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            text = categoryMovie.name
                        )
                    }
                }

                if (state.errorMessage.isNotBlank()) {
                    Text(
                        text = state.errorMessage,
                        color = Color.Red,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Box(modifier = Modifier.fillMaxSize()) {
                    if (state.isLoading) {
                        CircularProgressIndicator(
                            color = Color.Red,
                            modifier = Modifier.align(alignment = Alignment.Center).size(50.dp)
                        )
                    }
                }

            }
    }
    }
}