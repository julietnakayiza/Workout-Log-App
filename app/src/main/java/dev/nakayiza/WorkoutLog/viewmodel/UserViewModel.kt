package dev.nakayiza.WorkoutLog.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.nakayiza.WorkoutLog.models.LoginRequest
import dev.nakayiza.WorkoutLog.models.LoginResponse
import dev.nakayiza.WorkoutLog.models.RegisteRequest
import dev.nakayiza.WorkoutLog.models.RegisterResponse
import dev.nakayiza.WorkoutLog.respository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {
    val userRepository=UserRepository()
    val loginLiveData=MutableLiveData<LoginResponse>()
    val loginError=MutableLiveData<String>()
    val registerError=MutableLiveData<RegisterResponse>()
    val registerLiveData=MutableLiveData<String>()

    fun login(loginRequest: LoginRequest){
        viewModelScope.launch {
            val response=userRepository.loginUser(loginRequest)
            if (response.isSuccessful){
                loginLiveData.postValue(response.body())
            } else{
                loginError.postValue(response.errorBody()?.string())
            }
        }
    }
    fun register(registeRequest: RegisteRequest){
        viewModelScope.launch {
            val response=userRepository.registerUser(registeRequest)
            if (response.isSuccessful){
                registerLiveData.postValue(response.body())
            }else {
                registerError.postValue(response.errorBody()?.string())
            }
        }
    }
}