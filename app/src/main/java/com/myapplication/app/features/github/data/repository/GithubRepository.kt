package com.myapplication.app.features.github.data.repository

import com.myapplication.app.features.github.data.datasource.GithubRemoteDataSource
import com.myapplication.app.features.github.domain.model.GithubUserModel
import com.myapplication.app.features.github.domain.repository.IGithubRepository
import java.lang.Exception

class GithubRepository(
    private val remoteDataSource: GithubRemoteDataSource
) : IGithubRepository {
    override suspend fun findByNickName(value: String): Result<GithubUserModel> {
        if (value.isEmpty()) {
            return Result.failure(Exception("The field cannot be empty"))
        }

        val response = remoteDataSource.getGithubUser(value)
        response.fold(
            onSuccess = { userDto ->
                return Result.success(
                    GithubUserModel(
                        nickname = userDto.login,
                        pathUrl = userDto.url
                    )
                )
            },
            onFailure = { throwable ->
                return Result.failure(throwable)
            }
        )
    }
}
