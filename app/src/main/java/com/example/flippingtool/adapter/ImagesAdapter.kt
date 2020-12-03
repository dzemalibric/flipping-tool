package com.example.flippingtool.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flippingtool.ImageViewHolder
import com.example.flippingtool.images.Image

/**
 *
 *
 *
 * @author Comp at 3.12.2020.
 **/

class ImagesAdapter : BaseRVAdapter() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ImageViewHolder.getViewHolder(parent)
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(viewHolder, position)
        (viewHolder as ImageViewHolder).bind(mItems[position] as Image, position)
    }
}