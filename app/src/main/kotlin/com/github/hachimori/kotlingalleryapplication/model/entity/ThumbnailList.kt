package com.github.hachimori.kotlingalleryapplication.model.entity

/**
 * Model class for storing data retrieved from 500px API "GET photos"
 * https://github.com/500px/api-documentation/blob/master/endpoints/photo/GET_photos.md
 *
 * Created by benhachimori on 2017/02/04.
 */
data class ThumbnailList (
        val photos: List<Thumbnail>,
        val total_pages: Int)

data class Thumbnail(var id: String, val image_url: String)
