package com.dev_vlad.wikisearch.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev_vlad.wikisearch.R
import com.dev_vlad.wikisearch.models.WikiPage
import com.dev_vlad.wikisearch.rv_holders.ListItemHolder

class ArticleListItemAdapter: RecyclerView.Adapter<ListItemHolder>() {

    val currentResults : ArrayList<WikiPage> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemHolder {
        var listItem = LayoutInflater.from(parent.context).inflate(R.layout.article_list_item, parent, false)
        return ListItemHolder(listItem)
    }

    override fun getItemCount(): Int {
       return currentResults.size
    }

    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {
       var wikiPage = currentResults[position]
        holder.bindData(wikiPage)
    }


}