package dev.nakayiza.WorkoutLog.models

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    var message: String,
    var user:String
)