package com.example.firestoremovieapp.presentation.register_view

import UserModel
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firestoremovieapp.domain.use_case.register_use_case.RegisterUseCase
import com.example.firestoremovieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerUseCase: RegisterUseCase):ViewModel() {


    private val _state= MutableStateFlow<RegisterState>(RegisterState())
    val state:StateFlow<RegisterState> = _state

    fun register(userModel: UserModel,password:String){
        viewModelScope.launch {
           val result =registerUseCase.register(userModel,password)
            when(result){
                is Resource.Success->{
                    _state.value= RegisterState(isSucsess = true)
                    Log.e("succeess","user adeddd firestore")
                }
                is Resource.Error->{
                    _state.value=RegisterState(isError = "Error ViewMode:${result.message}")
                    Log.e("failure","user error firestore:${result.message}")
                }
                is Resource.Loading->{
                    _state.value=RegisterState(isLoading = true)
                }
            }
        }
    }


}