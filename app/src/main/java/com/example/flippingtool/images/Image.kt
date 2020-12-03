package com.example.flippingtool.images

import com.example.flippingtool.adapter.RVModel

/**
 *
 *
 *
 * @author Comp at 3.12.2020.
 **/

data class Image(val id : String,
                 val basePath : String,
                 val fileName : String) : RVModel(ViewType.IMAGE)