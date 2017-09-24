package com.github.hachimori.kotlingalleryapplication.util.scheduler

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by benhachimori on 2017/09/19.
 */
class SchedulerProvider private constructor() : ISchedulerProvider {

    override fun computation(): Scheduler = Schedulers.computation()

    override fun io(): Scheduler = Schedulers.io()

    override fun ui(): Scheduler = AndroidSchedulers.mainThread()
    
    companion object {

        private var INSTANCE: SchedulerProvider? = null

        val instance: SchedulerProvider
            @Synchronized get() {
                if (INSTANCE == null) INSTANCE = SchedulerProvider()
                return INSTANCE as SchedulerProvider
            }
    }
}
