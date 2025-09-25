package com.myapplication.app.features.dollar.data.datasource

import com.myapplication.app.features.dollar.data.database.dao.IDollarDao
import com.myapplication.app.features.dollar.data.mapper.toEntity
import com.myapplication.app.features.dollar.data.mapper.toModel
import com.myapplication.app.features.dollar.domain.model.DollarModel

class DollarLocalDataSource(
    val dao: IDollarDao
) {
    suspend fun getList(): List<DollarModel> = dao.getList().map { it.toModel() }

    suspend fun getLast(): DollarModel? = dao.getLast()?.toModel()

    suspend fun deleteAll() { dao.deleteAll() }

    suspend fun inserTDollars(list: List<DollarModel>) { dao.insertDollars(list.map { it.toEntity() }) }

    suspend fun insert(dollar: DollarModel) { dao.insert(dollar.toEntity()) }
}
