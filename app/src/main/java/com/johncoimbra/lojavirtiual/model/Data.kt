package com.johncoimbra.lojavirtiual.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Data(
    val uid: String = "",
    val nome: String = "",
    val preco: String = "",
    val url: String = ""):Parcelable