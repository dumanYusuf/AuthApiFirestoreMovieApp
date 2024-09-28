package com.example.firestoremovieapp.presentation.filter_movies_view.views

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firestoremovieapp.domain.use_case.catogory_filter_movies_use_case.FilterMoviesUseCase
import com.example.firestoremovieapp.presentation.filter_movies_view.FilterState
import com.example.firestoremovieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FilterMoviesViewModel @Inject constructor(private val filterMoviesUseCase: FilterMoviesUseCase):ViewModel() {


    private val _stateFilter= MutableStateFlow<FilterState>(FilterState())
    val stateFilter:StateFlow<FilterState> =_stateFilter



    fun moviesFilter(category:Int){
        viewModelScope.launch {
            filterMoviesUseCase.filterMovies(category).collect{
                when(it){
                   is Resource.Success->{
                       _stateFilter.value=FilterState(categoryFiLTERMoviesList = it.data?: emptyList())
                       Log.e("success","data found filter:${it.data}")
                   }
                    is Resource.Error->{
                        _stateFilter.value=FilterState(errorMessage = "Error")
                        Log.e("Data not found","data not  found filter:${it.message}")
                    }
                    is Resource.Loading->{
                        _stateFilter.value=FilterState(isLoading = true)
                        Log.e("loading","data loading:")
                    }
                }
            }
        }
    }

}