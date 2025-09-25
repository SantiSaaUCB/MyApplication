package com.myapplication.app.features.github.domain.usecase

import com.myapplication.app.features.github.domain.model.GithubUserModel
import com.myapplication.app.features.github.domain.repository.IGithubRepository
import kotlinx.coroutines.delay

class GithubFindByNickNameUseCase(
    val repository: IGithubRepository
) {
    suspend fun invoke(nickname: String) : Result<GithubUserModel> {
        delay(2000)
        return repository.findByNickName(nickname)
    }
}