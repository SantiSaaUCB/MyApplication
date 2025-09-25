package com.myapplication.app.features.profile.domain.repository

import com.myapplication.app.features.profile.domain.model.ProfileModel

interface IProfileRepository {
    suspend fun getProfile(): ProfileModel
}
