package com.example.filmatory.controllers.sceneControllers

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import com.example.filmatory.controllers.MainController
import com.example.filmatory.scenes.activities.RegisterScene
import com.google.firebase.auth.FirebaseUser

class RegisterController(registerScene: RegisterScene) : MainController(registerScene) {
    val registerScene = registerScene

    fun registerUser(email : String, password : String) {
        registerScene.auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(registerScene) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = registerScene.auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(registerScene, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        TODO("Not yet implemented")
    }
}