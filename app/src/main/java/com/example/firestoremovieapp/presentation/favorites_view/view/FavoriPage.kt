package com.example.firestoremovieapp.presentation.favorites_view.view

import androidx.compose.foundation.Image
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
import coil.compose.rememberAsyncImagePainter
import com.example.firestoremovieapp.R
import com.example.firestoremovieapp.presentation.favorites_view.FavoriViewModel
import com.example.firestoremovieapp.util.Constans

@Composable
fun FavoriPage(
    modifier: Modifier = Modifier,
    viewModel: FavoriViewModel = hiltViewModel()
) {
    val state = viewModel.stateFavori.collectAsState()

    LaunchedEffect(true) {
        viewModel.getFavori()
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
            text = "Favori Movies")
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
                items(state.value.stateListFavori) { favoriItem ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),

                        ) {

                        val backdropPath = favoriItem.original_title
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
                                        text = favoriItem.backdrop_path,
                                        fontSize = 16.sp,
                                        modifier = Modifier.padding(end = 8.dp)
                                    )

                                }
                                Text(
                                    text = favoriItem.poster_path,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(end = 8.dp)
                                )

                                Row {
                                    Icon(
                                        tint = Color.Yellow,
                                        painter = painterResource(id = R.drawable.star),
                                        contentDescription = "Favorite",
                                        modifier = Modifier.size(24.dp)
                                    )
                                    Text(
                                        text = favoriItem.vote_average.toString(),
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
                                    contentDescription = favoriItem.overview
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
