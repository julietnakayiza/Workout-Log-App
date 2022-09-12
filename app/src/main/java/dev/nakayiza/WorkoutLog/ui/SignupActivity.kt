package dev.nakayiza.WorkoutLog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dev.nakayiza.WorkoutLog.APIClient
import dev.nakayiza.WorkoutLog.api.APIInterface
import dev.nakayiza.WorkoutLog.databinding.ActivitySignUpBinding
import dev.nakayiza.WorkoutLog.models.LoginRequest
import dev.nakayiza.WorkoutLog.models.RegisteRequest
import dev.nakayiza.WorkoutLog.models.RegisterResponse
import dev.nakayiza.WorkoutLog.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    lateinit var sharedprefs:SharedPreferences
    val userViewModel:UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedprefs=getSharedPreferences("WORKOUT PREFS", MODE_PRIVATE)

        binding.tvlog.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }
        binding.btnsignupp.setOnClickListener {
            validate()
        }

    }

    override fun onResume() {
        super.onResume()
        userViewModel.loginLiveData.observe(this, Observer { RegisterResponse->
            Toast.makeText(baseContext,RegisterResponse?.message,Toast.LENGTH_LONG).show()
            startActivity(Intent(baseContext,HomeActivity::class.java))
        })
        userViewModel.registerError.observe(this,errorMsg->
        Toast.makeText(baseContext,errorMsg,Toast.LENGTH_LONG).show()
        )
    }

    fun validate() {
        var Firstname = binding.etFirstname.text.toString()
        var Lastname = binding.etLastname.text.toString()
        var email = binding.etemail.text.toString()
        var password = binding.etpassword.text.toString()
        var confirm = binding.etconfirm.text.toString()


//        var error = false

        if (Firstname.isBlank()) {
            binding.tilFirstname.error = "Firstname is required"
        }
        if (Lastname.isBlank()) {
            binding.tilLastname.error = "Lastname is required"
        }
        binding.etemail.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(p0: CharSequence?, start: Int, before: Int, after: Int) {
                binding.tilemail.error = null
            }

            override fun beforeTextChanged(p0: CharSequence?, start: Int, before: Int, after: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        var error=false
        if (email.isBlank()) {
            binding.tilemail.error = "Please Input Your Email"
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilemail.error = "Email is invalid"
        }
        if (password.isBlank()) {
            binding.tilpassword.error = "Please Input Your password"
        }
        if (confirm.isBlank()) {
            binding.tilconfirm.error = "Please Confirm Your password"
        }
        if (confirm != (password))
            binding.tilconfirm.error = "Passwords do not match"

        if (!error){
            val RegisteRequest= RegisteRequest(email,password)
            userViewModel.register(RegisteRequest)
        }

    }




}






