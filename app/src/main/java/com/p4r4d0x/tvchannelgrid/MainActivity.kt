package com.p4r4d0x.tvchannelgrid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            launchHomeFragment()
        }

    }

    private fun launchHomeFragment() {
        supportFragmentManager.beginTransaction().replace(
            R.id.nav_fragment,
            HomeFragment()
        ).commit()
    }

    fun launchGalleryFragment() {
        supportFragmentManager.beginTransaction().replace(
            R.id.nav_fragment,
            GalleryFragment()
        ).commit()
    }


    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {
            finish()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

}
