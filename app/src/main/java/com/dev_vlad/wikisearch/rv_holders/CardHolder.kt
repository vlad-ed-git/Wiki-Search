package com.dev_vlad.wikisearch.rv_holders

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dev_vlad.wikisearch.ArticleDetailsActivity
import com.dev_vlad.wikisearch.R
import com.dev_vlad.wikisearch.models.WikiPage
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class CardHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val articleImgIv: ImageView = itemView.findViewById(R.id.article_img_iv)
    private val articleDescTv: TextView = itemView.findViewById(R.id.article_desc_tv)

    private var wikiPage : WikiPage? = null

    //create a listener
    init{
        itemView.setOnClickListener { view: View? ->

            val detailsActivityIntent = Intent(view!!.context, ArticleDetailsActivity::class.java)
            val pageJson = Gson().toJson(wikiPage)
            detailsActivityIntent.putExtra("wiki_page", pageJson)
            itemView.context.startActivity(detailsActivityIntent)
        }
    }

    fun bindData(page: WikiPage){
        wikiPage = page
        articleDescTv.text = page.title
        Picasso.get().load(page.thumbnail?.source).into(articleImgIv)
    }
}