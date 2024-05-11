package com.ugdgomezdiez.tresenraya.feature.tresenraya.domain

class SetGameTurnUseCase(private val gameRepository: GameRepository) {
    operator fun invoke(turn: Piece): Boolean{
        return gameRepository.setGameTurn(turn)
    }
}