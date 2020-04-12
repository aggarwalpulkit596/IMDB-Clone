package com.puldroid.imdb

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.navigation.NavigationView
import com.puldroid.imdb.movies.MoviesFragment
import com.puldroid.imdb.tvshows.TvShowsFragment
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
        setFragment(MoviesFragment())
        dashboardNavigation.setNavigationItemSelectedListener(this)
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.container, fragment)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        dashboardDrawer.closeDrawer(GravityCompat.START)
        return when (item.itemId) {
            R.id.drawer_tv_shows -> {

                setFragment(TvShowsFragment())
                true
            }
            R.id.drawer_movies -> {
                setFragment(MoviesFragment())
                return true
            }
            else -> false
        }
    }
}
