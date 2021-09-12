package com.example.filmatory.systems.login

import android.net.Uri
import android.view.View
import android.widget.VideoView
import com.example.filmatory.R
import com.example.filmatory.scenes.RegisterScene

class RegisterSystem(registerScene: RegisterScene) {
    private lateinit var videoView: VideoView
    var registerScene = registerScene

    init {
        runVideo()
    }

    private fun runVideo() {
        videoView = registerScene.findViewById<View>(R.id.videoView) as VideoView
        var uri = Uri.parse("android.resource://" + registerScene.packageName + "/" + R.raw.happyhike_bg)
        videoView.setVideoURI(uri)
        videoView.setOnPreparedListener { mp -> mp.isLooping = true }
        videoView.start()
    }
}