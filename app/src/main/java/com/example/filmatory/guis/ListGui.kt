package com.example.filmatory.guis

import android.content.Intent
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.ListController
import com.example.filmatory.scenes.activities.ListScene
import com.example.filmatory.scenes.activities.ListsScene

/**
 * ListGui contains all the gui elements for the list page
 *
 * @property listScene The scene to use
 * @property listController The controller to use
 */
class ListGui(private val listScene: ListScene, private val listController : ListController) {
    var listRecyclerView: RecyclerView = listScene.findViewById(R.id.listRecyclerView)
    var deleteListBtn : Button = listScene.findViewById(R.id.list_delete_btn)
    var listTitle : TextView = listScene.findViewById(R.id.l_title)

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