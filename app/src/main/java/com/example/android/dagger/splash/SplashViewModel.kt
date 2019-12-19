package com.example.android.dagger.splash

import android.app.Activity
import com.example.android.dagger.login.LoginActivity
import com.example.android.dagger.main.MainActivity
import com.example.android.dagger.registration.RegistrationActivity
import com.example.android.dagger.user.UserManager
import javax.inject.Inject

class SplashViewModel @Inject constructor(val userManager: UserManager) {

    fun getNextActivity() : Class<out Activity> =
        if (!userManager.isUserLoggedIn()) {
            if (!userManager.isUserRegistered()) {
                RegistrationActivity::class.java
            } else {
                LoginActivity::class.java
            }
        } else {
            MainActivity::class.java
        }
}