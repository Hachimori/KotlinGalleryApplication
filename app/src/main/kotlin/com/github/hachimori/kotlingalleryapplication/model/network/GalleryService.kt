package com.github.hachimori.kotlingalleryapplication.model.network

import com.github.hachimori.kotlingalleryapplication.model.entity.DetailPhoto
import com.github.hachimori.kotlingalleryapplication.model.entity.ThumbnailList
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Defines endpoint for 500px Web API.
 *
 * Created by benhachimori on 2017/02/04.
 */
interface GalleryService {

    /**
     * Returns the list of thumbnail photos.
     * https://github.com/500px/api-documentation/blob/master/endpoints/photo/GET_photos.md
     *
     * @param feature Photo stream to be retrieved (i.e. "popular", "highest_rated", "upcoming", etc.)
     * @param page The page (1-indexed) of photo stream
     */
    @GET("photos")
    fun getThumbnailList(@Query("feature") feature: String, @Query("exclude") exclude: String, @Query("page") page: String): Flowable<ThumbnailList>

    /**
     * Returns the detailed information of the photo.
     * https://github.com/500px/api-documentation/blob/master/endpoints/photo/GET_photos_id.md
     *
     * @param id ID of photo
     * @param imageSize size of image (1: the smallest, 4: the largest)
     */
    @GET("photos/{id}")
    fun getDetailPhoto(@Path("id") id: String, @Query("image_size") imageSize: String): Flowable<DetailPhoto>
}