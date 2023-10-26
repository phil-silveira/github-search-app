package br.edu.dio.githubportifolio.domain

import com.google.gson.annotations.SerializedName

data class Repository(
    val name: String,
    @SerializedName("html_url")
    val htmlUrl: String
)
