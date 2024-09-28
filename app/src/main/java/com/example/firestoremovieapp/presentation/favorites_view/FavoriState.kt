package com.example.firestoremovieapp.presentation.favorites_view

import com.example.firestoremovieapp.domain.model.FavoriModel

data class FavoriState(
    val stateListFavori:List<FavoriModel> = emptyList(),
    val isError:String="",
    val isLoading:Boolean=false
)
