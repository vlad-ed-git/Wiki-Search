package com.dev_vlad.wikisearch.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev_vlad.wikisearch.R
import com.dev_vlad.wikisearch.models.WikiPage
import com.dev_vlad.wikisearch.rv_holders.CardHolder

class ArticleCardAdapter : RecyclerView.Adapter<CardHolder>() {

    val currentResults : ArrayList<WikiPage> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
       val cardItem = LayoutInflater.from(parent.context).inflate(R.layout.article_card_item, parent, false)
        return CardHolder(cardItem)
    }

    override fun getItemCount(): Int {
        return currentResults.size
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
       val wikiPage : WikiPage  = currentResults[position]
        holder.bindData(wikiPage)
    }


}