package com.example.filmatory.scenes.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.filmatory.R
import com.example.filmatory.api.data.person.Person
import com.example.filmatory.controllers.sceneControllers.MovieController
import com.example.filmatory.controllers.sceneControllers.PersonController
import com.example.filmatory.scenes.SuperScene

class PersonScene : SuperScene() {
    private lateinit var personController : PersonController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_scene)
        personController = PersonController(this)
    }
}