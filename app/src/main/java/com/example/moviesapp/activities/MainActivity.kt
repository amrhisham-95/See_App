package com.example.moviesapp.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.moviesapp.R
import com.example.moviesapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        }


    override fun onResume() {
    super.onResume()
    //To Add ToolBar(in ActionBar) for all fragments with appBarConfiguration
    setSupportActionBar(binding.toolbar)
    binding.toolbar.title = "See (Movies-TV)"
    binding.toolbar.setTitleTextColor(Color.WHITE)

}

    }