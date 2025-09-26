package com.myapplication.app.features.profile.domain.model

import org.junit.Assert.assertEquals
import org.junit.Test

class ProfileModelTest {

    @Test
    fun createAndReadFields() {
        val model = ProfileModel(
            fullName = "Alice Doe",
            age = 22,
            idCard = "ID12345",
            gender = "FEMALE",
            phoneNumber = "70000000",
            university = "UMSS",
            maritalStatus = "SINGLE"
        )
        assertEquals("Alice Doe", model.fullName)
        assertEquals(22, model.age)
        assertEquals("ID12345", model.idCard)
        assertEquals("FEMALE", model.gender)
        assertEquals("70000000", model.phoneNumber)
        assertEquals("UMSS", model.university)
        assertEquals("SINGLE", model.maritalStatus)
    }
}
