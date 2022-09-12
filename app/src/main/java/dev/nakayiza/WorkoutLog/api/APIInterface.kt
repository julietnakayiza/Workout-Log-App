package dev.nakayiza.WorkoutLog.api

import dev.nakayiza.WorkoutLog.models.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface APIInterface {
    @FormUrlEncoded
    @POST("CreateUser")
    fun createUser(@Body registeRequest: RegisteRequest): Response<RegisterResponse>

    @POST("/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>


}