package com.example.filmatory.systems

import android.content.Intent
import com.example.filmatory.systems.ApiSystem.PostBaseOptions
import com.example.filmatory.errors.BaseError
import com.example.filmatory.scenes.SuperScene
import com.example.filmatory.scenes.activities.StartScene

/**
 * ListSystem takes care of list actions
 *
 * @property apiSystem The api system to use
 * @property snackbarSystem The snackbar system to use
 * @property scene The scene to use
 */
class ListSystem(private val apiSystem: ApiSystem, private val snackbarSystem: SnackbarSystem, private val scene: SuperScene) {
    /**
     * Deletes a list
     *
     * @param listId The list ID
     */
    fun deleteList(listId : String){
        deleteListFromDB(listId)
    }

    /**
     * Deletes a list from the database
     *
     * @param listId The list ID
     */
    private fun deleteListFromDB(listId : String){
        val params: HashMap<String, String> = HashMap()
        params["listId"] = listId
        apiSystem.postListDelete(PostBaseOptions(null, null, params, ::snackBarResponse, ::onFailure))
    }

    /**
     * Shows a snackbar message
     *
     * @param string The message
     */
    private fun snackBarResponse(string : String?) {
        snackbarSystem.duration = 2000
        snackbarSystem.showSnackbarSuccess(string!!)
    }

    /**
     * Runs when API failed
     *
     * @param baseError The error message
     */
    private fun onFailure(baseError: BaseError) {
        snackbarSystem.showSnackbarFailure(baseError.message, ::retry, "Home")
    }

    /**
     * Sends the user back to the start page
     *
     */
    private fun retry() {
        val intent = Intent(scene, StartScene::class.java)
        scene.finish()
        scene.startActivity(intent)
    }
}