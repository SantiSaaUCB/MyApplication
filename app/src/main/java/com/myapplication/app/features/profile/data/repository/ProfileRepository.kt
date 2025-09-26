package com.myapplication.app.features.profile.data.repository

import com.myapplication.app.features.profile.domain.model.ProfileModel
import com.myapplication.app.features.profile.domain.repository.IProfileRepository

class ProfileRepository : IProfileRepository {
    override suspend fun getProfile(): ProfileModel {
        return ProfileModel(
            fullName = "Santiago Saavedra",
            age = 21,
            idCard = "CB 9326030",
            gender = "Male",
            phoneNumber = "+591 69503355",
            university = "Universidad Catolica Boliviana",
            maritalStatus = "Married"
        )
    }
}
