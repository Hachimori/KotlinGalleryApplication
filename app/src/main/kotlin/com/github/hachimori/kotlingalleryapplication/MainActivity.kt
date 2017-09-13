package com.github.hachimori.kotlingalleryapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.hachimori.kotlingalleryapplication.ui.thumbnailgallery.ThumbnailGalleryFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.container, ThumbnailGalleryFragment.newInstance())
                    .commit()
        }
    }
}
