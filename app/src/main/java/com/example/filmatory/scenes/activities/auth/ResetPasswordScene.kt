package com.example.filmatory.scenes.activities.auth

import android.os.Bundle
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.auth.ResetPasswordController

/**
 * RegisterScene contains all the components for creating a reset password page
 *
 */
class ResetPasswordScene : AuthScene() {
    private lateinit var resetPasswordController: ResetPasswordController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
        resetPasswordController = ResetPasswordController(this)
    }
}