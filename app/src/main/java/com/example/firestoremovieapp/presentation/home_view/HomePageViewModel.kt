package com.example.firestoremovieapp.presentation.home_view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firestoremovieapp.domain.use_case.category_movies_use_case.CategoryMoviesUseCase
import com.example.firestoremovieapp.domain.use_case.populer_movies_use_case.PopulerMoviesUseCase
import com.example.firestoremovieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val categoryUseCase: CategoryMoviesUseCase,
    private val populerMoviesUseCase: PopulerMoviesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<HomeState>(HomeState())
    val state: StateFlow<HomeState> = _state

    private val _statePopuler = MutableStateFlow<HomePopulerState>(HomePopulerState())
    val statePopuler: StateFlow<HomePopulerState> = _statePopuler


    private var categoryJob: Job? = null
    private var populerJob: Job? = null
    private var topRatedJob: Job? = null
    private var upComingJob: Job? = null

    init {
       getCategoryMovies()
        getPopulerMovies()
    }

    fun getCategoryMovies() {
        Log.e("start", "viewModelStart")
        categoryJob?.cancel()
        categoryJob = categoryUseCase.getCategory().onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = HomeState(categoryMoviesList = it.data ?: emptyList())
                    Log.e("success", "Data Found: ${it.data}")
                }
                is Resource.Error -> {
                    _state.value = HomeState(errorMessage = it.message ?: "")
                    Log.e("Error", "Error Not Found: ${it.message}")
                }
                is Resource.Loading -> {
                    _state.value = HomeState(isLoading = true)
                    Log.e("Loading", "Data is loading")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getPopulerMovies() {
        Log.e("start", "viewModelPopulerStart")
        populerJob?.cancel()
        populerJob = populerMoviesUseCase.getPopulerMovies().onEach {
            when (it) {
                is Resource.Success -> {
                    _statePopuler.value = HomePopulerState(populerMoviesList = it.data ?: emptyList())
                    Log.e("success", "Data Found: ${it.data}")
                }
                is Resource.Error -> {
                    _statePopuler.value = HomePopulerState(errorMessage = "ERROR")
                    Log.e("Error", "Error Not Found: ${it.message}")
                }
                is Resource.Loading -> {
                    _statePopuler.value = HomePopulerState(isLoading = true)
                    Log.e("Loading", "Data is loading")
                }
            }
        }.launchIn(viewModelScope)
    }
}
