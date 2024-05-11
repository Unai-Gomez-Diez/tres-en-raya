package com.ugdgomezdiez.tresenraya.feature.tresenraya.domain

class GetGameUseCase(private val gameRepository: GameRepository) {
    operator fun invoke(): Array<Array<Piece?>>{
        return gameRepository.getGame()
    }
}