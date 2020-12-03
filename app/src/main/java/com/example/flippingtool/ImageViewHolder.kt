package com.example.flippingtool

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.flippingtool.adapter.BaseViewHolder
import com.example.flippingtool.images.Image
import com.example.flippingtool.images.ImageQuality
import com.example.flippingtool.images.ImagesGenerator
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_image.view.*

/**
 *
 *
 *
 * @author Comp at 3.12.2020.
 **/

class ImageViewHolder(itemView: View) : BaseViewHolder<Image>(itemView) {
    companion object {
        fun getViewHolder(viewGroup: ViewGroup) : ImageViewHolder {
            return ImageViewHolder(LayoutInflater.from(viewGroup.context).inflate(
                    R.layout.item_image, viewGroup, false
            ))
        }
    }

    fun bind(image: Image, position : Int) {
        super.bind(image, position)
        Picasso.get().load(ImagesGenerator.generateImageUrl(image, ImageQuality.HIGH)).into(itemView.imageView)
    }

}