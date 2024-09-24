package com.example.firestoremovieapp.presentation.login_view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firestoremovieapp.domain.use_case.login_use_case.LoginUseCase
import com.example.firestoremovieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase):ViewModel() {


    private val _state= MutableStateFlow<LoginState>(LoginState())
    val state: StateFlow<LoginState> = _state

    fun login(email:String,password:String){
        viewModelScope.launch {
            val result =loginUseCase.invoke(email,password)
            when(result){
                is Resource.Success->{
                    _state.value= LoginState(isSucsess = true)
                    Log.e("succeess","user login ")
                }
                is Resource.Error->{
                    _state.value= LoginState(isError = "Error ViewMode:${result.message}")
                    Log.e("failure","user not login firestore:${result.message}")
                }
                is Resource.Loading->{
                    _state.value= LoginState(isLoading = true)
                }
            }
        }
    }


}