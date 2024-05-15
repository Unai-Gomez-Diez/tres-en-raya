package com.ugdgomezdiez.tresenraya.feature.tresenraya.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.ugdgomezdiez.tresenraya.databinding.FragmentGameBinding
import com.ugdgomezdiez.tresenraya.feature.tresenraya.data.GameDataRepository
import com.ugdgomezdiez.tresenraya.feature.tresenraya.data.xml.GameXmlLocalDataSource
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.CleanBoardUseCase
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.GetGameUseCase
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.GetTurnUseCase
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.Piece
import com.ugdgomezdiez.tresenraya.feature.tresenraya.domain.SetGameTurnUseCase
import com.ugdgomezdiez.tresenraya.feature.tresenraya.presentation.adapter.GameAdapter

class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    private val gameAdapter = GameAdapter()
    private val viewModel: GameViewModel by lazy {
        GameViewModel(
            SetGameTurnUseCase(
                GameDataRepository(
                    GameXmlLocalDataSource(
                        this.requireContext(), Gson()
                    )
                )
            ),
            GetGameUseCase(
                GameDataRepository(
                    GameXmlLocalDataSource(
                        this.requireContext(), Gson()
                    )
                )
            ),
            GetTurnUseCase(
                GameDataRepository(
                    GameXmlLocalDataSource(
                        this.requireContext(), Gson()
                    )
                )
            ),
            CleanBoardUseCase(
                GameDataRepository(
                    GameXmlLocalDataSource(
                        this.requireContext(), Gson()
                    )
                )
            )
        )
    }

    var boardActual: Array<Array<Piece>>  = piecesArray
    var boardWin: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        setupView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        viewModel.getBoard()

    }

    private fun setupView() {
        binding.apply {
            board.apply {
                layoutManager = GridLayoutManager(
                    requireContext(),
                    3,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                gameAdapter.setEvent {
                    viewModel.setPiece(it)
                    boardWin = winCondition(boardActual)
                    if (boardWin == true){
                        viewModel.cleanBoard()
                    }
                }
                adapter = gameAdapter
            }
        }
    }

    private fun setupObserver() {
        val observer = Observer<GameViewModel.UiState> {
            if (it.isLoading) {

            } else {
                if (it.error != null) {

                } else {
                    viewModel.getBoard()
                    val boardGame = it.board.flatten().toMutableList()
                    boardActual = it.board
                    gameAdapter.submitList(boardGame)

                }
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    private fun winCondition(board: Array<Array<Piece>>): Boolean{
        return if (board[0][0].selectPiece != null
            && (board[0][0].selectPiece == board[1][1].selectPiece
            && board[0][0].selectPiece == board[2][2].selectPiece
            || board[0][0].selectPiece == board[0][2].selectPiece
            && board[0][0].selectPiece == board[0][1].selectPiece
            || board[0][0].selectPiece == board[1][0].selectPiece
            && board[0][0].selectPiece == board[2][0].selectPiece
                    )){
            true
        }else if(board[1][1].selectPiece != null
            && (board[1][1].selectPiece == board[0][1].selectPiece
            && board[1][1].selectPiece == board[2][1].selectPiece
            || board[1][1].selectPiece == board[1][0].selectPiece
            && board[1][1].selectPiece == board[1][2].selectPiece
            || board[1][1].selectPiece == board[0][2].selectPiece
            && board[1][1].selectPiece == board[2][0].selectPiece
                    )){
            true
        }else if(board[2][2].selectPiece != null
            && (board[2][2].selectPiece == board[2][1].selectPiece
                    && board[2][2].selectPiece == board[2][0].selectPiece
                    || board[2][2].selectPiece == board[1][2].selectPiece
                    && board[2][2].selectPiece == board[0][2].selectPiece
                    )){
            true
        }else{
            false
        }
    }
}