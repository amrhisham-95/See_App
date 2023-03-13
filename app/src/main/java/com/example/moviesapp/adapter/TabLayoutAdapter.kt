package com.example.moviesapp.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.moviesapp.fragments.ContentMovieFragment
import com.example.moviesapp.fragments.ContentSeriesFragment


internal class TabLayoutAdapter(var context : Context, fm:FragmentManager, private var totalTabs: Int) : FragmentPagerAdapter(fm){
    override fun getCount(): Int {
        return totalTabs
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> {ContentMovieFragment()}
            else -> {ContentSeriesFragment()}
        }
    }

}