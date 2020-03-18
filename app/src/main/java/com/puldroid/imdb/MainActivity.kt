package com.puldroid.imdb

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.google.android.material.navigation.NavigationView
import com.puldroid.imdb.movies.MoviesFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_dashboard.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toggle = ActionBarDrawerToggle(
            this,
            dashboardDrawer,
            dashboardToolbar,
            R.string.open,
            R.string.close
        )

        dashboardDrawer.addDrawerListener(toggle)
        toggle.syncState()
        setFragment()
    }

    private fun setFragment() {
        supportFragmentManager.commit {
            replace(R.id.container, MoviesFragment())
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return false
    }
}
