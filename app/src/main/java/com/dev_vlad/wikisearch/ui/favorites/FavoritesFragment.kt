package com.dev_vlad.wikisearch.ui.favorites


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
import com.dev_vlad.wikisearch.adapters.ArticleCardAdapter
import kotlinx.android.synthetic.main.fragment_favorites.*

/**
 * A simple [Fragment] subclass.
 */
class FavoritesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view : View? = inflater.inflate(R.layout.fragment_favorites, container, false)

        //because auto mapping cannot be done in fragments
        val favorites_rv: RecyclerView? = view?.findViewById<RecyclerView>(R.id.favorites_rv)


        favorites_rv?.layoutManager = LinearLayoutManager(context)
        favorites_rv?.adapter = ArticleCardAdapter()


        return view
    }


}
