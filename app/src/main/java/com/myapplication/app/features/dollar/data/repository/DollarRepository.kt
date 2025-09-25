package com.myapplication.app.features.dollar.data.repository

import com.myapplication.app.features.dollar.data.datasource.DollarLocalDataSource
import com.myapplication.app.features.dollar.data.datasource.DollarRealTimeDataSource
import com.myapplication.app.features.dollar.domain.model.DollarModel
import com.myapplication.app.features.dollar.domain.repository.IDollarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class DollarRepository(
    val realTimeRemoteDataSource: DollarRealTimeDataSource,
    val localDataSource: DollarLocalDataSource
): IDollarRepository {

    override suspend fun getDollar(): Flow<DollarModel> {
        return realTimeRemoteDataSource.getDollarUpdates()
            .onEach {
                localDataSource.insert(it)
            }

    }
}