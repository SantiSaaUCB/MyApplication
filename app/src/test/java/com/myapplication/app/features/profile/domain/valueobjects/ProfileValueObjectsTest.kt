package com.myapplication.app.features.profile.domain.valueobject

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class ProfileValueObjectsTest {

    @Test
    fun fullNameValidAndInvalid() {
        val ok = FullName("John Smith")
        assertEquals("John Smith", ok.value)
        assertThrows(IllegalArgumentException::class.java) { FullName(" ") }
    }

    @Test
    fun ageValidAndInvalid() {
        val ok = Age(30)
        assertEquals(30, ok.value)
        assertThrows(IllegalArgumentException::class.java) { Age(-1) }
    }

    @Test
    fun phoneValidAndInvalid() {
        val ok = PhoneNumber("+591 71234567")
        assertEquals("+591 71234567", ok.value)
        assertThrows(IllegalArgumentException::class.java) { PhoneNumber("abc") }
    }
}
