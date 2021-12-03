package com.example.filmatory.guis

import android.content.Intent
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.ListController
import com.example.filmatory.scenes.activities.ListScene
import com.example.filmatory.scenes.activities.ListsScene

class ListGui(private val listScene: ListScene, private val listController : ListController) {
    var listRecyclerView: RecyclerView = listScene.findViewById(R.id.listRecyclerView)
    var deleteListBtn : Button = listScene.findViewById(R.id.list_delete_btn)

    init {
        deleteListBtn.setOnClickListener {
            if (listController.listId != null) {
                listController.listSystem.deleteList(listController.listId)
            }
            val intent = Intent(listScene, ListsScene::class.java)
            listScene.finish()
            listScene.startActivity(intent)
        }
    }
}