package com.treintaYTres.vdc.usecase.user

import android.content.SharedPreferences
import com.google.gson.Gson
import com.treintaYTres.vdc.network.ApiService
import com.treintaYTres.vdc.network.model.request.CompleteRegistryRequest
import com.treintaYTres.vdc.network.model.request.EmailUserRequest
import com.treintaYTres.vdc.network.model.request.InstrumentsUserRequest
import com.treintaYTres.vdc.network.model.request.PermissionsUserRequest
import com.treintaYTres.vdc.network.model.response.member.MemberResponse
import com.treintaYTres.vdc.repository.UserRepository
import com.treintaYTres.vdc.util.getUserId
import com.treintaYTres.vdc.util.saveUserId
import com.treintaYTres.vdc.util.toProfileUrl
import com.treintaYTres.vdc.util.updatePermissions
import com.treintaYTres.vdc.util.updateProfileUrl
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.net.URLConnection
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val gson: Gson,
    private val sharedPreferences: SharedPreferences
) : UserRepository {
    override suspend fun getUserData(id: Int): MemberResponse? {
        return api.getUserData(id).body()
    }

    override suspend fun createUser(email: String): Int {
        val id = api.createUser(EmailUserRequest(email)).body()
        if (id != null) {
            sharedPreferences.saveUserId(id)
            return id
        }
        return -1
    }

    override suspend fun completeRegistry(
        image: File,
        data: CompleteRegistryRequest
    ): String? {
        val extension = URLConnection.guessContentTypeFromName(image.name)
        val requestFile = image.asRequestBody(extension.toMediaTypeOrNull())

        val json = gson.toJson(data)
        val id = sharedPreferences.getUserId()

        val result = api.completeRegistry(
            id,
            MultipartBody.Part.createFormData("image", image.name, requestFile),
            json.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        ).body()

        sharedPreferences.updateProfileUrl(result ?: "")

        return result
    }

    override suspend fun updateProfileImage(
        image: File
    ): String? {
        val extension = URLConnection.guessContentTypeFromName(image.name)
        val requestFile = image.asRequestBody(extension.toMediaTypeOrNull())

        val id = sharedPreferences.getUserId()
        return api.updateProfileImage(
            id,
            MultipartBody.Part.createFormData("image", image.name, requestFile)
        ).body()
    }

    override suspend fun updatePermissions(id: Int): String? {
        return api.updatePermissions(
            id,
        ).body()
    }

    override suspend fun updateInstruments(id: Int, instrumentIds: List<Int>): String? {
        val primary = instrumentIds[0]
        val instruments = instrumentIds.subList(1,instrumentIds.size)
        return api.updateInstruments(
            id,
            InstrumentsUserRequest(primary,instruments)
        ).body()
    }

    override suspend fun updateInstruments(instrumentIds: List<Int>): String? {
        val primary = instrumentIds[0]
        val instruments = instrumentIds.subList(1,instrumentIds.size)
        return api.updateInstruments(
            sharedPreferences.getUserId(),
            InstrumentsUserRequest(primary,instruments)
        ).body()
    }

    override suspend fun updatePerteneceJunta(): String? {
        return api.updatePerteneceJunta(sharedPreferences.getUserId()).body()
    }

    override suspend fun getPermissions() {
        val id = sharedPreferences.getUserId()
        val result = api.getPermissions(id).body()

        result?.let { sharedPreferences.updatePermissions(it) }
    }
}