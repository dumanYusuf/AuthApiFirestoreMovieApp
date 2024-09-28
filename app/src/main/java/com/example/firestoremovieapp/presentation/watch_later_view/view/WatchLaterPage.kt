package com.example.firestoremovieapp.presentation.watch_later_view.view

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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import coil.compose.rememberAsyncImagePainter
import com.example.firestoremovieapp.R
import com.example.firestoremovieapp.Screen
import com.example.firestoremovieapp.domain.model.FavoriModel
import com.example.firestoremovieapp.domain.model.LaterMovies
import com.example.firestoremovieapp.presentation.watch_later_view.WatchLaterViewModel
import com.example.firestoremovieapp.util.Constans
import com.google.gson.Gson
import java.net.URLEncoder

@Composable
fun WatchLaterPage(
    modifier: Modifier = Modifier,
    viewModel: WatchLaterViewModel= hiltViewModel(),
    navController: NavController
) {

    val state=viewModel.stateLater.collectAsState()

    LaunchedEffect(true) {
        viewModel.getLaterMovies()
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier.fillMaxWidth(),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            text = "Watch Later Movies")
        Spacer(modifier = Modifier.size(10.dp))

        if (state.value.isLoading){
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color.Red)
            }
        }

        else{
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.value.stateListLaterMovies) { laterItem ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clickable {
                                // go  to detilPage
                                val movieObject = Gson().toJson(laterItem)
                                val encodedMovieObject =
                                    URLEncoder.encode(movieObject, "UTF-8")
                                navController.navigate(Screen.DetailPage.route + "/$encodedMovieObject")
                            },

                        ) {

                        val backdropPath = laterItem.original_title
                        val imageUrl = "${Constans.BASE_IMAGE_URL}$backdropPath"

                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {

                                Row {
                                    Icon(
                                        tint = Color.Yellow,
                                        painter = painterResource(id = R.drawable.calendar),
                                        contentDescription = "Favorite",
                                        modifier = Modifier.size(24.dp)
                                    )
                                    Text(
                                        text = laterItem.backdrop_path,
                                        fontSize = 16.sp,
                                        modifier = Modifier.padding(end = 8.dp)
                                    )

                                }
                                Text(
                                    text = laterItem.poster_path,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(end = 8.dp)
                                )
                                Icon(
                                    tint = Color.Red,
                                    painter = painterResource(id = R.drawable.delete),
                                    contentDescription = "Favorite",
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clickable {
                                            val laterMovies = LaterMovies(
                                                laterMoviesId = laterItem.id.toString(),
                                                backdrop_path = laterItem.backdrop_path ?: "",
                                                genre_ids = laterItem.genre_ids ?: listOf(),
                                                id = laterItem.id,
                                                original_language = laterItem.original_language,
                                                original_title = laterItem.original_title,
                                                overview = laterItem.overview,
                                                popularity = laterItem.popularity,
                                                poster_path = laterItem.poster_path ?: "",
                                                release_date = laterItem.release_date,
                                                title = laterItem.title,
                                                vote_average = laterItem.vote_average
                                            )
                                            viewModel.deleteLaterMovies(laterMovies)

                                        }
                                )


                                Row {
                                    Icon(
                                        tint = Color.Yellow,
                                        painter = painterResource(id = R.drawable.star),
                                        contentDescription = "Favorite",
                                        modifier = Modifier.size(24.dp)
                                    )
                                    Text(
                                        text = laterItem.vote_average.toString(),
                                        fontSize = 16.sp,
                                        modifier = Modifier.padding(end = 8.dp)
                                    )
                                }
                            }
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentScale = ContentScale.Crop,
                                    painter = rememberAsyncImagePainter(model = imageUrl),
                                    contentDescription = laterItem.overview
                                )
                                Icon(
                                    tint = Color.White,
                                    modifier = Modifier.size(50.dp),
                                    painter = painterResource(id = R.drawable.play), contentDescription = "")
                            }
                        }
                    }
                }
            }
        }

    }

}