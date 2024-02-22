package ru.geekbrains.mtmdb.model.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Credits(
    val id: Int,
    val cast: List<Cast>?
) : Parcelable