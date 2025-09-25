package com.myapplication.app.features.dollar.domain.repository

import com.myapplication.app.features.dollar.domain.model.DollarModel
import kotlinx.coroutines.flow.Flow

interface IDollarRepository {
    suspend fun getDollar(): Flow<DollarModel>
}