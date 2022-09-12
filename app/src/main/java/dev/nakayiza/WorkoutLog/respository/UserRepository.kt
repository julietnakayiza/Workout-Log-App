package dev.nakayiza.WorkoutLog.respository

import dev.nakayiza.WorkoutLog.APIClient
import dev.nakayiza.WorkoutLog.api.APIInterface
import dev.nakayiza.WorkoutLog.models.LoginRequest
import dev.nakayiza.WorkoutLog.models.LoginResponse
import dev.nakayiza.WorkoutLog.models.RegisteRequest
import dev.nakayiza.WorkoutLog.models.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
    var apiClent=APIClient.buildAPIClient(APIInterface::class.java)

    suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse> = withContext(Dispatchers.IO){
        val response=apiClent.loginUser(loginRequest)
        return@withContext response

    }
    suspend fun registerUser(registerRequest: RegisteRequest):Response<RegisterResponse>
            = withContext((Dispatchers.IO)){
        val response = apiClent.createUser(registerRequest)
        return@withContext response
    }


}