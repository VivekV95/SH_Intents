package com.vivekvishwanath.intentssh

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val ADD_IMAGE_CODE = 10
    }

    private val imageList = mutableListOf<ImageData>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        add_movie_button.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            startActivityForResult(intent, ADD_IMAGE_CODE)
        }
    }

    fun createTextView(imageData: ImageData, index: Int): TextView {
        val textview = TextView(this)
        textview.textSize = 20f
        textview.text = imageData.name

        textview.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra(ImageData.IMAGE_TAG, imageData)
            startActivityForResult(intent, ADD_IMAGE_CODE)
        }

        return textview
    }

    fun populateList(index: Int) {
        movie_list_layout.removeAllViews()
        for (imageData in imageList) {
            movie_list_layout.addView(createTextView(imageData, index))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ADD_IMAGE_CODE && resultCode == Activity.RESULT_OK) {
            val imageData = data?.getSerializableExtra(ImageData.IMAGE_TAG) as ImageData
            imageList.add(imageData)
            populateList(imageList.size - 1)
        }
    }
}
