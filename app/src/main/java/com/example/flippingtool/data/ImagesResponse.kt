package com.example.flippingtool.data

import com.example.flippingtool.data.images.Image
import com.google.gson.annotations.SerializedName

/**
 *
 *
 *
 * @author Comp at 6.12.2020.
 **/

data class ImagesResponse(
        @SerializedName("Page")
        val page : LinkedHashMap<String, Image>)