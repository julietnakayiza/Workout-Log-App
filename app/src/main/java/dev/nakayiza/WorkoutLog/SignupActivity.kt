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
import org.w3c.dom.Text

class SignupActivity : AppCompatActivity() {
    lateinit var tvlog:TextView
    lateinit var etFirstname:TextInputEditText
    lateinit var etLastname:TextInputEditText
    lateinit var etemail:TextInputEditText
    lateinit var etpassword:TextInputEditText
    lateinit var etconfirm:TextInputEditText
    lateinit var tilFirstname:TextInputLayout
    lateinit var tilLastname:TextInputLayout
    lateinit var tilemail:TextInputLayout
    lateinit var tilpassword:TextInputLayout
    lateinit var tilconfirm:TextInputLayout
    lateinit var btnsignupp:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        tvlog=findViewById(R.id.tvlog)
        etFirstname=findViewById(R.id.etFirstname)
        etLastname=findViewById(R.id.etLastname)
        etemail=findViewById(R.id.etemail)
        etpassword=findViewById(R.id.etpassword)
        etconfirm=findViewById(R.id.etconfirm)
        tilFirstname=findViewById(R.id.tilFirstname)
        tilLastname=findViewById(R.id.tilLastname)
        tilemail=findViewById(R.id.tilemail)
        tilpassword=findViewById(R.id.tilpassword)
        tilconfirm=findViewById(R.id.tilconfirm)
        btnsignupp=findViewById(R.id.btnsignupp)

        tvlog.setOnClickListener {
            val intent=Intent(this,LogInActivity::class.java)
            startActivity(intent)
        }
        btnsignupp.setOnClickListener {
            validate()
        }

    }
    fun validate() {
        var Firstname = etFirstname.text.toString()
        var Lastname = etLastname.text.toString()
        var email = etemail.text.toString()
        var password = etpassword.text.toString()
        var confirm = etconfirm.text.toString()
        if (Firstname.isBlank()) {
            tilFirstname.error = "Please Input Your Firstname"
        }
        if (Lastname.isBlank()) {
            tilLastname.error = "Please Input Your Lastname"
        }
        etemail.addTextChangedListener(object :TextWatcher {
            override fun onTextChanged(p0: CharSequence?, start: Int, before: Int, after: Int) {
                tilemail.error=null
            }

            override fun beforeTextChanged(p0: CharSequence?, start: Int, before: Int, after: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        if (email.isBlank()) {
            tilemail.error = "Please Input Your Email"
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            tilemail.error="Email is invalid"
        }
        if (password.isBlank()) {
            tilpassword.error = "Please Input Your password"
        }
        if (confirm.isBlank()) {
            tilconfirm.error = "Please Confirm Your password"
        }
        if (confirm !=(password))
            tilconfirm.error = "Your Password do not match"
        }
    }





