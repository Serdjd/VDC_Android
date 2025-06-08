package com.treintaYTres.vdc.network

import com.treintaYTres.vdc.network.model.request.AttendanceRequest
import com.treintaYTres.vdc.network.model.request.CreateEventRequest
import com.treintaYTres.vdc.network.model.request.EmailUserRequest
import com.treintaYTres.vdc.network.model.request.InstrumentsUserRequest
import com.treintaYTres.vdc.network.model.request.PermissionsUserRequest
import com.treintaYTres.vdc.network.model.request.RollCallRequest
import com.treintaYTres.vdc.network.model.request.ValidateRequest
import com.treintaYTres.vdc.network.model.response.band.BandResponse
import com.treintaYTres.vdc.network.model.response.event.EventResponse
import com.treintaYTres.vdc.network.model.response.eventdetails.EventDetailsResponse
import com.treintaYTres.vdc.network.model.response.group.CreateDataResponse
import com.treintaYTres.vdc.network.model.response.instrument.InstrumentsResponse
import com.treintaYTres.vdc.network.model.response.member.MemberResponse
import com.treintaYTres.vdc.network.model.response.profile.ProfileResponse
import com.treintaYTres.vdc.network.model.response.rollcall.RollCallResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("users/{id}")
    suspend fun getUserData(
        @Path("id") id: Int
    ): Response<MemberResponse>

    @GET("users/id/{email}")
    suspend fun getUserId(
        @Path("email") email: String
    ): Response<Int>

    @GET("users/validate/{id}")
    suspend fun checkValidation(
        @Path("id") id: Int
    ): Response<Boolean>

    @GET("users/registry_completed/{id}")
    suspend fun checkRegistryCompleted(
        @Path("id") id: Int
    ): Response<Boolean>

    @POST("users")
    suspend fun createUser(@Body emailUserRequest: EmailUserRequest): Response<Int>

    @Multipart
    @PUT("users/{id}")
    suspend fun completeRegistry(
        @Path("id") id: Int,
        @Part image: MultipartBody.Part,
        @Part("data") data: RequestBody
    ): Response<String>

    @Multipart
    @PUT("users/profile/{id}")
    suspend fun updateProfileImage(
        @Path("id") id: Int,
        @Part image: MultipartBody.Part
    ): Response<String>

    @PUT("users/permissions/{id}")
    suspend fun updatePermissions(
        @Path("id") id: Int,
        @Body request: PermissionsUserRequest = PermissionsUserRequest(0)
    ): Response<String>

    @PUT("users/junta/{id}")
    suspend fun updatePerteneceJunta(
        @Path("id") id: Int
    ): Response<String>

    @PUT("users/instruments/{id}")
    suspend fun updateInstruments(
        @Path("id") id: Int,
        @Body request: InstrumentsUserRequest
    ): Response<String>

    @GET("band/{id}")
    suspend fun getBandInfo(
        @Path("id") id: Int
    ): Response<BandResponse>

    @GET("events/{id}")
    suspend fun getEvents(
        @Path("id") id: Int,
        @Query("filter") filter: String
    ): Response<EventResponse>

    @POST("events")
    suspend fun createEvent(
        @Body request: CreateEventRequest
    ): Response<String>

    @PUT("events")
    suspend fun modifyEvent(
        @Body request: CreateEventRequest
    ): Response<String>

    @PUT("events/{userId}/{eventId}")
    suspend fun updateAttendance(
        @Path("eventId") eventId: Int,
        @Path("userId") userId: Int,
        @Body request: AttendanceRequest
    ): Response<String>

    @GET("eventdetails/{id}")
    suspend fun getEventDetails(
        @Path("id") id: Int
    ): Response<EventDetailsResponse>

    @GET("profile/{id}")
    suspend fun getProfile(
        @Path("id") id: Int
    ): Response<ProfileResponse>

    @GET("rollcall/{id}")
    suspend fun getRollCall(
        @Path("id") id: Int
    ): Response<RollCallResponse>

    @PUT("rollcall/{id}")
    suspend fun updateRollCall(
        @Path("id") id: Int,
        @Body request: List<RollCallRequest>
    ): Response<String>

    @GET("instruments")
    suspend fun getInstruments(): Response<InstrumentsResponse>

    @GET("users/permissions/{id}")
    suspend fun getPermissions(
        @Path("id") id: Int
    ): Response<Int>

    @GET("events/create")
    suspend fun getCreateData(): Response<CreateDataResponse>

    @PUT("users/validate/{id}")
    suspend fun deleteMember(
        @Path("id") id: Int,
        @Body request: ValidateRequest = ValidateRequest(false)
    ): Response<Unit>
}