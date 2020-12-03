package com.example.flippingtool.images

/**
 *
 *
 *
 * @author Comp at 3.12.2020.
 **/

object ImagesGenerator {
    fun generateImages() : ArrayList<Image> {
        return arrayListOf(
            Image("5006426", "https://static.flippler.de/images/Media/Brochure/001/16/337114/", "0001.jpg"),
            Image("5006427", "https://static.flippler.de/images/Media/Brochure/001/16/337114/", "0002.jpg" ),
            Image("5006428", "https://static.flippler.de/images/Media/Brochure/001/16/337114/","0003.jpg"),
            Image("5006429", "https://static.flippler.de/images/Media/Brochure/001/16/337114/","0004.jpg"),
            Image("5006430", "https://static.flippler.de/images/Media/Brochure/001/16/337114/","0005.jpg"),
            Image("5006431", "https://static.flippler.de/images/Media/Brochure/001/16/337114/","0006.jpg"),
            Image("5006432", "https://static.flippler.de/images/Media/Brochure/001/16/337114/","0007.jpg"),
            Image("5006433", "https://static.flippler.de/images/Media/Brochure/001/16/337114/","0008.jpg"),
            Image("5006434", "https://static.flippler.de/images/Media/Brochure/001/16/337114/","0009.jpg"),
        )
    }

    fun generateImageUrl(image: Image, quality: ImageQuality) : String {
        return image.basePath + quality.quality + "/" + image.fileName
    }
}