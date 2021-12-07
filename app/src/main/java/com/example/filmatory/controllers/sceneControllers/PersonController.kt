package com.example.filmatory.controllers.sceneControllers

import android.content.Intent
import android.view.View
import com.example.filmatory.api.data.person.Person
import com.example.filmatory.controllers.MainController
import com.example.filmatory.scenes.activities.PersonScene
import com.example.filmatory.guis.PersonGui
import com.example.filmatory.systems.ApiSystem.RequestBaseOptions

/**
 * PersonController controls everything related to the person page
 *
 * @param personScene The PersonScene to use
 */
class PersonController(private val personScene: PersonScene) : MainController(personScene) {
    private val personGui = PersonGui(personScene, this)
    private val intent: Intent = personScene.intent
    private val pId = intent.getIntExtra("personId",0)

    var personData : MutableList<String> = ArrayList()
        private set

    init {
        apiSystem.requestPerson(RequestBaseOptions(pId.toString(), null, ::getPerson, ::onFailure), languageCode)
    }

    /**
     * Update the gui with data from API
     *
     * @param person The response from API
     */
    fun loadMoreBio(data : MutableList<String>){
        personGui.personBiography.text = data[0]
    }
    fun loadLessBio(data : MutableList<String>){
        personGui.personBiography.text = data[1]
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

        if (person.personinfo.deathday != null) status = "Died " + person.personinfo.deathday;
        if (person.links.imdb_id != null) personGui.imdbLogoClick(person)
        if (person.links.facebook_id != null) personGui.facebookLogoClick(person)
        if (person.links.instagram_id != null) personGui.instagramLogoClick(person)
        if (person.links.twitter_id != null) personGui.twitterLogoClick(person)

        personScene.runOnUiThread {
            if (person.shortBio == person.personinfo.biography) personGui.personReadmoreBtn.visibility = View.GONE
            personGui.tmdbLogoClick(person)
            personGui.personBiography.text = person.shortBio
            personGui.personName.text = person.personinfo.name
            personGui.personDateBirthday.text = person.personinfo.place_of_birth
            personGui.personGender.text = gender
            personGui.personStatus.text = status
        }
        personGui.configureSlider(person)
    }
}