package com.ugdgomezdiez.tresenraya.feature.tresenraya.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugdgomezdiez.tresenraya.app.domain.right
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.CleanBoardUseCase
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.GetGameUseCase
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.GetTurnUseCase
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.Piece
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.SetGameTurnUseCase
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.Turn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameViewModel(private val setGameUseCase: SetGameTurnUseCase,
    private val getGameUseCase: GetGameUseCase,
    private val getTurnUseCase: GetTurnUseCase,
    private val cleanBoardUseCase: CleanBoardUseCase
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

    fun getTurn(){
        _uistate.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
             getTurnUseCase.invoke().fold(
                {responseError(it)},
                {responseTurn(it)}
            )
        }
    }

    fun cleanBoard(){
        _uistate.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            cleanBoardUseCase.invoke()
        }
    }

    private fun responseError(error: Error) =
        _uistate.postValue(UiState(error = error))

    private fun responseSucces(succes: Boolean){
        _uistate.postValue(UiState(succes = succes))
    }

    private fun responseBoard(board: Array<Array<Piece>>){
        _uistate.postValue(UiState(board = board))
    }

    private fun responseTurn(turn: Turn){
        _uistate.postValue(UiState(turn = turn))
    }

    data class UiState(
        val isLoading: Boolean = false,
        val error: Error? = null,
        val succes: Boolean? = null,
        val turn: Turn? = null,
        val board: Array<Array<Piece>> = piecesArray

    )

}
var cont = 0
val piecesArray = Array(3) { i ->
    Array(3) { j ->
        cont += 1
        Piece(cont,i, j, 0)
    }
}
