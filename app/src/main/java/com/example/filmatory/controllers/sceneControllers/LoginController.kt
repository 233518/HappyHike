package com.example.filmatory.controllers.sceneControllers

import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.filmatory.R
import com.example.filmatory.controllers.MainController
import com.example.filmatory.scenes.activities.LoginScene
import com.example.filmatory.scenes.activities.RegisterScene
import com.google.firebase.auth.FirebaseUser

class LoginController(loginScene: LoginScene) : MainController(loginScene) {
    val loginScene = loginScene
    private var regBtn: TextView

    //Init
    init {
        regBtn = loginScene.findViewById(R.id.regHereBtn)
        regBtn.setOnClickListener {
            val intent = Intent(loginScene, RegisterScene::class.java)
            loginScene.startActivity(intent)
        loginUser("testuser@test123.no", "hello12345");
        }
    }

    private fun loginUser(email : String, password : String) {
        loginScene.auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(loginScene) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = loginScene.auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(loginScene, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        TODO("Not yet implemented")
    }
}