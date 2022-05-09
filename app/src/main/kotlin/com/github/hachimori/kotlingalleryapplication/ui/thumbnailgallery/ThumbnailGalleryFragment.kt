package com.github.hachimori.kotlingalleryapplication.ui.thumbnailgallery

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.github.hachimori.kotlingalleryapplication.R
import com.github.hachimori.kotlingalleryapplication.databinding.FragmentThumbnailgalleryBinding
import com.github.hachimori.kotlingalleryapplication.model.entity.Thumbnail
import com.github.hachimori.kotlingalleryapplication.model.entity.ThumbnailList

/**
 * Created by benhachimori on 2017/09/11.
 */
class ThumbnailGalleryFragment: Fragment() {

    companion object {
        fun newInstance() = ThumbnailGalleryFragment()
    }


    private lateinit var binding: FragmentThumbnailgalleryBinding
    private lateinit var viewModel: ThumbnailGalleryViewModel
    private lateinit var thumbnailAdapter: ThumbnailAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(this).get(ThumbnailGalleryViewModel::class.java)

        viewModel.getThumbnailListObservable().observe(this, Observer<ThumbnailList> { thumbnailList ->
            thumbnailList?.let { thumbnailAdapter.addThumbnailList(thumbnailList.photos) }
        })

        viewModel.getErrorObservable().observe(this, Observer<Unit> {
            Toast.makeText(context, R.string.thumbnailgallery_error, Toast.LENGTH_LONG).show()
        })

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_thumbnailgallery, container, false)
        binding.setView(this)
        binding.setViewmodel(viewModel)

        thumbnailAdapter = ThumbnailAdapter(ArrayList<Thumbnail>(), object : ThumbnailAdapter.OnThumbnailClickListener {
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

        binding.thumbnailList.setLayoutManager(GridLayoutManager(getContext(), 2))
        binding.thumbnailList.setNestedScrollingEnabled(false)
        binding.thumbnailList.adapter = thumbnailAdapter

        viewModel.loadThumbnailList()

        return binding.root
    }
}