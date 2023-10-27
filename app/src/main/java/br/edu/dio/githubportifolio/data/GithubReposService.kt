package br.edu.dio.githubportifolio.data

import br.edu.dio.githubportifolio.domain.Repository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path



interface GithubReposService {
    @GET("/users/{user}/repos")
    // https://api.github.com/users/phil/repos
    fun listReposByUsername(@Path("user") user: String): Call<List<Repository>>
}

