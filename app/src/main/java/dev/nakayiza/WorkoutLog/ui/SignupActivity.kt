package dev.nakayiza.WorkoutLog.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.Toast
import dev.nakayiza.WorkoutLog.APIClient
import dev.nakayiza.WorkoutLog.APIInterface
import dev.nakayiza.WorkoutLog.databinding.ActivitySignUpBinding
import dev.nakayiza.WorkoutLog.models.RegisteRequest
import dev.nakayiza.WorkoutLog.models.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvlog.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }
        binding.btnsignupp.setOnClickListener {
            validate()
        }

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
    }
    fun makeregistrationRequest(registeRequest: RegisteRequest){
        var apiClient=APIClient.buildAPIClient(APIInterface::class.java)
        var request=apiClient.createUser(registeRequest)

        request.enqueue(object : Callback<RegisterResponse>{
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                binding.pbRegister.visibility= View.GONE
                if (response.isSuccessful){
                    val RegisterResponse=response.body()
                    Toast.makeText(baseContext,RegisterResponse?.message,Toast.LENGTH_LONG).show()

                }
                else{
                    var error=response.errorBody()?.string()
                    Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }
        })
    }




}






