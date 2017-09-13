package com.github.hachimori.kotlingalleryapplication.ui.thumbnailgallery

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.github.hachimori.kotlingalleryapplication.R
import com.github.hachimori.kotlingalleryapplication.databinding.ItemThumbnailgalleryBinding
import com.github.hachimori.kotlingalleryapplication.model.entity.Thumbnail

/**
 * Created by benhachimori on 2017/02/05.
 */
class ThumbnailAdapter(
        private val thumbnailList: MutableList<Thumbnail>,
        private val listener: OnThumbnailClickListener) : RecyclerView.Adapter<ThumbnailViewHolder>() {

    interface OnThumbnailClickListener {
        fun onThumbnailClicked(viewHolder: ThumbnailViewHolder, position: Int)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ThumbnailViewHolder =
        ThumbnailViewHolder(
                DataBindingUtil.inflate(
                        LayoutInflater.from(viewGroup.context),
                        R.layout.item_thumbnailgallery,
                        viewGroup,
                        false)
        )


    override fun onBindViewHolder(viewHolder: ThumbnailViewHolder, position: Int) {
        
        // Set thumbnail image
        Glide
                .with(viewHolder.itemView.context)
                .load(thumbnailList[position].imageUrl)
                .apply(RequestOptions.centerCropTransform())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(viewHolder.binding.thumbnail)
        
        // When thumbnail image is clicked, just send the callback event to RecyclverView's owner 
        viewHolder.binding.thumbnail.setOnClickListener { view -> listener.onThumbnailClicked(viewHolder, viewHolder.adapterPosition) }
    }

    override fun getItemCount(): Int = thumbnailList.size 

    fun getItem(position: Int): Thumbnail = thumbnailList[position]

    fun addThumbnailList(thumbnailList: List<Thumbnail>) {
        this.thumbnailList.addAll(thumbnailList)
        notifyDataSetChanged()
    }
}



class ThumbnailViewHolder(val binding: ItemThumbnailgalleryBinding): RecyclerView.ViewHolder(binding.root)
