package com.example.filmatory.controllers.sceneControllers

import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.filmatory.R
import com.example.filmatory.controllers.MainController
import com.example.filmatory.scenes.activities.LoginScene
import com.example.filmatory.scenes.activities.RegisterScene
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseUser
import org.w3c.dom.Text
import kotlin.math.log

class LoginController(loginScene: LoginScene) : MainController(loginScene) {
    val loginScene = loginScene
    private var regBtn = loginScene.findViewById<TextView>(R.id.regHereBtn)
    private var logBtn = loginScene.findViewById<Button>(R.id.login_btn)

    //Init
    init {
        regBtn.setOnClickListener {
            val intent = Intent(loginScene, RegisterScene::class.java)
            loginScene.startActivity(intent)
        }
        logBtn.setOnClickListener {
            var email = loginScene.findViewById<TextInputEditText>(R.id.loginEmailEditTextField).text.toString()
            var password = loginScene.findViewById<TextInputEditText>(R.id.loginPasswordEditTextField).text.toString()
            Log.d(TAG, "Email: $email password: $password")
            loginUser( email, password);
        }
    }

    private fun loginUser(email : String, password : String) {
        getUser()
        loginScene.auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(loginScene) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = loginScene.auth.currentUser
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(loginScene, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }
    }

    fun getUser() {
        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        TODO("Not yet implemented")
    }
}