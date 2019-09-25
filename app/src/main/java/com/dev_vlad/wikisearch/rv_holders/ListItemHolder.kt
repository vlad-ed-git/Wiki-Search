package com.dev_vlad.wikisearch.rv_holders

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dev_vlad.wikisearch.ArticleDetailsActivity
import com.dev_vlad.wikisearch.R
import com.dev_vlad.wikisearch.models.WikiPage
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class ListItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val itemThumbnail: ImageView = itemView.findViewById(R.id.item_thumbnail)
    private val itemShortDesc: TextView = itemView.findViewById(R.id.item_short_desc)

    private var wikiPage : WikiPage? = null

    //create a listener
    init{
        itemView.setOnClickListener { view: View? ->

            val detailsActivityIntent = Intent(view!!.context, ArticleDetailsActivity::class.java)
            var pageJson = Gson().toJson(wikiPage)
            detailsActivityIntent.putExtra("wiki_page", pageJson)
            itemView.context.startActivity(detailsActivityIntent)
        }
    }

    fun bindData(page: WikiPage){
        wikiPage = page
        itemShortDesc.text = page.title
        Picasso.get().load(page.thumbnail?.source).into(itemThumbnail)
    }
}