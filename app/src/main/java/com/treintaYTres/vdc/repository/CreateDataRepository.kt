package com.treintaYTres.vdc.repository

import com.treintaYTres.vdc.network.model.response.group.CreateDataResponse

interface CreateDataRepository {
    suspend fun getCreateData(): CreateDataResponse?
}