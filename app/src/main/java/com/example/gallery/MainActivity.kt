package com.example.gallery

import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import android.graphics.BlurMaskFilter
import android.graphics.Color
import android.graphics.Paint
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var PICTURE: ImageView
    private lateinit var NAME_OF_THE_PICTURE: TextView
    private lateinit var NAME_OF_THE_CREATOR: TextView
    private lateinit var YEAR: TextView
    private lateinit var PREVIOUS: Button
    private lateinit var NEXT: Button
    private var currentExhibit = 0
    private lateinit var exhibitImages: TypedArray
    private lateinit var exhibitNames: TypedArray
    private lateinit var exhibitCreators: TypedArray
    private lateinit var exhibitYears: TypedArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PICTURE = findViewById(R.id.picture)
        NAME_OF_THE_PICTURE = findViewById(R.id.name_of_the_picture)
        NAME_OF_THE_CREATOR = findViewById(R.id.name_of_the_creator)
        YEAR = findViewById(R.id.year)
        PREVIOUS = findViewById(R.id.previous)
        NEXT = findViewById(R.id.next)

        NAME_OF_THE_PICTURE.bringToFront()
        NAME_OF_THE_CREATOR.bringToFront()
        YEAR.bringToFront()

        exhibitImages = resources.obtainTypedArray(R.array.exhibit_images)
        exhibitNames = resources.obtainTypedArray(R.array.exhibit_names)
        exhibitCreators = resources.obtainTypedArray(R.array.exhibit_creators)
        exhibitYears = resources.obtainTypedArray(R.array.exhibit_years)

        // Установите начальное изображение
        PICTURE.setImageResource(exhibitImages.getResourceId(currentExhibit, -1))

        NEXT.setOnClickListener {
            changeExhibit(1)
        }

        PREVIOUS.setOnClickListener {
            changeExhibit(-1)
        }

    }

    private fun changeExhibit(offset: Int) {
        currentExhibit = (currentExhibit + offset + exhibitImages.length()) % exhibitImages.length()

        // Установите новое изображение
        PICTURE.setImageResource(exhibitImages.getResourceId(currentExhibit, -1))

        // Установите новые значения текста
        NAME_OF_THE_PICTURE.text = resources.getStringArray(R.array.exhibit_names)[currentExhibit]
        NAME_OF_THE_CREATOR.text = resources.getStringArray(R.array.exhibit_creators)[currentExhibit]
        YEAR.text = resources.getStringArray(R.array.exhibit_years)[currentExhibit]
    }

    override fun onDestroy() {
        super.onDestroy()
        exhibitImages.recycle()
    }
}