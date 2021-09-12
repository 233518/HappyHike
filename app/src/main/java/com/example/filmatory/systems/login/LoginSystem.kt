package com.example.filmatory.systems.login

import android.widget.VideoView
import android.net.Uri
import android.view.View
import com.example.filmatory.R
import com.example.filmatory.scenes.LoginScene


class LoginSystem(loginScene: LoginScene) {
    private lateinit var videoView: VideoView
    var loginScene = loginScene

    init {
        runVideo()
    }

    private fun runVideo() {
        videoView = loginScene.findViewById<View>(R.id.videoView) as VideoView
        var uri = Uri.parse("android.resource://" + loginScene.packageName + "/" + R.raw.happyhike_bg)
        videoView.setVideoURI(uri)
        videoView.setOnPreparedListener { mp -> mp.isLooping = true }
        videoView.start()
    }

    private fun onResume() {
        videoView.resume()
    }
}

