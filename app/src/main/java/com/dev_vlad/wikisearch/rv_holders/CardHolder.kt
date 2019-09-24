package com.dev_vlad.wikisearch.rv_holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dev_vlad.wikisearch.R

class CardHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val article_img_iv: ImageView = itemView.findViewById(R.id.article_img_iv)
    private val article_desc_tv: TextView = itemView.findViewById(R.id.article_desc_tv)
}