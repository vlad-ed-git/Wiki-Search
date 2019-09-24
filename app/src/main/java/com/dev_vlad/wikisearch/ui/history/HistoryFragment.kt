package com.dev_vlad.wikisearch.ui.history

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.dev_vlad.wikisearch.R
import com.dev_vlad.wikisearch.adapters.ArticleListItemAdapter


class HistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view : View? = inflater.inflate(R.layout.fragment_history, container, false)

        val history_rv: RecyclerView? = view?.findViewById<RecyclerView>(R.id.history_rv)

        history_rv?.layoutManager = LinearLayoutManager(context)
        history_rv?.adapter = ArticleListItemAdapter()


        return view
    }

}
