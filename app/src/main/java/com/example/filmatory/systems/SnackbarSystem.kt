package com.example.filmatory.systems

import android.view.View
import com.example.filmatory.R
import com.google.android.material.snackbar.Snackbar

/**
 * SnackbarSystem handles showing snackbars on a view
 *
 * @property view The view to show the snackbar on
 * @author Sivert Heisholt - 233518
 */
class SnackbarSystem(private var view : View) {
    //View components
    private var context = view.context

    //Custom
    private var length = Snackbar.LENGTH_LONG
    var duration = 5000
        set(value) {field = value}
    private var messageTextColor = context.getColor(R.color.white)

    data class ActionSnack(
        val function: Any,
        val actionMsg: String,
        val actionMsgColor: Int,
    )

    /**
     * Show success snackbar
     *
     * @param message The message to put in snackbar
     * @author Sivert Heisholt - 233518
     */
    fun showSnackbarSuccess(message: String) {
        var color = context.getColor(R.color.snackbar_success)
        makeSnackbar(message, color, null)
    }

    /**
     * Show failure snackbar
     *
     * @param message The message to put in snackbar
     * @param function The function to callback after
     * @param actionMsg The message of the
     * @author Sivert Heisholt - 233518
     */
    fun showSnackbarFailure(message: String, function: () -> Unit, actionMsg: String) {
        val colorBackground = context.getColor(R.color.snackbar_failure)
        val colorActionMsg = context.getColor(R.color.white)
        val actionSnack = ActionSnack(function, actionMsg, colorActionMsg)

        makeSnackbar(message, colorBackground, actionSnack)
    }

    /**
     * Show info snackbar
     *
     * @param message The message to put in snackbar
     * @author Sivert Heisholt - 233518
     */
    fun showSnackbarInfo(message: String) {
        var color = context.getColor(R.color.snackbar_info)
        makeSnackbar(message, color, null)
    }

    /**
     * Show warning snackbar
     *
     * @param message The message to put in snackbar
     * @author Sivert Heisholt - 233518
     */
    fun showSnackbarWarning(message: String) {
        val color = context.getColor(R.color.snackbar_warning)
        makeSnackbar(message, color, null)
    }

    /**
     * Creates the snackbar and shows in on the view
     *
     * @param message The message to put in the snackbar
     * @param color The color of the snackbar
     * @param actionSnack The options for the action
     * @author Sivert Heisholt - 233518
     */
    private fun makeSnackbar(message: String, color: Int, actionSnack: ActionSnack?){
        val snackbar = Snackbar.make(view, message, length)
            .setAction(actionSnack?.actionMsg) {
                actionSnack?.function
            }.setActionTextColor(actionSnack!!.actionMsgColor)

        snackbar.duration = duration
        snackbar.view.setBackgroundColor(color)
        snackbar.show()
    }
}