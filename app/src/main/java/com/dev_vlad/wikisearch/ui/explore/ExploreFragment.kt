package com.dev_vlad.wikisearch.ui.explore

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.dev_vlad.wikisearch.R
import com.dev_vlad.wikisearch.SearchActivity
import com.dev_vlad.wikisearch.adapters.ArticleCardAdapter
import com.dev_vlad.wikisearch.content_providers.ArticleDataProvider
import com.dev_vlad.wikisearch.models.WikiResult
import kotlinx.android.synthetic.main.fragment_explore.*
import java.lang.Exception

class ExploreFragment : Fragment() {

    private val articleCardAdapter: ArticleCardAdapter =  ArticleCardAdapter()
    private val articleDataProvider : ArticleDataProvider = ArticleDataProvider()
    private  var swipe_to_refresh:SwipeRefreshLayout? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view : View? = inflater.inflate(R.layout.fragment_explore, container, false)

        //because auto mapping cannot be done in fragments
        val searchCardView: CardView? = view?.findViewById(R.id.search_card_view)
        val exploreWikiRv: RecyclerView? = view?.findViewById(R.id.explore_wiki_rv)
        swipe_to_refresh = view?.findViewById(R.id.swipe_to_refresh)


        //user search
        searchCardView?.setOnClickListener {
            val searchIntent = Intent(context, SearchActivity::class.java)
            context?.startActivity(searchIntent)
        }

        exploreWikiRv?.layoutManager = LinearLayoutManager(context)
        exploreWikiRv?.adapter = articleCardAdapter


        swipe_to_refresh!!.setOnRefreshListener {

            swipe_to_refresh!!.isRefreshing = true
            getRandomArticles()
        }
        //call first time
        getRandomArticles()
        return view
    }

    private fun getRandomArticles(){
        swipe_to_refresh!!.isRefreshing = true

        try {
            articleDataProvider.getRandom(15) { wikiResult ->
                articleCardAdapter.currentResults.clear()
                //done asynchronously

                articleCardAdapter.currentResults.addAll(wikiResult.query!!.pages)
                activity!!.runOnUiThread {
                    articleCardAdapter.notifyDataSetChanged()
                    swipe_to_refresh!!.isRefreshing = false
                }
            }
        }catch (exc : Exception){
            Log.d("Exception thrown", exc.message!!)
        }

    }

}
