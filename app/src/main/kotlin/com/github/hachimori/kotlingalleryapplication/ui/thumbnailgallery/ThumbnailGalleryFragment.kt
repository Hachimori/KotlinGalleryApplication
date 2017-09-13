package com.github.hachimori.kotlingalleryapplication.ui.thumbnailgallery

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.hachimori.kotlingalleryapplication.R
import com.github.hachimori.kotlingalleryapplication.databinding.FragmentThumbnailgalleryBinding
import com.github.hachimori.kotlingalleryapplication.model.entity.Thumbnail

/**
 * Created by benhachimori on 2017/09/11.
 */
class ThumbnailGalleryFragment: Fragment() {
    
    companion object {
        fun newInstance() = ThumbnailGalleryFragment()
    }
    
    private lateinit var binding: FragmentThumbnailgalleryBinding
    private lateinit var thumbnailAdapter: ThumbnailAdapter
    
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_thumbnailgallery, container, false)
        
        binding.thumbnailList.setLayoutManager(GridLayoutManager(getContext(), 2));
        binding.thumbnailList.setNestedScrollingEnabled(false);
        
        thumbnailAdapter = ThumbnailAdapter(ArrayList<Thumbnail>(), object: ThumbnailAdapter.OnThumbnailClickListener {
            override fun onThumbnailClicked(viewHolder: ThumbnailViewHolder, position: Int) {
                
                val thumbnail: Thumbnail = thumbnailAdapter.getItem(position)
                
                // Specify detail photo's id and image size
                // val detailFragment: DetailPhotoFragment = DetailPhotoFragment.newInstance(thumbnail.getId(), "4")
        
                // Display detail photo screen
                /*
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.container, detailFragment)
                        .hide(ThumbnailGalleryFragment.this)
                        .addToBackStack(null)
                        .commit()
                        */
            }
        });
        
        
        
        return binding.root
    }
}