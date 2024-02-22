package ru.geekbrains.mtmdb.model.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.geekbrains.mtmdb.model.entities.Credits

interface CreditsAPI {
    @GET("3/movie/{movie_id}/credits")
    fun getCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") key: String,
        @Query("language") lang: String
    ): Call<Credits>
}