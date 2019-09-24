package com.dev_vlad.wikisearch

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setSupportActionBar(search_act_toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        //handle back button
        if(item.itemId == android.R.id.home)
            finish()
        return true

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //inflate menu
        menuInflater.inflate(R.menu.search_menu, menu)
        //get search action item
        val searchActionMenuItem = menu!!.findItem(R.id.action_search)

        val searchManager =  getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView =  searchActionMenuItem!!.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        //make it expanded and focused when activity starts
        searchView.setIconifiedByDefault(false)
        searchView.requestFocus()

        //handle search query
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false //TODO
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return false //TODO
            }

        })


        return super.onCreateOptionsMenu(menu)
    }
}
