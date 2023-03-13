package com.example.moviesapp.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//to hide navigation drawer from fragment you want to hide
interface DrawerLocker {
    fun setDrawerLocked(shouldLock: Boolean)
}