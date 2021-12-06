package com.example.filmatory.systems

import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.filmatory.errors.BaseError
import com.example.filmatory.scenes.activities.StartScene
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException


/**
 * AuthSystem takes care of authenticaton of users
 *
 * @property apiSystem The ApiSystem to use
 * @property auth The FirebaseAuth object to use
 * @property scene What scene it will be implemented in
 */
class AuthSystem(private val apiSystem: ApiSystem, private val auth: FirebaseAuth, private val scene: AppCompatActivity, private val snackbarSystem: SnackbarSystem) {

    /**
     * loginUser logs the user in
     *
     * @param email The email for the user
     * @param password The password for the user
     */
    fun loginUser(email : String, password : String) {
        if(email != "" && password != ""){
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(scene) { task ->
                    if (task.isSuccessful) {
                        // Sign in success
                        Log.d(TAG, "signInWithEmail:success")
                        val intent = Intent(scene, StartScene::class.java)
                        scene.finish()
                        scene.startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        val errorCode = (task.exception as FirebaseAuthException?)!!.localizedMessage
                        snackbarSystem.showSnackbarFailure(errorCode!!, ::retry, "")
                    }
                }
        } else {
            snackbarSystem.showSnackbarFailure("One or both fields are empty.", ::retry, "")
        }
    }

    /**
     * registerUser creates a new user
     *
     * @param email The email for the new account
     * @param password The password for the new account
     */
    fun registerUser(email : String, password : String) {
        if(email != "" && password != ""){
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(scene) { task ->
                    if (task.isSuccessful) {
                        // Sign in success
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        newUserInDatabase(user!!.uid)
                        val intent = Intent(scene, StartScene::class.java)
                        scene.finish()
                        scene.startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        val errorCode = (task.exception as FirebaseAuthException?)!!.localizedMessage
                        snackbarSystem.showSnackbarFailure(errorCode!!, ::retry, "")
                    }
                }
        } else {
            snackbarSystem.showSnackbarFailure("Make sure all fields are filled", ::retry, "")
        }
    }

    fun updatePassword(password: String){
        val user = auth.currentUser
        user!!.updatePassword(password)
            .addOnCompleteListener{ task ->
                if(task.isSuccessful){
                    Log.d(TAG, "updatePassword:success")
                    snackbarSystem.showSnackbarSuccess("Password has been changed.")
                } else {
                    Log.w(TAG, "updatePassword:failure", task.exception)
                    val errorCode = (task.exception as FirebaseAuthException?)!!.localizedMessage
                    snackbarSystem.showSnackbarFailure(errorCode!!, ::retry, "")
                }
            }
    }

    fun sendResetLink(email : String){
        if(email != ""){
            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        Log.d(TAG, "Email sent")
                        snackbarSystem.showSnackbarSuccess("Email has been sent.")
                    } else {
                        Log.w(TAG, "Email did not send", task.exception)
                        val errorCode = (task.exception as FirebaseAuthException?)!!.localizedMessage
                        snackbarSystem.showSnackbarFailure(errorCode!!, ::retry, "")
                    }
                }
        } else {
            snackbarSystem.showSnackbarFailure("Make sure field is not empty.", ::retry, "")
        }
    }

    /**
     * Creates a new user in the mongoDb database
     *
     * @param uid The uid for the user
     */
    private fun newUserInDatabase(uid : String) {
        apiSystem.postUser(ApiSystem.PostBaseOptions(null, uid, null, ::newUserResponse, ::onFailure))
    }

    /**
     * The respond from the API
     *
     * @param string Result
     */
    private fun newUserResponse(string : String?) {
        Log.d(TAG, "$string")
    }

    private fun retry() {
        val intent = Intent(scene, StartScene::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        scene.finish()
        scene.startActivity(intent)
    }

    private fun onFailure(baseError: BaseError) {
        snackbarSystem.showSnackbarFailure(baseError.message, ::retry, "Reload")
    }
}