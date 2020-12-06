package com.example.flippingtool.data.images

/**
 *
 *
 *
 * @author Comp at 3.12.2020.
 **/
enum class ImageState(val quality: String) {
    DEFAULT("0"),
    THUMBNAIL("2"),
    PREVIEW("4"),
    MEDIUM("6"),
    HIGH("8"),
    ULTRA("9")
}