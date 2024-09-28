package com.example.firestoremovieapp.presentation.watch_later_view


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firestoremovieapp.domain.use_case.later_watches_ger_firestore_use_case.LaterWatchesGetMoviesUseCase
import com.example.firestoremovieapp.presentation.favorites_view.FavoriState
import com.example.firestoremovieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WatchLaterViewModel @Inject constructor(
   private val getLaterWatchesYUseCase: LaterWatchesGetMoviesUseCase
):
    ViewModel() {


        private val _stateLater= MutableStateFlow<WatchLaterState>(WatchLaterState())
         val stateLater:StateFlow<WatchLaterState> = _stateLater


    fun getFavori(){
        Log.e("get favori","start get favori")
        viewModelScope.launch {
            _stateLater.value= WatchLaterState(isLoading = true)
            getLaterWatchesYUseCase.getLaterMovies().collect{result->
                when(result){
                    is Resource.Success->{
                        _stateLater.value= WatchLaterState(stateListLaterMovies = result.data?: emptyList())
                        Log.e("get notes","viewmodel success get notes")
                    }
                    is Resource.Error->{
                        _stateLater.value= WatchLaterState(isError = result.message?:"Error")
                        Log.e("get notes","viewmodel failure get notes:${result.message}")
                    }
                    is Resource.Loading->{
                        _stateLater.value= WatchLaterState(isLoading =true)
                        Log.e("get notes","viewmodel isloading get notes")
                    }
                }
            }
        }
    }



}