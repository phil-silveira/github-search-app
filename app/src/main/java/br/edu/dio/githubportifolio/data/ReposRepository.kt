package br.edu.dio.githubportifolio.data

import br.edu.dio.githubportifolio.domain.Repository
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path


object ReposRepository {
    private val dataSource = GithubReposDataSource
    fun listReposByUsername(username: String) = dataSource.listReposByUsername(username)
}

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

interface GithubReposService {
    @GET("/users/{user}/repos")
    fun listReposByUsername(@Path("user") user: String): Call<List<Repository>>
}

// https://api.github.com/users/phil/repos