package com.dev_vlad.wikisearch.ui.favorites


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.dev_vlad.wikisearch.R
import com.dev_vlad.wikisearch.WikiSearchApp
import com.dev_vlad.wikisearch.adapters.ArticleCardAdapter
import com.dev_vlad.wikisearch.managers.WikiManager
import kotlinx.android.synthetic.main.fragment_favorites.*
import org.jetbrains.anko.doAsync
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 */
class FavoritesFragment : Fragment() {


    private var wikiManager : WikiManager? = null

    private val articleCardAdapter: ArticleCardAdapter =  ArticleCardAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        wikiManager = (activity?.applicationContext as WikiSearchApp).wikiManager

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view : View? = inflater.inflate(R.layout.fragment_favorites, container, false)

        //because auto mapping cannot be done in fragments
        val favorites_rv: RecyclerView? = view?.findViewById<RecyclerView>(R.id.favorites_rv)


        favorites_rv?.layoutManager = LinearLayoutManager(context)
        favorites_rv?.adapter = articleCardAdapter


        return view
    }

    override fun onResume() {
        super.onResume()


        doAsync {
            val favoriteArticles = wikiManager!!.getFavorites()
            articleCardAdapter.currentResults.clear()
            if (favoriteArticles != null) {
                articleCardAdapter.currentResults.addAll(favoriteArticles)
                activity!!.runOnUiThread {
                    articleCardAdapter.notifyDataSetChanged()
                }
            }
        }

    }


}
