package com.example.flippingtool

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.squareup.picasso.Picasso
import java.net.URL

/**
 *
 *
 *
 * @author Comp at 2.12.2020.
 **/

class PicassoImageLoader {

    fun getBitmap(url : String?) : Bitmap? {
        var bmp : Bitmap ? = null
        Picasso.get().load(url).into(object : com.squareup.picasso.Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                bmp =  bitmap
            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {}
        })
        return bmp
    }
}