package com.fevziomurtekin.kotlin_coroutines_example.Model.Repos

import com.google.gson.annotations.SerializedName

data class Permissions(
    @SerializedName("admin")
    val admin: Boolean,
    @SerializedName("pull")
    val pull: Boolean,
    @SerializedName("push")
    val push: Boolean
)