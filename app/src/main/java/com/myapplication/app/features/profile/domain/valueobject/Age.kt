package com.myapplication.app.features.profile.domain.valueobject

data class Age(val value: Int) {
    init {
        require(value in 0..120)
    }
}
