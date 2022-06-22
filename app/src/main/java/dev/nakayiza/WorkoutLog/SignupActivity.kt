package dev.nakayiza.WorkoutLog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dev.nakayiza.WorkoutLog.databinding.ActivitySignUpBinding
import org.w3c.dom.Text

class SignupActivity : AppCompatActivity() {
    lateinit var binding:ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvlog.setOnClickListener {
            val intent=Intent(this,LogInActivity::class.java)
            startActivity(intent)
        }
        binding.btnsignupp.setOnClickListener {
            validate()
        }


    }
    fun validate() {
         var Firstname= binding.etFirstname.text.toString()
         var Lastname=binding.etLastname.text.toString()
         var email=  binding.etemail.text.toString()
         var password=binding.etpassword.text.toString()
         var confirm=binding.etconfirm.text.toString()
        if (Firstname.isBlank()) {
            binding.tilFirstname.error = "Please Input Your Firstname"
        }
        if (Lastname.isBlank()) {
            binding.tilLastname.error = "Please Input Your Lastname"
        }
        binding.etemail.addTextChangedListener(object :TextWatcher {
            override fun onTextChanged(p0: CharSequence?, start: Int, before: Int, after: Int) {
                binding.tilemail.error=null
            }

            override fun beforeTextChanged(p0: CharSequence?, start: Int, before: Int, after: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        if (email.isBlank()) {
            binding.tilemail.error = "Please Input Your Email"
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.tilemail.error="Email is invalid"
        }
        if (password.isBlank()) {
            binding.tilpassword.error = "Please Input Your password"
        }
        if (confirm.isBlank()) {
            binding.tilconfirm.error = "Please Confirm Your password"
        }
        if (confirm !=(password))
            binding.tilconfirm.error = "Your Password do not match"
        }
    }





