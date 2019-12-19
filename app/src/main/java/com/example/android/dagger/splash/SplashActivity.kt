package com.example.android.dagger.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.android.dagger.MyApplication
import com.example.android.dagger.R
import com.example.android.dagger.main.MainActivity
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var splashViewModel: SplashViewModel

    private lateinit var clazzToLaunch: Class<out Activity>

    private val launchMainScreen: () -> Unit = {
        val intent = Intent(this, clazzToLaunch)
        Log.d("SplashActivity", "next activity: $clazzToLaunch")
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        (application as MyApplication).appComponent.inject(this)
        clazzToLaunch = splashViewModel.getNextActivity()

        Handler(mainLooper).postDelayed(launchMainScreen, 1000)
    }
}