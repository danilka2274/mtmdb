package ru.geekbrains.mtmdb.model

import ru.geekbrains.mtmdb.model.entities.Cast


sealed class CreditsState {
    data class Success(val creditsData: List<Cast>) : CreditsState()
    data class Error(val error: Throwable) : CreditsState()
}