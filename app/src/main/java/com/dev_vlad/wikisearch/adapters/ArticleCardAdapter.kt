package com.dev_vlad.wikisearch.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev_vlad.wikisearch.R
import com.dev_vlad.wikisearch.rv_holders.CardHolder

class ArticleCardAdapter : RecyclerView.Adapter<CardHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
       var cardItem = LayoutInflater.from(parent.context).inflate(R.layout.article_card_item, parent, false)
        return CardHolder(cardItem)
    }

    override fun getItemCount(): Int {
        return 0 //TODO
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}