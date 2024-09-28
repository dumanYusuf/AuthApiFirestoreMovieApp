package com.example.firestoremovieapp.presentation.favorites_view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firestoremovieapp.domain.model.FavoriModel
import com.example.firestoremovieapp.domain.use_case.favori_add_firestore_use_case.FavoriAddFirestoreUseCase
import com.example.firestoremovieapp.domain.use_case.favori_delete_firestore_use_case.FavoriDeleteUseCase
import com.example.firestoremovieapp.domain.use_case.favori_get_firestore_use_case.GetFavoriUseCase
import com.example.firestoremovieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriViewModel @Inject constructor(
    private val getFavoriuseCase: GetFavoriUseCase,
    private val deleteFavoriDeleteUseCase: FavoriDeleteUseCase
):ViewModel() {

    private val _stateFavori= MutableStateFlow<FavoriState>(FavoriState())
    val stateFavori:StateFlow<FavoriState> = _stateFavori



    fun getFavori(){
        Log.e("get favori","start get favori")
        viewModelScope.launch {
            _stateFavori.value=FavoriState(isLoading = true)
            getFavoriuseCase.getFavori().collect{result->
                when(result){
                    is Resource.Success->{
                        _stateFavori.value= FavoriState(stateListFavori = result.data?: emptyList())
                        Log.e("get notes","viewmodel success get notes")
                    }
                    is Resource.Error->{
                        _stateFavori.value=FavoriState(isError = result.message?:"Error")
                        Log.e("get notes","viewmodel failure get notes:${result.message}")
                    }
                    is Resource.Loading->{
                        _stateFavori.value=FavoriState(isLoading =true)
                        Log.e("get notes","viewmodel isloading get notes")
                    }
                }
            }
        }
    }

    fun deleteFavori(favoriId: FavoriModel) {
        viewModelScope.launch {
            val resultFavori = deleteFavoriDeleteUseCase.invoke(favoriId)

            when (resultFavori) {
                is Resource.Success -> {
                    _stateFavori.value = _stateFavori.value.copy(
                        stateListFavori = _stateFavori.value.stateListFavori.filter { it.id.toString() != favoriId.favoriId }
                    )
                    Log.e("FavoriViewModel", "Favori silindi: ${resultFavori.message}")
                }
                is Resource.Error -> {
                    Log.e("FavoriViewModel", "Favori silinemedi: ${resultFavori.message}")
                }
                is Resource.Loading -> {
                }
            }
        }
    }


}