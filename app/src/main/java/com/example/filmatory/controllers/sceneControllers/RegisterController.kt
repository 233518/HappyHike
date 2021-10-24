package com.example.filmatory.controllers.sceneControllers

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.filmatory.R
import com.example.filmatory.controllers.MainController
import com.example.filmatory.entities.User
import com.example.filmatory.scenes.activities.RegisterScene
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseUser

class RegisterController(registerScene: RegisterScene) : MainController(registerScene) {
    val registerScene = registerScene
    val registerBtn = registerScene.findViewById<Button>(R.id.reg_btn)

    init {
        registerBtn.setOnClickListener {
            var email = registerScene.findViewById<TextInputEditText>(R.id.regEmailEditField).text.toString()
            var password = registerScene.findViewById<TextInputEditText>(R.id.regPasswordEditField).text.toString()
            var passwordRepeat = registerScene.findViewById<TextInputEditText>(R.id.regPasswordRepeatEditField).text.toString()
            if(password === passwordRepeat)
                registerUser(email, password, "hi")
        }
    }

    fun registerUser(email : String, password : String, username : String) {
        registerScene.auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(registerScene) { task ->
                if (task.isSuccessful) {
                    var user = User(email, password, username)
                    update_Database(user)
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(registerScene, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }
    }

    fun update_Database(user : User) {
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        TODO("Not yet implemented")
    }
}