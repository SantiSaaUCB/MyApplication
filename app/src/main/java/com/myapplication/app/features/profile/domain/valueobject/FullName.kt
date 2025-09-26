package com.myapplication.app.features.profile.domain.valueobject

data class FullName(val value: String) {
    init {
        require(value.isNotBlank())
        require(value.trim().length in 2..100)
    }
}
