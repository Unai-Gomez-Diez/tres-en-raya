package com.ugdgomezdiez.tresenraya.feature.tresenraya.domain

import com.ugdgomezdiez.tresenraya.app.domain.Either
import com.ugdgomezdiez.tresenraya.app.domain.right

class GetTurnUseCase(val gameRepository: GameRepository) {
    operator fun invoke(): Either<Error, Int>{
        return gameRepository.getTurn().right()
    }
}