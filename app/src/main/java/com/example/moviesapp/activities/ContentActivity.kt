package com.example.moviesapp.activities


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.example.moviesapp.R
import com.example.moviesapp.databinding.ActivityContentBinding
import com.example.moviesapp.ui.DrawerLocker
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ContentActivity : AppCompatActivity(), DrawerLocker {
    private lateinit var binding : ActivityContentBinding
    private lateinit var toggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_content)
        binding.lifecycleOwner = this


        makeToggleWithDrawerNavigationContentActivity()

        //to make drawer navigation available
        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)

        makeListenerWithNavigationDrawerItems()

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val findMenuItems = menuInflater
        findMenuItems.inflate(R.menu.menu2, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (toggle.onOptionsItemSelected(item)) {
            true
        }else{
            when (item.itemId) {
                R.id.signOutItem -> {
                    val intent = Intent (this, MainActivity::class.java)
                    startActivity(intent)
                    Firebase.auth.signOut()
                }
                R.id.searchItem -> {
                    val intent = Intent (this,SearchActivity::class.java)
                    startActivity(intent)
                }
            }
            super.onOptionsItemSelected(item)
        }

    }

    override fun onResume() {
        super.onResume()
        //To Add ToolBar(in ActionBar) for all fragments with appBarConfiguration
        setSupportActionBar(binding.toolbarContent)
        binding.toolbarContent.setTitle(R.string.welcome_content_fragment)
        binding.toolbarContent.setTitleTextColor(Color.WHITE)
    }

    override fun setDrawerLocked(shouldLock: Boolean) {
        if (shouldLock) {
            binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        } else {
            binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        }
    }

    fun getHideToggleButton(){
        binding.toolbarContent.navigationIcon = null
    }

    fun getToggleSync(){
        toggle.syncState()
    }

    private fun makeToggleWithDrawerNavigationContentActivity(){
        toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,binding.toolbarContent,
            R.string.open,
            R.string.close
        )

        //to change the color of toggle
        toggle.drawerArrowDrawable.color=resources.getColor(R.color.white)

        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

    }

    private fun makeListenerWithNavigationDrawerItems(){
        binding.navigationDrawerView.setNavigationItemSelectedListener {
            when (it.itemId) {

                R.id.SearchingItem -> {
                    val intent = Intent(this,SearchActivity::class.java)
                    startActivity(intent)
                }

                R.id.signOut -> {
                    Toast.makeText(
                        this,
                        "Sign In Again, If You Want To Get A Service",
                        Toast.LENGTH_LONG
                    ).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    Firebase.auth.signOut()
                }

            }
            true
        }
    }
}