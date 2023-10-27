package br.edu.dio.githubportifolio.data


object ReposRepository {
    private val dataSource = GithubReposDataSource
    fun listReposByUsername(username: String) = dataSource.listReposByUsername(username)
}
