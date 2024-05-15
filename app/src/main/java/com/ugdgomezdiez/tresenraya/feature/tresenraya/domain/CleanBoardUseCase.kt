package com.ugdgomezdiez.tresenraya.feature.tresenraya.domain

class CleanBoardUseCase(private val gameRepository: GameRepository) {
    operator fun invoke(){
        gameRepository.cleanBoard()
    }
}