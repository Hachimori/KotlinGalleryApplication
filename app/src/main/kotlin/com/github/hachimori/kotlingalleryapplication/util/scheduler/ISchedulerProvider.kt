package com.github.hachimori.kotlingalleryapplication.util.scheduler

import io.reactivex.Scheduler

/**
 * Created by benhachimori on 2017/09/19.
 */
interface ISchedulerProvider {
    fun ui(): Scheduler
    fun computation(): Scheduler
    fun io(): Scheduler
}