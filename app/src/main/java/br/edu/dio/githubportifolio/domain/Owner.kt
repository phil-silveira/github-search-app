package br.edu.dio.githubportifolio.domain

import com.google.gson.annotations.SerializedName


data class Owner(
    @SerializedName("avatar_url")
    val avatarUrl: String,

    @SerializedName("login")
    val username: String
)
