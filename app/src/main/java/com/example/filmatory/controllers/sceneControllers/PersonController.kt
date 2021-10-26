package com.example.filmatory.controllers.sceneControllers

import android.content.Intent
import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.filmatory.R
import com.example.filmatory.api.data.person.Person
import com.example.filmatory.controllers.MainController
import com.example.filmatory.scenes.activities.PersonScene

/**
 * PersonController manipulates the PersonScene gui
 *
 * @param personScene The PersonScene to use
 */
class PersonController(private val personScene: PersonScene) : MainController(personScene) {
    var intent: Intent = personScene.intent
    private val pId = intent.getIntExtra("personId",0)

    init {
        apiSystem.requestPerson(pId.toString(),::getPerson)
    }

    /**
     * Update the gui with data from API
     *
     * @param person The response from API
     */
    private fun getPerson(person: Person){
        var gender = "Other";
        var status = "Alive";

        if (person.personinfo.gender == 1) {
            gender = "Female";
        } else if (person.personinfo.gender == 2) {
            gender = "Male";
        }

        if (person.personinfo.deathday != null) {
            status = "Died " + person.personinfo.deathday;
        }

        personScene.runOnUiThread(Runnable {
            if (person.links.imdb_id != null){
                personScene.findViewById<View>(R.id.person_imdbLogo).visibility = View.VISIBLE
            }
            if (person.links.facebook_id != null){
                personScene.findViewById<View>(R.id.person_facebook_logo).visibility = View.VISIBLE
            }
            if (person.links.instagram_id != null){
                personScene.findViewById<View>(R.id.person_instagram_logo).visibility = View.VISIBLE
            }
            if (person.links.twitter_id != null){
                personScene.findViewById<View>(R.id.person_twitter_logo).visibility = View.VISIBLE
            }
            personScene.findViewById<TextView>(R.id.person_biography).text = person.shortBio
            personScene.findViewById<TextView>(R.id.person_name).text = person.personinfo.name
            personScene.findViewById<TextView>(R.id.person_data_birthplace).text = person.personinfo.place_of_birth
            personScene.findViewById<TextView>(R.id.person_data_gender).text = gender
            personScene.findViewById<TextView>(R.id.person_data_status).text = status
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