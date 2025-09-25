package com.myapplication.app.features.github.domain.repository

import com.myapplication.app.features.github.domain.model.GithubUserModel

interface IGithubRepository {
    suspend fun findByNickName(value: String): Result<GithubUserModel>
}