package com.example.flippingtool.data.images

import com.google.gson.annotations.SerializedName

/**
 *
 *
 *
 * @author Comp at 3.12.2020.
 **/

data class Image(@SerializedName("Id") val id : String,
                 @SerializedName("BasePath") val basePath : String,
                 @SerializedName("FileName") val fileName : String,
                 @SerializedName("ImageRatio") val ratio : String)