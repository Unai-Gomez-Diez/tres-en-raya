package com.ugdgomezdiez.tresenraya.feature.tresenraya.domain

import com.ugdgomezdiez.tresenraya.app.domain.Either
import com.ugdgomezdiez.tresenraya.app.domain.right

class SetGameTurnUseCase(private val gameRepository: GameRepository) {
    suspend operator fun invoke(piece: Piece): Either<Error, Boolean>{
        return gameRepository.setPiece(piece).right()
    }
}