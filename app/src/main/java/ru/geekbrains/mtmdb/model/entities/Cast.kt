package ru.geekbrains.mtmdb.model.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Cast(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    val known_for_department: String?,
    val name: String?,
    val original_name: String?,
    val popularity: Number?,
    val profile_path: String?,
    val cast_id: Int,
    val character: String?,
    val credit_id: String?,
    val order: Int
) : Parcelable