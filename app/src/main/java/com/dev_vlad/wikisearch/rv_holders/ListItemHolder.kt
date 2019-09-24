package com.dev_vlad.wikisearch.rv_holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dev_vlad.wikisearch.R

class ListItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val item_thumbnail: ImageView = itemView.findViewById(R.id.item_thumbnail)
    private val item_short_desc: TextView = itemView.findViewById(R.id.item_short_desc)
}