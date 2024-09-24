package com.example.firestoremovieapp.presentation.person_view

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.firestoremovieapp.domain.use_case.log_out_use_case.LogOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class PersonViewModel @Inject constructor(private val logOutUseCase: LogOutUseCase):ViewModel() {

    fun logOut(){
        logOutUseCase.logOut()
        Log.e("logOut","success logOut")
    }

}