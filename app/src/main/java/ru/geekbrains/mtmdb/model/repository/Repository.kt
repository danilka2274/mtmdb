package ru.geekbrains.mtmdb.model.repository

import ru.geekbrains.mtmdb.model.entities.Credits
import ru.geekbrains.mtmdb.model.entities.History
import ru.geekbrains.mtmdb.model.entities.Movie
import ru.geekbrains.mtmdb.model.entities.MoviesList
import ru.geekbrains.mtmdb.model.entities.Person
import retrofit2.Callback

interface Repository {
    fun getNewMoviesListFromServer(
        callback: retrofit2.Callback<MoviesList>
    )

    fun getMovieDetailsFromServer(
        id: Int,
        callback: retrofit2.Callback<Movie>
    )

    fun saveToHistory(history: History)

    fun getAllHistory(): List<History>
    fun getCreditsFromServer(id: Int, callback: Callback<Credits>)
    fun getPersonFromServer(id: Int, callback: Callback<Person>)
}