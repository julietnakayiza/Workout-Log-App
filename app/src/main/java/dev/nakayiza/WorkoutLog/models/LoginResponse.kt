package dev.nakayiza.WorkoutLog.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    var message:String,
    @SerializedName("access_token")var access_token:String,
    @SerializedName("user_id")var user_id:String,
    @SerializedName("profile_id")var profile_id:String
)
