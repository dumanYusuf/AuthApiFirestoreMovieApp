package com.example.firestoremovieapp.presentation.home_view.view


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.firestoremovieapp.R
import com.example.firestoremovieapp.Screen
import com.example.firestoremovieapp.domain.model.FavoriModel
import com.example.firestoremovieapp.domain.model.LaterMovies
import com.example.firestoremovieapp.presentation.home_view.HomePageViewModel
import com.example.firestoremovieapp.util.Constans
import com.google.gson.Gson
import java.net.URLEncoder

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    viewModel: HomePageViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.collectAsState().value
    val statePopuler = viewModel.statePopuler.collectAsState().value


    Column(modifier.fillMaxSize()) {
        Row (modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
            Text(
                modifier = Modifier.padding(10.dp),
                fontSize = 24.sp,
                text = "Movies"
            )
            Icon(
                modifier = Modifier.size(50.dp).padding(5.dp),
                painter = painterResource(id = R.drawable.search) , contentDescription ="" )
        }

        // Category Movies
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            if (state.categoryMoviesList.isNotEmpty()) {
                LazyRow(modifier.padding(10.dp)) {
                    items(state.categoryMoviesList) { categoryMovie ->
                        Button(
                            modifier = Modifier.padding(5.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                            onClick = {
                                // go to categoryFilterPage
                                val movieObject = Gson().toJson(categoryMovie)
                                val encodedMovieObject =
                                    URLEncoder.encode(movieObject, "UTF-8")
                                navController.navigate(Screen.FilterMoviesPage.route+"/$encodedMovieObject")
                            }) {
                            Text(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                text = categoryMovie.name
                            )
                        }
                    }
                }
            } else if (state.errorMessage.isNotBlank()) {
                Text(
                    text = state.errorMessage,
                    color = Color.Red,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        // Popüler Movies
        if (statePopuler.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            if (statePopuler.populerMoviesList.isNotEmpty()) {
                Text(
                    modifier = Modifier.padding(horizontal = 15.dp),
                    fontSize = 20.sp,
                    text = "Watch Movies"
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    items(statePopuler.populerMoviesList) { populerMovie ->
                        Card(
                            modifier = Modifier
                                .size(250.dp)
                                .padding(5.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(5.dp)
                            ) {
                                val backdropPath = populerMovie.original_language
                                val imageUrl = "${Constans.BASE_IMAGE_URL}$backdropPath"

                                Box(modifier = Modifier.fillMaxWidth()) {
                                    Image(
                                        modifier = Modifier
                                            .size(250.dp)
                                            .clickable {
                                                // go to detail movies
                                                 val movieObject = Gson().toJson(populerMovie)
                                                val encodedMovieObject =
                                                    URLEncoder.encode(movieObject, "UTF-8")
                                               navController.navigate(Screen.DetailPage.route+"/$encodedMovieObject")
                                            },
                                        painter = rememberAsyncImagePainter(model = imageUrl),
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
                                                    painter = painterResource(id = R.drawable.star),
                                                    contentDescription = "Vote Average"
                                                )
                                                Text(
                                                    color = Color.White,
                                                    text = populerMovie.vote_average.toString()
                                                )
                                            }
                                            Row {
                                                Icon(
                                                   modifier = Modifier.clickable {
                                                       val favoriModel = FavoriModel(
                                                           favoriId =populerMovie.id.toString(),
                                                           backdrop_path = populerMovie.backdrop_path ,
                                                           genre_ids = populerMovie.genre_ids ,
                                                           id = populerMovie.id,
                                                           original_language = populerMovie.original_language,
                                                           original_title = populerMovie.original_title,
                                                           overview = populerMovie.overview,
                                                           popularity = populerMovie.popularity,
                                                           poster_path = populerMovie.poster_path ,
                                                           release_date = populerMovie.release_date,
                                                           title = populerMovie.title,
                                                           vote_average = populerMovie.vote_average
                                                       )
                                                       viewModel.addFavori(favoriModel = favoriModel)
                                                   },
                                                    tint = Color.Red,
                                                    painter = painterResource(id = R.drawable.favori),
                                                    contentDescription = ""
                                                )
                                            }
                                        }
                                        Spacer(modifier = Modifier.padding(top = 10.dp))
                                        Column(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(bottom = 10.dp, start = 10.dp),
                                            verticalArrangement = Arrangement.Bottom,
                                            horizontalAlignment = Alignment.Start
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Icon(
                                                    modifier = Modifier.clickable {
                                                        val laterMovie = LaterMovies(
                                                            laterMoviesId = populerMovie.id.toString(),
                                                            backdrop_path = populerMovie.backdrop_path ,
                                                            genre_ids = populerMovie.genre_ids ,
                                                            id = populerMovie.id,
                                                            original_language = populerMovie.original_language,
                                                            original_title = populerMovie.original_title,
                                                            overview = populerMovie.overview,
                                                            popularity = populerMovie.popularity,
                                                            poster_path = populerMovie.poster_path ?: "",
                                                            release_date = populerMovie.release_date,
                                                            title = populerMovie.title,
                                                            vote_average = populerMovie.vote_average
                                                        )
                                                        viewModel.addLaterMovies(laterMovie)
                                                    },
                                                    tint = Color.Red,
                                                    painter = painterResource(id = R.drawable.later),
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
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
