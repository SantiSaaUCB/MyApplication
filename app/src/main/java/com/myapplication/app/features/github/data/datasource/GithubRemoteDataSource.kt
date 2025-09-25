package com.myapplication.app.features.github.data.datasource

import com.myapplication.app.features.github.data.api.GithubService
import com.myapplication.app.features.github.data.api.dto.GithubDto

class GithubRemoteDataSource(
    val githubService: GithubService
) {
    suspend fun getGithubUser(nickname: String): Result<GithubDto> {
        val response = githubService.getInfoAvatar(nickname)
        return if (response.isSuccessful) {
            Result.success(response.body()!!)
        } else {
            Result.failure(Exception("Failed to fetch user from GitHub"))
        }
    }
}