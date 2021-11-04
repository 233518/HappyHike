package com.example.filmatory.systems

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

/**
 * AuthSystem takes care of authenticaton of users
 *
 * @property apiSystem The ApiSystem to use
 * @property auth The FirebaseAuth object to use
 * @property scene What scene it will be implemented in
 */
class AuthSystem(private val apiSystem: ApiSystem, private val auth: FirebaseAuth, private val scene: AppCompatActivity) {

    /**
     * loginUser logs the user in
     *
     * @param email The email for the user
     * @param password The password for the user
     */
    fun loginUser(email : String, password : String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(scene) { task ->
                if (task.isSuccessful) {
                    // Sign in success
                    Log.d(TAG, "signInWithEmail:success")
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(scene, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    /**
     * registerUser creates a new user
     *
     * @param email The email for the new account
     * @param password The password for the new account
     */
    fun registerUser(email : String, password : String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(scene) { task ->
                if (task.isSuccessful) {
                    // Sign in success
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    newUserInDatabase(user!!.uid)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(scene, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun updatePassword(password: String){
        val user = auth.currentUser
        user!!.updatePassword(password)
            .addOnCompleteListener{ task ->
                if(task.isSuccessful){
                    Log.d(TAG, "updatePassword:success")
                    Toast.makeText(scene, "Password update success",
                        Toast.LENGTH_SHORT).show()
                } else {
                    Log.w(TAG, "updatePassword:failure", task.exception)
                    Toast.makeText(scene, "Password update failed",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun sendResetLink(email : String){
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    Log.d(TAG, "Email sent")
                    Toast.makeText(scene, "Email has been sent",
                        Toast.LENGTH_SHORT).show()
                } else {
                    Log.w(TAG, "Email did not send", task.exception)
                    Toast.makeText(scene, "Reset email failed",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    /**
     * Creates a new user in the mongoDb database
     *
     * @param uid The uid for the user
     */
    private fun newUserInDatabase(uid : String) {
        apiSystem.postUser(uid, ::newUserResponse)
    }

    /**
     * The respond from the API
     *
     * @param string Result
     */
    private fun newUserResponse(string : String?) {
        Log.d(TAG, "$string")
    }
}