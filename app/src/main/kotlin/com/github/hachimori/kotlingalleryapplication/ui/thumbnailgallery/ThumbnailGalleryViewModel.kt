package com.github.hachimori.kotlingalleryapplication.ui.thumbnailgallery

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.github.hachimori.kotlingalleryapplication.model.entity.ThumbnailList
import com.github.hachimori.kotlingalleryapplication.model.network.ServiceGenerator
import com.github.hachimori.kotlingalleryapplication.util.scheduler.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by benhachimori on 2017/09/16.
 */
class ThumbnailGalleryViewModel: ViewModel() {
    
    private var currentIndex = 1
    private val thumbnailListObservable: MutableLiveData<ThumbnailList> = MutableLiveData()
    private val errorObservable: MutableLiveData<Unit> = MutableLiveData()
    
    private val disposables = CompositeDisposable();
    
    override fun onCleared() {
        disposables.clear()
    }
    
    fun getThumbnailListObservable(): LiveData<ThumbnailList> =
            thumbnailListObservable
        
    fun getErrorObservable(): LiveData<Unit> =
            errorObservable
    
    fun loadThumbnailList() {
        disposables.add(ServiceGenerator
                .INSTANCE
                .getThumbnailList("popular", "nude", currentIndex.toString())
                .subscribeOn(SchedulerProvider.instance.io())
                .observeOn(SchedulerProvider.instance.ui())
                .subscribe(
                        {
                            thumbnailListObservable.value = it
                            currentIndex++
                        },
                        { errorObservable.value = Unit } 
                )
        )
        
        currentIndex++
    }
}