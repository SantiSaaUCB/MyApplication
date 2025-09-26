package com.myapplication.app.features.profile.domain.usecase

import com.myapplication.app.features.profile.domain.model.ProfileModel
import com.myapplication.app.features.profile.domain.repository.IProfileRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

private class FakeProfileRepository(private val value: ProfileModel) : IProfileRepository {
    override suspend fun getProfile(): ProfileModel = value
}

class GetUserUseCaseTest {

    @Test
    fun returnsProfileFromRepository() = runTest {
        val expected = ProfileModel(
            fullName = "Test User",
            age = 30,
            idCard = "XYZ999",
            gender = "OTHER",
            phoneNumber = "71234567",
            university = "UMSS",
            maritalStatus = "SINGLE"
        )
        val useCase = GetUserUseCase(FakeProfileRepository(expected))
        val result = useCase.invoke()
        assertEquals(expected, result)
    }
}
