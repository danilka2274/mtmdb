package ru.geekbrains.mtmdb.model

import ru.geekbrains.mtmdb.model.entities.Movie

sealed class MovieState {
    data class Success(val moviesData: List<Movie>) : MovieState()
    data class Error(val error: Throwable) : MovieState()
    object Loading : MovieState()
}