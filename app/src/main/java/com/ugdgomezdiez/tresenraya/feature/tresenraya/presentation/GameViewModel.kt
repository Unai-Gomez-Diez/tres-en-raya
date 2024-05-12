package com.ugdgomezdiez.tresenraya.feature.tresenraya.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.GetGameUseCase
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.Piece
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.SetGameTurnUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameViewModel(private val setGameUseCase: SetGameTurnUseCase,
    private val getGameUseCase: GetGameUseCase
): ViewModel() {
    private val _uistate = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uistate

    fun setPiece(piece: Piece){
        _uistate.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO){
            setGameUseCase.invoke(piece).fold(
                {responseError(it)},
                {responseSucces(it)}
            )
        }
    }

    fun getBoard(){
        _uistate.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            getGameUseCase.invoke().fold(
                {responseError(it)},
                {responseBoard(it)}
            )
        }
    }

    private fun responseError(error: Error) =
        _uistate.postValue(UiState(error = error))

    private fun responseSucces(succes: Boolean){
        _uistate.postValue(UiState(succes = succes))
    }

    private fun responseBoard(board: Array<Array<Piece?>>){
        _uistate.postValue(UiState(board = board))
    }

    data class UiState(
        val isLoading: Boolean = false,
        val error: Error? = null,
        val succes: Boolean? = null,
        val board: Array<Array<Piece?>> = arrayOf(
            arrayOf(null, null, null),
            arrayOf(null, null, null),
            arrayOf(null, null, null))
    )
}