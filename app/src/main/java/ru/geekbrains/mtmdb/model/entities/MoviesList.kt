package ru.geekbrains.mtmdb.model.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviesList (
    val results: List<Movie>
): Parcelable