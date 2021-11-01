package com.example.filmatory.scenes.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.filmatory.R


class AccinfoFragment : Fragment() {
    lateinit var changeUsernameBtn : Button
    lateinit var changePwBtn : Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view : View = inflater.inflate(R.layout.fragment_accinfo, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeUsernameBtn = view.findViewById(R.id.accinfo_username_btn)
        changePwBtn = view.findViewById(R.id.accinfo_password_btn)
        changeUsernameBtn.setOnClickListener {
            val name : String = view.findViewById<TextView>(R.id.accinfoUsernameTextField).text.toString()

        }
    }
}