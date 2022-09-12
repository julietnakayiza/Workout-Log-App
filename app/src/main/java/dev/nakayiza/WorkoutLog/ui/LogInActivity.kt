package dev.nakayiza.WorkoutLog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dev.nakayiza.WorkoutLog.APIClient
import dev.nakayiza.WorkoutLog.api.APIInterface
import dev.nakayiza.WorkoutLog.databinding.ActivityLogInBinding
import dev.nakayiza.WorkoutLog.models.LoginRequest
import dev.nakayiza.WorkoutLog.models.LoginResponse
import dev.nakayiza.WorkoutLog.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LogInActivity : AppCompatActivity() {
    lateinit var binding: ActivityLogInBinding
    lateinit var sharedPrefs:SharedPreferences
    val userViewModel:UserViewModel by viewModels()
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

    override fun onResume() {
        super.onResume()
        userViewModel.loginLiveData.observe(this, Observer{ LoginResponse->
            Toast.makeText(baseContext,LoginResponse?.message,Toast.LENGTH_LONG).show()
            persistLoginDetails(LoginResponse!!)
            startActivity(Intent(baseContext,HomeActivity::class.java))
        })
        userViewModel.loginError.observe(this, Observer { errorMsg->
            Toast.makeText(baseContext,errorMsg,Toast.LENGTH_LONG).show()
        })
    }

    fun validateLogin() {
        var email = binding.etEmail.text.toString()
        var password = binding.etPassword.text.toString()
        var error = false
        if (email.isBlank()) {
            binding.tilEmail.error = "Email is required"
        }
        if (password.isBlank()) {
            binding.tilPassword.error = "Password is required"
        }
        if (!error){
            val loginRequest=LoginRequest(email,password)
            userViewModel.login(loginRequest)
        }

    }


    fun persistLoginDetails(loginResponse: LoginResponse){
        val editor=sharedPrefs.edit()
        editor.putString("USER_ID",loginResponse.user_id)
        editor.putString("ACCESS_TOKEN",loginResponse.access_token)
        editor.putString("PROFILE_ID",loginResponse.profile_id)
        editor.apply()
    }


}
