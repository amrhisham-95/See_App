package com.example.moviesapp.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.example.moviesapp.R
import com.example.moviesapp.activities.ContentActivity
import com.example.moviesapp.adapter.TabLayoutAdapter
import com.example.moviesapp.databinding.FragmentContentBinding
import com.example.moviesapp.ui.DrawerLocker
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContentFragment : Fragment() {

private lateinit var binding : FragmentContentBinding
    private lateinit var tabLayout : TabLayout
    private lateinit var viewPager : ViewPager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =DataBindingUtil.inflate(inflater,R.layout.fragment_content,container,false)

        //to hide navigation drawer from fragment you want to hide
        (activity as DrawerLocker?)!!.setDrawerLocked(false)

        //to open toggle from the layout toolbar
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //to appear toggle first with 3 vertical bars
        (activity as ContentActivity).getToggleSync()

        //to put title on toolbar
        (activity as ContentActivity).supportActionBar?.title = "See (Movies & TV)"

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabLayout = binding.tabLayout
        viewPager = binding.viewPager

        tabLayout.addTab(tabLayout.newTab().setText("Movies"))
        tabLayout.addTab(tabLayout.newTab().setText("Series"))

        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val tabLayoutAdapter =
            TabLayoutAdapter(requireContext(), childFragmentManager, tabLayout.tabCount)
        viewPager.adapter = tabLayoutAdapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

    }


    //to hide navigation drawer from fragment you want to hide
    override fun onDestroy() {
        super.onDestroy()
        (activity as DrawerLocker?)!!.setDrawerLocked(true)
    }
}