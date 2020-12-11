package com.example.flippingtool.data.images

import com.example.flippingtool.data.images.Image
import com.google.gson.annotations.SerializedName

/**
 * Data class used to map JSON object provided by the client. Maps only relevant data
 * @author Dzemal at 6.12.2020.
 **/

data class ImagesResponse(@SerializedName("Page") val page: LinkedHashMap<String, Image>)