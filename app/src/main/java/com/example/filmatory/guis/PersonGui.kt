package com.example.filmatory.guis

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.filmatory.R
import com.example.filmatory.api.data.person.Person
import com.example.filmatory.controllers.sceneControllers.PersonController
import com.example.filmatory.scenes.activities.PersonScene

/**
 * PersonGui contains all the gui elements for the person page
 *
 * @property personScene The scene to use
 * @property personController The controller to use
 */
class PersonGui(private val personScene: PersonScene, private val personController: PersonController) {
    var personReadmoreBtn : Button = personScene.findViewById(R.id.person_readmore)
    var personReadlessBtn : Button = personScene.findViewById(R.id.person_readless)
    var personBiography : TextView = personScene.findViewById(R.id.person_biography)
    var personName : TextView = personScene.findViewById(R.id.person_name)
    var personDateBirthday : TextView = personScene.findViewById(R.id.person_data_birthplace)
    var personGender : TextView = personScene.findViewById(R.id.person_data_gender)
    var personStatus : TextView = personScene.findViewById(R.id.person_data_status)
    var personImdbLogo : View = personScene.findViewById(R.id.person_imdbLogo)
    var personFacebookLogo : View = personScene.findViewById(R.id.person_facebook_logo)
    var personInstagramLogo : View = personScene.findViewById(R.id.person_instagram_logo)
    var personTwitterLogo : View = personScene.findViewById(R.id.person_twitter_logo)
    var personTmdbLogo : View = personScene.findViewById(R.id.person_tmdb_logo)
    init {
        personReadmoreBtn.setOnClickListener {
            personController.loadMoreBio(personController.personData);
            personReadmoreBtn.visibility = View.GONE
            personReadlessBtn.visibility = View.VISIBLE
        }
        personReadlessBtn.setOnClickListener {
            personController.loadLessBio(personController.personData);
            personReadmoreBtn.visibility = View.VISIBLE
            personReadlessBtn.visibility = View.GONE
        }
    }

    /**
     * Creates the action for clicking on the imdb logo
     *
     * @param person Person info
     */
    fun imdbLogoClick(person: Person) {
        personScene.runOnUiThread {
            personImdbLogo.visibility = View.VISIBLE
            personImdbLogo.setOnClickListener {
                personScene.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.imdb.com/name/" + person.links.imdb_id)
                    )
                )
            }
        }
    }

    /**
     * Creates the action for clicking on the facebook logo
     *
     * @param person Person info
     */
    fun facebookLogoClick(person: Person) {
        personScene.runOnUiThread {
            personFacebookLogo.visibility = View.VISIBLE
            personFacebookLogo.setOnClickListener {
                personScene.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.facebook.com/" + person.links.facebook_id)
                    )
                )
            }
        }
    }

    /**
     * Creates the action for clicking on the instagram logo
     *
     * @param person Person info
     */
    fun instagramLogoClick(person: Person) {
        personScene.runOnUiThread {
            personInstagramLogo.visibility = View.VISIBLE
            personInstagramLogo.setOnClickListener {
                personScene.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/" + person.links.instagram_id)
                    )
                )
            }
        }
    }

    /**
     * Creates the action for clicking on the twitter logo
     *
     * @param person Person info
     */
    fun twitterLogoClick(person: Person) {
        personScene.runOnUiThread {
            personTwitterLogo.visibility = View.VISIBLE
            personTwitterLogo.setOnClickListener {
                personScene.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://twitter.com/" + person.links.twitter_id)
                    )
                )
            }
        }
    }

    /**
     * Creates the action for clicking on the tmdb logo
     *
     * @param person Person info
     */
    fun tmdbLogoClick(person: Person) {
        personTmdbLogo.setOnClickListener {
            personScene.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.themoviedb.org/person/" + person.links.id)
                )
            )
        }
    }

    /**
     * Configures the slider
     *
     * @param person Person info
     */
    fun configureSlider(person: Person) {
        personScene.runOnUiThread {
            Glide.with(personScene)
                .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + person.personinfo.profile_path)
                .placeholder(R.drawable.placeholder_image)
                .fallback(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)
                .centerCrop()
                .into(personScene.findViewById(R.id.person_img))
        }
    }
}