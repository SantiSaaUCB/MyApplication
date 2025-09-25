package com.myapplication.app.features.dollar.data.mapper

import com.myapplication.app.features.dollar.data.database.entity.DollarEntity
import com.myapplication.app.features.dollar.domain.model.DollarModel

fun DollarEntity.toModel() : DollarModel {
    return DollarModel(
        dollarOfficial = dollarOfficial,
        dollarParallel = dollarParallel
    )
}

fun DollarModel.toEntity() : DollarEntity {
    return DollarEntity(
        dollarOfficial = dollarOfficial,
        dollarParallel = dollarParallel)
}
