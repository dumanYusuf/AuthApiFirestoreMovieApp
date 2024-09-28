package com.example.firestoremovieapp.presentation.person_view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firestoremovieapp.domain.use_case.get_user_use_case.GetUserUseCase
import com.example.firestoremovieapp.domain.use_case.log_out_use_case.LogOutUseCase
import com.example.firestoremovieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PersonViewModel @Inject constructor(
    private val logOutUseCase: LogOutUseCase,
    private val getUserUseCase: GetUserUseCase
):ViewModel() {


    private val _state= MutableStateFlow<PersonState>(PersonState())
    val state:StateFlow<PersonState> = _state

    fun getUser()=viewModelScope.launch {
        _state.value=PersonState(isLoading = true)
        val result=getUserUseCase.getUser()
        when(result){
            is Resource.Success->{
                _state.value=PersonState(isSucsess = result.data?: emptyList())
                Log.e("success","viewModel succseess getUser:${result.data}")
            }
            is Resource.Error->{
                _state.value=PersonState(isError = "Eror:${result.message}")
                Log.e("failure","viewModel not user getUser:${result.message}")
            }
            is Resource.Loading->{
                _state.value=PersonState(isLoading = true)
            }
        }
    }




    fun logOut(){
        logOutUseCase.logOut()
        Log.e("logOut","success logOut")
    }

}