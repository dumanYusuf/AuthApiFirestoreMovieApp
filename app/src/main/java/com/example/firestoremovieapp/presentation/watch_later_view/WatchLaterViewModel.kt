package com.example.firestoremovieapp.presentation.watch_later_view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firestoremovieapp.domain.model.LaterMovies
import com.example.firestoremovieapp.domain.use_case.later_watches_movies_use_case.LaterWatchesAddFirestoreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WatchLaterViewModel @Inject constructor(private val lateruseCase: LaterWatchesAddFirestoreUseCase):
    ViewModel() {


        private val _state= MutableStateFlow<WatchLaterState>(WatchLaterState())
         val state:StateFlow<WatchLaterState> = _state


    fun addLaterMovies(laterMovies:LaterMovies){
        viewModelScope.launch {
            lateruseCase.addLaterMovies(laterMovies)
            Log.e("adedd later movies","success adedd later movies")
        }
    }


}