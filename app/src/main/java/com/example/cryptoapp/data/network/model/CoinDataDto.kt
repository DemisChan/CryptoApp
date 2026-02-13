package com.example.cryptoapp.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinDataDto (
    @SerializedName("CoinInfo")
    @Expose
    val coinInfo: CoinNameDto? = null
)
