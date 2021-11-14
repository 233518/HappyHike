package com.example.filmatory.scenes.activities


import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.TvsController
import com.example.filmatory.scenes.SuperScene

/**
 * TvsScene is the scene for showing tvs
 *
 */
class TvsScene : SuperScene(), AdapterView.OnItemSelectedListener  {
    private lateinit var tvsController: TvsController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.media_list_container)
        tvsController = TvsController(this)
    }

    override fun onItemSelected(p0: AdapterView<*>, view: View?, pos: Int, id: Long) {
        tvsController.onNewSelected(p0.getItemAtPosition(pos))
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}