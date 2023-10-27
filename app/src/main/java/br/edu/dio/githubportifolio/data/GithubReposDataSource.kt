package br.edu.dio.githubportifolio.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


object GithubReposDataSource {
    private const val BASE_URL = "https://api.github.com"

    private val service: GithubReposService  by lazy {
        Retrofit.Builder()
            .baseUrl(GithubReposDataSource.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create()
    }

    fun listReposByUsername(username: String) = service.listReposByUsername(username)
}

