package br.edu.dio.githubportifolio.domain

import com.google.gson.annotations.SerializedName

data class Repository(
    @SerializedName("name")
    val title: String,

    val description: String?,

    @SerializedName("stargazers_count")
    val numberOfStars: Int,

    @SerializedName("html_url")
    val htmlUrl: String,

    val owner: Owner
)
