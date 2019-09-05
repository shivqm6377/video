package com.example.android.videos

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URI

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener()
        {
            var intent=Intent()
            intent.setType("video/*")
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(intent,101)
        }
    }

    override fun onActivityResult(requestCode:Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode,resultCode, data)
      if(resultCode == Activity.RESULT_OK && data!=null)
      {
          if(requestCode == 101)

              {
                  var uri: Uri = data.data!!
                  var selectedImage:String=getPath(uri)
                  if(selectedImage!=null)
                  {
                      videoView.setVideoPath(selectedImage)
                      var mediaController:MediaController=MediaController(this)
                      videoView.setMediaController(mediaController)
                      videoView.start()
                  }
              }
      }
    }

    private fun getPath(uri: Uri): String{
        var projectionArray= arrayOf(MediaStore.Video.Media.DATA)
        var cursor: Cursor? =applicationContext.contentResolver.query(uri,projectionArray,null,null,null)
         if(cursor!=null) {
            val columnIndex:Int=cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
            cursor.moveToFirst()
            return cursor.getString(columnIndex)

        }
         else {
            return ""
        }
    }
}
