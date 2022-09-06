package dev.nakayiza.WorkoutLog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dev.nakayiza.WorkoutLog.APIClient
import dev.nakayiza.WorkoutLog.APIInterface
import dev.nakayiza.WorkoutLog.databinding.ActivityLogInBinding
import dev.nakayiza.WorkoutLog.models.LoginRequest
import dev.nakayiza.WorkoutLog.models.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class LogInActivity : AppCompatActivity() {
    lateinit var binding: ActivityLogInBinding
    lateinit var sharedPrefs:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPrefs=getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)
        handleclick()
    }


    fun handleclick() {
        binding.tvSignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener {
            validateLogin()
        }

    }
    fun validateLogin() {
        var email = binding.etEmail.text.toString()
        var password = binding.etPassword.text.toString()
        if (email.isBlank()) {
            binding.tilEmail.error = "Email is required"
        }
        if (password.isBlank()) {
            binding.tilPassword.error = "Password is required"
        }
//        if (!error()){
//   x         val loginRequest=LoginRequest(email, password)
//        }
    }

    fun makeLoginRequest(loginRequest: LoginRequest) {
        val apiClient = APIClient.buildAPIClient(APIInterface::class.java)
        val request = apiClient.loginUser(loginRequest)

        request.enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful){
                    val loginResponse= response.body()
                    Toast.makeText(baseContext,loginResponse?.message,Toast.LENGTH_LONG).show()
                    startActivity(Intent(baseContext,HomeActivity::class.java))
                }
                else{
                    val error=response.errorBody()?.string()
                    Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }
        })
    }
    fun persistLoginDetails(loginResponse: LoginResponse){
        val editor=sharedPrefs.edit()
        editor.putString("USER_ID",loginResponse.user_id)
        editor.putString("ACCESS_TOKEN",loginResponse.access_token)
        editor.putString("PROFILE_ID",loginResponse.profile_id)
        editor.apply()
    }


}
