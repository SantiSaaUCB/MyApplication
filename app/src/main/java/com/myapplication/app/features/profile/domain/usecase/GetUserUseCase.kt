package com.myapplication.app.features.profile.domain.usecase

import com.myapplication.app.features.profile.domain.model.ProfileModel
import com.myapplication.app.features.profile.domain.repository.IProfileRepository

class GetUserUseCase(
    private val repository: IProfileRepository
) {
    suspend operator fun invoke(): ProfileModel = repository.getProfile()
}
