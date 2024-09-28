package com.example.firestoremovieapp.presentation.filter_movies_view.views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.firestoremovieapp.R
import com.example.firestoremovieapp.Screen
import com.example.firestoremovieapp.domain.model.CategoryMoviesModel
import com.example.firestoremovieapp.util.Constans
import com.google.gson.Gson
import java.net.URLEncoder


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FilterMoviesPage(
    modifier: Modifier = Modifier,
    filterMovie: CategoryMoviesModel,
    viewModel: FilterMoviesViewModel = hiltViewModel(),
    navController: NavController
) {
    val state=viewModel.stateFilter.collectAsState().value

    LaunchedEffect(true) {
        viewModel.moviesFilter(category = filterMovie.id)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = filterMovie.name)
            })
        },
        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(110.dp)
            ) {
                items(state.categoryFiLTERMoviesList) { filter ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                            .size(300.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(5.dp)
                        ) {
                            val backdropPath = filter.original_language
                            val imageUrl = "${Constans.BASE_IMAGE_URL}$backdropPath"

                            Box(modifier = Modifier.fillMaxWidth()) {
                                Image(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(300.dp)
                                        .clickable {
                                            val movieObject = Gson().toJson(filter)
                                            val encodedMovieObject =
                                                URLEncoder.encode(movieObject, "UTF-8")
                                            navController.navigate(Screen.DetailPage.route + "/$encodedMovieObject")
                                        },
                                    painter = rememberImagePainter(data = imageUrl),
                                    contentDescription = "Movie Backdrop",
                                    contentScale = ContentScale.Crop
                                )
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Row {
                                            Icon(
                                                tint = Color.Yellow,
                                                painter = painterResource(id = R.drawable.calendar),
                                                contentDescription = ""
                                            )
                                            Text(
                                                color = Color.Yellow,
                                                text = filter.release_date
                                            )
                                        }
                                        Row {
                                            Icon(
                                                tint = Color.Yellow,
                                                painter = painterResource(id = R.drawable.star),
                                                contentDescription = ""
                                            )
                                            Text(
                                                color = Color.White,
                                                text = filter.vote_average.toString()
                                            )
                                        }
                                    }
                                    Box(modifier = Modifier.fillMaxSize()) {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxSize(),
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Icon(
                                                tint = Color.White,
                                                modifier = Modifier
                                                    .size(100.dp)
                                                    .align(Alignment.CenterHorizontally),
                                                painter = painterResource(id = R.drawable.play),
                                                contentDescription = ""
                                            )
                                        }

                                        Column(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(bottom = 10.dp, start = 10.dp),
                                            verticalArrangement = Arrangement.Bottom,
                                            horizontalAlignment = Alignment.Start
                                        ) {
                                            Text(
                                                color = Color.White,
                                                text = filter.title,
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 24.sp
                                            )

                                            Row(
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Icon(
                                                    tint = Color.Red,
                                                    painter = painterResource(id = R.drawable.flag),
                                                    contentDescription = ""
                                                )
                                                Spacer(modifier = Modifier.size(5.dp))
                                                Text(
                                                    color = Color.White,
                                                    text = "Dublaj & Altyazı"
                                                )
                                            }
                                        }
                                       }

                                    if (state.isLoading){
                                        Box(modifier = Modifier
                                            .fillMaxWidth()
                                            .align(Alignment.CenterHorizontally)){
                                            CircularProgressIndicator(color = Color.Red
                                            )
                                        }
                                    }
                                    }
                                }
                            }
                        }
                    }
            }
        }
    )
}
