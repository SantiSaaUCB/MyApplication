package com.myapplication.app.features.dollar.domain.usecase

import com.myapplication.app.features.dollar.domain.model.DollarModel
import com.myapplication.app.features.dollar.domain.repository.IDollarRepository
import kotlinx.coroutines.flow.Flow

class GetDollarUseCase(
    val repository: IDollarRepository
) {

    suspend fun invoke(): Flow<DollarModel> {
        return repository.getDollar()
    }
}