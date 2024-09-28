package com.example.firestoremovieapp.presentation.watch_later_view

import com.example.firestoremovieapp.domain.model.FavoriModel
import com.example.firestoremovieapp.domain.model.LaterMovies

data class WatchLaterState(
    val stateListLaterMovies:List<LaterMovies> = emptyList(),
    val isError:String="",
    val isLoading:Boolean=false
)
