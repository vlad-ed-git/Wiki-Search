package com.dev_vlad.wikisearch

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.dev_vlad.wikisearch.ui.explore.ExploreFragment
import com.dev_vlad.wikisearch.ui.favorites.FavoritesFragment
import com.dev_vlad.wikisearch.ui.history.HistoryFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val exploreFragment:ExploreFragment
    private val favoritesFragment:FavoritesFragment
    private val historyFragment:HistoryFragment


    init{
        exploreFragment = ExploreFragment()
        favoritesFragment = FavoritesFragment()
        historyFragment = HistoryFragment()
    }


    //switch fragment based on id of selected menu-item in bottom nav menu
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener{ item ->
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)

        when(item.itemId)
        {
            R.id.explore_wiki -> transaction.replace(R.id.nav_host_frame, exploreFragment)
            R.id.favorites -> transaction.replace(R.id.nav_host_frame, favoritesFragment)
            R.id.user_history -> transaction.replace(R.id.nav_host_frame, historyFragment)

        }

        transaction.commit()

        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(main_act_toolbar)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.nav_host_frame, exploreFragment)
        transaction.commit()
    }


}
