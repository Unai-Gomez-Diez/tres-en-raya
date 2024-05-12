package com.ugdgomezdiez.tresenraya.feature.tresenraya.domain

import com.ugdgomezdiez.tresenraya.app.domain.Either
import com.ugdgomezdiez.tresenraya.app.domain.left
import com.ugdgomezdiez.tresenraya.app.domain.right
import java.lang.Error

class GetGameUseCase(private val gameRepository: GameRepository) {
    operator fun invoke(): Either<Error,Array<Array<Piece?>>>{
        return gameRepository.getGame().right()
    }
}