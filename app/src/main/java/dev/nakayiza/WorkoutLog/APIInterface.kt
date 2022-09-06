package dev.nakayiza.WorkoutLog

import dev.nakayiza.WorkoutLog.models.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface APIInterface {
    @FormUrlEncoded
    @POST("CreateUser")
    fun createUser(@Body registeRequest: RegisteRequest): Call<RegisterResponse>

    @POST("/login")
    fun loginUser(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("/profile")
    fun userProfile(@Body profileRequest: ProfileRequest): Call<ProfileResponse>
}