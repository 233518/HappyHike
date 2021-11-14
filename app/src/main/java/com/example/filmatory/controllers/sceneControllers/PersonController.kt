package com.example.filmatory.controllers.sceneControllers

import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.filmatory.R
import com.example.filmatory.api.data.person.Person
import com.example.filmatory.controllers.MainController
import com.example.filmatory.scenes.activities.PersonScene

import android.net.Uri
import com.example.filmatory.errors.BaseError
import com.example.filmatory.systems.ApiSystem.RequestBaseOptions

/**
 * PersonController manipulates the PersonScene gui
 *
 * @param personScene The PersonScene to use
 */
class PersonController(private val personScene: PersonScene) : MainController(personScene) {
    var intent: Intent = personScene.intent
    private val pId = intent.getIntExtra("personId",0)
    private val person_readmore_btn = personScene.findViewById<Button>(R.id.person_readmore)
    private val person_readless_btn = personScene.findViewById<Button>(R.id.person_readless)
    private val personData : MutableList<String> = ArrayList()
    init {
        apiSystem.requestPerson(RequestBaseOptions(pId.toString(), null, ::getPerson, ::onFailure), languageCode)
        person_readmore_btn.setOnClickListener {
            loadMoreBio(personData);
            person_readmore_btn.visibility = View.GONE
            person_readless_btn.visibility = View.VISIBLE
        }
        person_readless_btn.setOnClickListener {
            loadLessBio(personData);
            person_readmore_btn.visibility = View.VISIBLE
            person_readless_btn.visibility = View.GONE
        }
    }

    fun onFailure(baseError: BaseError) {

    }

    /**
     * Update the gui with data from API
     *
     * @param person The response from API
     */
    private fun loadMoreBio(data : MutableList<String>){
        personScene.findViewById<TextView>(R.id.person_biography).text = data[0]
    }
    private fun loadLessBio(data : MutableList<String>){
        personScene.findViewById<TextView>(R.id.person_biography).text = data[1]
    }
    private fun getPerson(person: Person){
        personData.add(person.personinfo.biography)
        personData.add(person.shortBio)
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
            if (person.shortBio == person.personinfo.biography){
                person_readmore_btn.visibility = View.GONE
            }
            if (person.links.imdb_id != null){
                personScene.findViewById<View>(R.id.person_imdbLogo).visibility = View.VISIBLE
                personScene.findViewById<View>(R.id.person_imdbLogo).setOnClickListener {
                    personScene.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.imdb.com/name/" + person.links.imdb_id)))
                }
            }
            if (person.links.facebook_id != null){
                personScene.findViewById<View>(R.id.person_facebook_logo).visibility = View.VISIBLE
                personScene.findViewById<View>(R.id.person_facebook_logo).setOnClickListener {
                    personScene.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/" + person.links.facebook_id)))
                }
            }
            if (person.links.instagram_id != null){
                personScene.findViewById<View>(R.id.person_instagram_logo).visibility = View.VISIBLE
                personScene.findViewById<View>(R.id.person_instagram_logo).setOnClickListener {
                    personScene.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/" + person.links.instagram_id)))
                }
            }
            if (person.links.twitter_id != null){
                personScene.findViewById<View>(R.id.person_twitter_logo).visibility = View.VISIBLE
                personScene.findViewById<View>(R.id.person_twitter_logo).setOnClickListener {
                    personScene.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/" + person.links.twitter_id)))
                }
            }
            personScene.findViewById<View>(R.id.person_tmdb_logo).setOnClickListener {
                personScene.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.themoviedb.org/person/" + person.links.id)))
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