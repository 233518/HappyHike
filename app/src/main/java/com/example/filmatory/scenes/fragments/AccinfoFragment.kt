package com.example.filmatory.scenes.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.filmatory.R
import com.example.filmatory.scenes.activities.AccountInfoScene
import com.example.filmatory.systems.ApiSystem
import com.example.filmatory.systems.AuthSystem
import com.example.filmatory.systems.SnackbarSystem
import com.example.filmatory.systems.UserInfoSystem
import com.google.android.material.textfield.TextInputEditText


class AccinfoFragment(apiSystem: ApiSystem, var accountInfoScene: AccountInfoScene, snackbarSystem: SnackbarSystem) : Fragment() {
    lateinit var changeUsernameBtn : Button
    lateinit var changePwBtn : Button
    var userInfoSystem = UserInfoSystem(apiSystem)
    val authSystem = AuthSystem(apiSystem, accountInfoScene.auth, accountInfoScene, snackbarSystem)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view : View = inflater.inflate(R.layout.fragment_accinfo, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeUsernameBtn = view.findViewById(R.id.accinfo_username_btn)
        changePwBtn = view.findViewById(R.id.accinfo_password_btn)
        changeUsernameBtn.setOnClickListener {
            val name : String = view.findViewById<TextInputEditText>(R.id.accinfoUsernameTextField).text.toString()
            userInfoSystem.updateUsername(accountInfoScene.auth.currentUser!!.uid, name)
        }

        changePwBtn.setOnClickListener {
            val passwordFieldOne : String = view.findViewById<TextInputEditText>(R.id.accinfoPassEditTextField).text.toString()
            val passwordFieldTwo : String = view.findViewById<TextInputEditText>(R.id.accinfoRepeatPassEditTextField).text.toString()
            if(passwordFieldOne == passwordFieldTwo){
                authSystem.updatePassword(passwordFieldOne)
            }
        }
    }
}