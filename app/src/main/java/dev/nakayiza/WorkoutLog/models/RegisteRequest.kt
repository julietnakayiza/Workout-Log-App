package dev.nakayiza.WorkoutLog.models

import com.google.gson.annotations.SerializedName

data class RegisteRequest(
    @SerializedName("first_name") var FirstName: String,
    @SerializedName("last_name") var LastName: String,
    @SerializedName("email") var email: String,
    @SerializedName("phone_number") var phoneNumber: String,
    @SerializedName("password") var password: String,
    val phoneNumber1: String
)
