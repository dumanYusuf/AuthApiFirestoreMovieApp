package com.example.firestoremovieapp.presentation.home_view

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firestoremovieapp.domain.use_case.category_movies_use_case.CategoryMoviesUseCase
import com.example.firestoremovieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class HomePageViewModel @Inject constructor(private val useCase: CategoryMoviesUseCase): ViewModel(){



    private val _state= mutableStateOf<HomeState>(HomeState())
    val state: State<HomeState> =_state

    private var job: Job?=null


    fun getCategoryMovies(){
        Log.e("start","viewModellStart")
        job?.cancel()
        job=useCase.getCategory().onEach {
            when(it){
                is Resource.Success->{
                    _state.value=HomeState(categoryMoviesList = it.data ?: emptyList())
                    Log.e("sucsess","Data Found:${it.data}")
                }
                is Resource.Error->{
                    _state.value=HomeState(errorMessage = it.message ?:"")
                    Log.e("Error","Error Not Found:${it.message}")
                }
                is Resource.Loading->{
                    _state.value=HomeState(isLoading = true)
                    Log.e("Loaading","data is loadind")
                }
            }
        }.launchIn(viewModelScope)
    }



}