package com.example.filmatory.controllers.sceneControllers

import android.widget.TextView
import com.example.filmatory.R
import com.example.filmatory.api.data.Lists.List
import com.example.filmatory.api.data.Lists.ListItem
import com.example.filmatory.controllers.MainController
import com.example.filmatory.scenes.activities.ListScene

class ListController(listScene: ListScene) : MainController(listScene) {
    val listScene = listScene
    var intent = listScene.intent
    val listId = intent.getStringExtra("listId")

    init {
        apiSystem.requestList(listId.toString() ,::getList)
    }

    fun getList(list: List){
        listScene.runOnUiThread(Runnable {
            listScene.findViewById<TextView>(R.id.l_title).text = "Test"
        })
    }
}