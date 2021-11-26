package com.app.gamesuitver2.media

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.URLUtil
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.core.view.isVisible
import com.app.gamesuitver2.R
import com.app.gamesuitver2.databinding.ActivityInternetVideoBinding
import com.app.gamesuitver2.view.activity.menu.MenuActivity

class InternetVideoActivity : AppCompatActivity() {

    companion object {
        private const val PLAYBACK_TIME_KEY = "playback_time"
    }

    private val videoView: VideoView by lazy {
        findViewById(R.id.video_view)
    }

    private var currentPosition = 0

    private lateinit var binding : ActivityInternetVideoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInternetVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = "https://sample-videos.com/video123/mp4/720/big_buck_bunny_720p_2mb.mp4"
        val uri = getMedia(url)

        videoView.setVideoURI(uri)

        val mediaController = MediaController(this)

        mediaController.setMediaPlayer(videoView)
        videoView.setMediaController(mediaController)

        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt(PLAYBACK_TIME_KEY)
        }

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))
        }
    }

    private fun startVideo() {
        videoView.setOnPreparedListener {
            binding.tvBuffer.isVisible = false

            if (currentPosition > 0) {
                videoView.seekTo(currentPosition)
            } else {
                videoView.seekTo(1)
            }

            videoView.start()
        }

        videoView.setOnCompletionListener {
            Toast.makeText(this, "Complete", Toast.LENGTH_SHORT).show()
            videoView.seekTo(0)
        }
    }

    private fun getMedia(url: String): Uri {
        return if (URLUtil.isValidUrl(url)) {
            Uri.parse(url)
        } else {
            val localVideo = "android.resource://$packageName/${R.raw.bumi}"
            Uri.parse(localVideo)
        }
    }

    override fun onStart() {
        super.onStart()
        startVideo()
    }

    override fun onStop() {
        super.onStop()
        videoView.stopPlayback()
    }

    override fun onPause() {
        super.onPause()
        videoView.pause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(PLAYBACK_TIME_KEY, videoView.currentPosition)
    }
}