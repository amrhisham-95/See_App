package com.example.moviesapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.moviesapp.R
import com.example.moviesapp.activities.ContentActivity
import com.example.moviesapp.databinding.FragmentWelcomeBinding
import com.example.moviesapp.ui.DrawerLocker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeFragment : Fragment() {
    private lateinit var binding : FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_welcome,container,false)

        //to hide navigation drawer from fragment you want to hide
        (activity as DrawerLocker?)!!.setDrawerLocked(true)

        //to close toggle from the layout toolbar
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        //to hide toggle from the layout toolbar
        (activity as ContentActivity).getHideToggleButton()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.apply {
            welcomeBtn.setOnClickListener {
                findNavController().navigate(R.id.action_welcomeFragment_to_contentFragment2)
            }
        }
    }

    //to hide navigation drawer from fragment you want to hide
    override fun onDestroy() {
        super.onDestroy()
        (activity as DrawerLocker?)!!.setDrawerLocked(false)
    }

}