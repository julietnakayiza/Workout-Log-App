package dev.nakayiza.WorkoutLog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {
    lateinit var sharedPrefs:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPrefs=getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)

        var accesToken=sharedPrefs.getString("ACCESS_TOKEN","").toString()
        if (accesToken.isBlank()){
            val intent=Intent(this,LogInActivity::class.java)
            startActivity(intent)
        }
        else{
            val intent=Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }

        var intent=Intent(this, LogInActivity::class.java)
        startActivity(intent)
        finish()
    }
}