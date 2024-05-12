package com.ugdgomezdiez.tresenraya.feature.tresenraya.domain

import com.ugdgomezdiez.tresenraya.app.domain.Either
import com.ugdgomezdiez.tresenraya.app.domain.left
import com.ugdgomezdiez.tresenraya.app.domain.right

class SetGameTurnUseCase(private val gameRepository: GameRepository) {
    operator fun invoke(turn: Piece): Either<Error, Boolean>{
        return gameRepository.setGameTurn(turn).right()
    }
}