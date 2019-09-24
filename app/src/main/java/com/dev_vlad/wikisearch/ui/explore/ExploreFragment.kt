package com.dev_vlad.wikisearch.ui.explore

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.dev_vlad.wikisearch.R
import com.dev_vlad.wikisearch.SearchActivity
import com.dev_vlad.wikisearch.adapters.ArticleCardAdapter

class ExploreFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view : View? = inflater.inflate(R.layout.fragment_explore, container, false)

        //because auto mapping cannot be done in fragments
        val searchCardView: CardView? = view?.findViewById<CardView>(R.id.search_card_view)
        val exploreWikiRv: RecyclerView? = view?.findViewById<RecyclerView>(R.id.explore_wiki_rv)


        //user search
        searchCardView?.setOnClickListener {
            val searchIntent = Intent(context, SearchActivity::class.java)
            context?.startActivity(searchIntent)
        }

        exploreWikiRv?.layoutManager = LinearLayoutManager(context)
        exploreWikiRv?.adapter = ArticleCardAdapter()


        return view
    }

}
