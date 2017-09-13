package com.github.hachimori.kotlingalleryapplication.model.entity

/**
 * Model class for storing data retrieved from 500px API "GET photos/:id"
 * https://github.com/500px/api-documentation/blob/master/endpoints/photo/GET_photos_id.md
 *
 * Created by benhachimori on 2017/02/04.
 */
data class DetailPhoto(val photo: Photo) {

    val image_url: String
        get() = photo.image_url
}

data class Photo(val image_url: String)
