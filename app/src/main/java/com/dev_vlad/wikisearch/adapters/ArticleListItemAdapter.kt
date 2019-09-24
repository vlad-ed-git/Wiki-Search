package com.dev_vlad.wikisearch.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev_vlad.wikisearch.R
import com.dev_vlad.wikisearch.rv_holders.ListItemHolder

class ArticleListItemAdapter: RecyclerView.Adapter<ListItemHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemHolder {
        var listItem = LayoutInflater.from(parent.context).inflate(R.layout.article_list_item, parent, false)
        return ListItemHolder(listItem)
    }

    override fun getItemCount(): Int {
       return 0 //TODO
    }

    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}