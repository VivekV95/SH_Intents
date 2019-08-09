package com.vivekvishwanath.intentssh

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {

    companion object {
        const val IMAGE_REQUEST_CODE = 11
    }

    private var imageData = ImageData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val extra = intent.getSerializableExtra(ImageData.IMAGE_TAG)
        if (extra != null) {
            imageData = extra as ImageData
            image_view.setImageURI(imageData.getUri())
            image_name.setText(imageData.name)
        }

        find_image_button.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.type = "image/*"
            startActivityForResult(intent, IMAGE_REQUEST_CODE)
        }

        save_button.setOnClickListener {
            imageData.name = image_name.text.toString()
            val intent = Intent()
            intent.putExtra(ImageData.IMAGE_TAG, imageData)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            image_view.setImageURI(data?.data)
            imageData.setUri(data?.data)
        }
    }
}
