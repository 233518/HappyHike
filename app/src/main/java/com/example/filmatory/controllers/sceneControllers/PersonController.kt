package com.example.filmatory.controllers.sceneControllers

import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.filmatory.R
import com.example.filmatory.api.data.person.Person
import com.example.filmatory.controllers.MainController
import com.example.filmatory.scenes.activities.PersonScene

class PersonController(personScene: PersonScene) : MainController(personScene) {
    val personScene = personScene
    var intent = personScene.intent
    val pId = intent.getIntExtra("personId",0)


    init {
        apiSystem.requestPerson("2524",::getPerson)
    }

    fun getPerson(person: Person){
        personScene.runOnUiThread(Runnable {
            print(person.personinfo)
            personScene.findViewById<TextView>(R.id.person_biography).text = person.personinfo.biography
            Glide.with(personScene)
                .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + person.personinfo.profile_path)
                .placeholder(R.drawable.placeholder_image)
                .fallback(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)
                .centerCrop()
                .into(personScene.findViewById(R.id.person_img))
        })
    }
}