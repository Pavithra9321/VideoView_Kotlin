package com.viewview_kotlin

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.MediaController
import android.widget.ProgressBar
import android.widget.VideoView

class MainActivity : AppCompatActivity() {

    private var btnonce: Button?=null
    private var btnconti: Button?=null
    private var btnstop: Button?=null
    private var btnplay: Button?=null
    private var videoView : VideoView?=null
    private var progrss : ProgressBar?=null

    private var mediaController : MediaController?=null
    private var uri : Uri?=null
    private var isContinuously=false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnonce = findViewById(R.id.btnonce)
        btnconti = findViewById(R.id.btnconti)
        btnstop = findViewById(R.id.btnstop)
        btnplay = findViewById(R.id.btnplay)
        videoView = findViewById(R.id.vv)
        progrss = findViewById(R.id.progrss)

        mediaController = MediaController(this)
        mediaController?.setAnchorView(videoView)
        val uriPath = "https://www.demonuts.com/Demonuts/smallvideo.mp4" //update package name
        uri = Uri.parse(uriPath)
        playVideo()

        btnonce?.setOnClickListener{
            isContinuously=false
            playVideo()
        }

        btnconti?.setOnClickListener {
            isContinuously=true
            playVideo()
        }

        btnstop?.setOnClickListener {
            videoView?.pause()
        }

        btnplay?.setOnClickListener{
            videoView?.start()
        }

        videoView?.setOnPreparedListener{
            progrss?.visibility=View.GONE
        }

      videoView?.setOnCompletionListener {
          if(isContinuously){
              videoView?.start()
      }
      }

    }

    private fun playVideo(){
        progrss?.setVisibility(View.GONE)
        videoView?.setMediaController(mediaController)
        videoView?.setVideoURI(uri)
        videoView?.requestFocus()
        videoView?.start()
    }
}
