package com.treintaYTres.vdc.usecase.groups

import com.treintaYTres.vdc.network.ApiService
import com.treintaYTres.vdc.network.model.response.group.CreateDataResponse
import com.treintaYTres.vdc.repository.CreateDataRepository
import javax.inject.Inject

class CreateDataRepositoryImpl @Inject constructor(
    private val api: ApiService
): CreateDataRepository {

    override suspend fun getCreateData(): CreateDataResponse? {
        return api.getCreateData().body()
    }

}