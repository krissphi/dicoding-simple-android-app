package com.kripix.dev.wislu

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Wisata(
    val name: String,
    val location: String,
    val map: String,
    val operational: String,
    val ticket: String,
    val description: String,
    val photo1: Int
) : Parcelable
