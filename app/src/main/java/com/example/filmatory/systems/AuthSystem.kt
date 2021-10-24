package com.example.filmatory.systems

import android.content.ContentValues
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class AuthSystem(private val apiSystem: ApiSystem, private val auth: FirebaseAuth, private val scene: AppCompatActivity) {

    fun loginUser(email : String, password : String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(scene) { task ->
                if (task.isSuccessful) {
                    // Sign in success
                    Log.d(ContentValues.TAG, "signInWithEmail:success")
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(ContentValues.TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(scene, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
    fun registerUser(email : String, password : String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(scene) { task ->
                if (task.isSuccessful) {
                    // Sign in success
                    Log.d(ContentValues.TAG, "createUserWithEmail:success")
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(scene, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}