package dev.nakayiza.WorkoutLog.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.nakayiza.WorkoutLog.R
import dev.nakayiza.WorkoutLog.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding:ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        castViews()
        setUpBottomNav()
    }

    fun castViews() {
        binding.bottomNavigation
        binding.fcvHome
    }

    fun setUpBottomNav() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.plan -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, PlanFragment())
                        .commit()
                    true
                }
                R.id.track -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, TrackFragment())
                        .commit()
                    true

                }
                R.id.profile -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, ProfileFragment())
                        .commit()
                    true

                }
                else -> false
            }
        }
    }
}