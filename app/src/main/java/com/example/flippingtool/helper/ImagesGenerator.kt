package com.example.flippingtool.helper

import com.example.flippingtool.data.images.ImagesResponse
import com.example.flippingtool.data.images.Image
import com.example.flippingtool.data.images.ImageState
import com.google.gson.Gson

/**
 * @author Dzemal at 3.12.2020.
 **/

object ImagesGenerator {

    val json = "{\n" +
            "\t\"Id\": 337114,\n" +
            "\t\"Name\": \"ALDI Vorschau\",\n" +
            "\t\"ItemTypeID\": 1,\n" +
            "\t\"MemberOfGroupID\": 30369,\n" +
            "\t\"PublisherID\": 16,\n" +
            "\t\"PublisherName\": \"Aldi Nord\",\n" +
            "\t\"PublisherNameNormalized\": \"Aldi-Nord\",\n" +
            "\t\"CompanyBackgroundColor\": \"rgba(11,163,213,0.85)\",\n" +
            "\t\"Categories\": \"1,2\",\n" +
            "\t\"Highlighted\": false,\n" +
            "\t\"HighlightedBrand\": false,\n" +
            "\t\"ItemBrandValues\": \"1-7,2-5\",\n" +
            "\t\"ShowFrom\": \"2019-04-29T09:31:00\",\n" +
            "\t\"ShowTo\": \"2019-05-13T09:31:00\",\n" +
            "\t\"ValidFrom\": \"2019-05-06T00:00:00\",\n" +
            "\t\"ValidTo\": \"2019-05-11T23:59:00\",\n" +
            "\t\"IsFavorite\": false,\n" +
            "\t\"IsFollowed\": false,\n" +
            "\t\"IsViewed\": false,\n" +
            "\t\"IsNational\": false,\n" +
            "\t\"TotalViewCount\": 2,\n" +
            "\t\"TotalLikeCount\": 0,\n" +
            "\t\"TotalPageCount\": 10,\n" +
            "\t\"Page\": {\n" +
            "    \t\"1\": {\n" +
            "        \t\"Id\": 5006426,\n" +
            "        \t\"BasePath\": \"https://static.flippler.de/images/Media/Brochure/001/16/337114/\",\n" +
            "        \t\"FileName\": \"0001.jpg\",\n" +
            "        \t\"Order\": 1,\n" +
            "        \t\"DefaultItem\": true,\n" +
            "        \t\"ImageRatio\": 0.7072317262830482,\n" +
            "        \t\"IsDoublePage\": false,\n" +
            "        \t\"Tags\": []\n" +
            "    \t},\n" +
            "    \t\"2\": {\n" +
            "        \t\"Id\": 5006427,\n" +
            "        \t\"BasePath\": \"https://static.flippler.de/images/Media/Brochure/001/16/337114/\",\n" +
            "        \t\"FileName\": \"0002.jpg\",\n" +
            "        \t\"Order\": 2,\n" +
            "        \t\"DefaultItem\": false,\n" +
            "        \t\"ImageRatio\": 0.7072317262830482,\n" +
            "        \t\"IsDoublePage\": false,\n" +
            "        \t\"Tags\": []\n" +
            "    \t},\n" +
            "    \t\"3\": {\n" +
            "        \t\"Id\": 5006428,\n" +
            "        \t\"BasePath\": \"https://static.flippler.de/images/Media/Brochure/001/16/337114/\",\n" +
            "        \t\"FileName\": \"0003.jpg\",\n" +
            "        \t\"Order\": 3,\n" +
            "        \t\"DefaultItem\": false,\n" +
            "        \t\"ImageRatio\": 0.7072317262830482,\n" +
            "        \t\"IsDoublePage\": false,\n" +
            "        \t\"Tags\": []\n" +
            "    \t},\n" +
            "    \t\"4\": {\n" +
            "        \t\"Id\": 5006429,\n" +
            "        \t\"BasePath\": \"https://static.flippler.de/images/Media/Brochure/001/16/337114/\",\n" +
            "        \t\"FileName\": \"0004.jpg\",\n" +
            "        \t\"Order\": 4,\n" +
            "        \t\"DefaultItem\": false,\n" +
            "        \t\"ImageRatio\": 0.7072317262830482,\n" +
            "        \t\"IsDoublePage\": false,\n" +
            "        \t\"Tags\": []\n" +
            "    \t},\n" +
            "    \t\"5\": {\n" +
            "        \t\"Id\": 5006430,\n" +
            "        \t\"BasePath\": \"https://static.flippler.de/images/Media/Brochure/001/16/337114/\",\n" +
            "        \t\"FileName\": \"0005.jpg\",\n" +
            "        \t\"Order\": 5,\n" +
            "        \t\"DefaultItem\": false,\n" +
            "        \t\"ImageRatio\": 0.7072317262830482,\n" +
            "        \t\"IsDoublePage\": false,\n" +
            "        \t\"Tags\": []\n" +
            "    \t},\n" +
            "    \t\"6\": {\n" +
            "        \t\"Id\": 5006431,\n" +
            "        \t\"BasePath\": \"https://static.flippler.de/images/Media/Brochure/001/16/337114/\",\n" +
            "        \t\"FileName\": \"0006.jpg\",\n" +
            "        \t\"Order\": 6,\n" +
            "        \t\"DefaultItem\": false,\n" +
            "        \t\"ImageRatio\": 0.7072317262830482,\n" +
            "        \t\"IsDoublePage\": false,\n" +
            "        \t\"Tags\": []\n" +
            "    \t},\n" +
            "    \t\"7\": {\n" +
            "        \t\"Id\": 5006432,\n" +
            "        \t\"BasePath\": \"https://static.flippler.de/images/Media/Brochure/001/16/337114/\",\n" +
            "        \t\"FileName\": \"0007.jpg\",\n" +
            "        \t\"Order\": 7,\n" +
            "        \t\"DefaultItem\": false,\n" +
            "        \t\"ImageRatio\": 0.7072317262830482,\n" +
            "        \t\"IsDoublePage\": false,\n" +
            "        \t\"Tags\": []\n" +
            "    \t},\n" +
            "    \t\"8\": {\n" +
            "        \t\"Id\": 5006433,\n" +
            "        \t\"BasePath\": \"https://static.flippler.de/images/Media/Brochure/001/16/337114/\",\n" +
            "        \t\"FileName\": \"0008.jpg\",\n" +
            "        \t\"Order\": 8,\n" +
            "        \t\"DefaultItem\": false,\n" +
            "        \t\"ImageRatio\": 0.7072317262830482,\n" +
            "        \t\"IsDoublePage\": false,\n" +
            "        \t\"Tags\": []\n" +
            "    \t},\n" +
            "    \t\"9\": {\n" +
            "        \t\"Id\": 5006434,\n" +
            "        \t\"BasePath\": \"https://static.flippler.de/images/Media/Brochure/001/16/337114/\",\n" +
            "        \t\"FileName\": \"0009.jpg\",\n" +
            "        \t\"Order\": 9,\n" +
            "        \t\"DefaultItem\": false,\n" +
            "        \t\"ImageRatio\": 0.7072317262830482,\n" +
            "        \t\"IsDoublePage\": false,\n" +
            "        \t\"Tags\": []\n" +
            "    \t},\n" +
            "    \t\"10\": {\n" +
            "        \t\"Id\": 5006435,\n" +
            "        \t\"BasePath\": \"https://static.flippler.de/images/Media/Brochure/001/16/337114/\",\n" +
            "        \t\"FileName\": \"0010.jpg\",\n" +
            "        \t\"Order\": 10,\n" +
            "        \t\"DefaultItem\": false,\n" +
            "        \t\"ImageRatio\": 0.7072317262830482,\n" +
            "        \t\"IsDoublePage\": false,\n" +
            "        \t\"Tags\": []\n" +
            "    \t},\n" +
            "    \t\"11\": {\n" +
            "        \t\"Id\": 5006436,\n" +
            "        \t\"BasePath\": \"https://static.flippler.de/images/Media/Brochure/001/16/337114/\",\n" +
            "        \t\"FileName\": \"0011.jpg\",\n" +
            "        \t\"Order\": 10,\n" +
            "        \t\"DefaultItem\": false,\n" +
            "        \t\"ImageRatio\": 0.7072317262830482,\n" +
            "        \t\"IsDoublePage\": false,\n" +
            "        \t\"Tags\": []\n" +
            "    \t},\n" +
            "    \t\"12\": {\n" +
            "        \t\"Id\": 5006437,\n" +
            "        \t\"BasePath\": \"https://static.flippler.de/images/Media/Brochure/001/16/337114/\",\n" +
            "        \t\"FileName\": \"0012.jpg\",\n" +
            "        \t\"Order\": 10,\n" +
            "        \t\"DefaultItem\": false,\n" +
            "        \t\"ImageRatio\": 0.7072317262830482,\n" +
            "        \t\"IsDoublePage\": false,\n" +
            "        \t\"Tags\": []\n" +
            "    \t},\n" +
            "    \t\"13\": {\n" +
            "        \t\"Id\": 5006438,\n" +
            "        \t\"BasePath\": \"https://static.flippler.de/images/Media/Brochure/001/16/337114/\",\n" +
            "        \t\"FileName\": \"0013.jpg\",\n" +
            "        \t\"Order\": 10,\n" +
            "        \t\"DefaultItem\": false,\n" +
            "        \t\"ImageRatio\": 0.7072317262830482,\n" +
            "        \t\"IsDoublePage\": false,\n" +
            "        \t\"Tags\": []\n" +
            "    \t},\n" +
            "    \t\"14\": {\n" +
            "        \t\"Id\": 5006439,\n" +
            "        \t\"BasePath\": \"https://static.flippler.de/images/Media/Brochure/001/16/337114/\",\n" +
            "        \t\"FileName\": \"0014.jpg\",\n" +
            "        \t\"Order\": 10,\n" +
            "        \t\"DefaultItem\": false,\n" +
            "        \t\"ImageRatio\": 0.7072317262830482,\n" +
            "        \t\"IsDoublePage\": false,\n" +
            "        \t\"Tags\": []\n" +
            "    \t},\n" +
            "    \t\"15\": {\n" +
            "        \t\"Id\": 5006440,\n" +
            "        \t\"BasePath\": \"https://static.flippler.de/images/Media/Brochure/001/16/337114/\",\n" +
            "        \t\"FileName\": \"0015.jpg\",\n" +
            "        \t\"Order\": 10,\n" +
            "        \t\"DefaultItem\": false,\n" +
            "        \t\"ImageRatio\": 0.7072317262830482,\n" +
            "        \t\"IsDoublePage\": false,\n" +
            "        \t\"Tags\": []\n" +
            "    \t},\n" +
            "    \t\"16\": {\n" +
            "        \t\"Id\": 5006441,\n" +
            "        \t\"BasePath\": \"https://static.flippler.de/images/Media/Brochure/001/16/337114/\",\n" +
            "        \t\"FileName\": \"0016.jpg\",\n" +
            "        \t\"Order\": 10,\n" +
            "        \t\"DefaultItem\": false,\n" +
            "        \t\"ImageRatio\": 0.7072317262830482,\n" +
            "        \t\"IsDoublePage\": false,\n" +
            "        \t\"Tags\": []\n" +
            "    \t},\n" +
            "    \t\"17\": {\n" +
            "        \t\"Id\": 5006442,\n" +
            "        \t\"BasePath\": \"https://static.flippler.de/images/Media/Brochure/001/16/337114/\",\n" +
            "        \t\"FileName\": \"0017.jpg\",\n" +
            "        \t\"Order\": 10,\n" +
            "        \t\"DefaultItem\": false,\n" +
            "        \t\"ImageRatio\": 0.7072317262830482,\n" +
            "        \t\"IsDoublePage\": false,\n" +
            "        \t\"Tags\": []\n" +
            "    \t},\n" +
            "    \t\"18\": {\n" +
            "        \t\"Id\": 5006443,\n" +
            "        \t\"BasePath\": \"https://static.flippler.de/images/Media/Brochure/001/16/337114/\",\n" +
            "        \t\"FileName\": \"0018.jpg\",\n" +
            "        \t\"Order\": 10,\n" +
            "        \t\"DefaultItem\": false,\n" +
            "        \t\"ImageRatio\": 0.7072317262830482,\n" +
            "        \t\"IsDoublePage\": false,\n" +
            "        \t\"Tags\": []\n" +
            "    \t}\n" +
            "\t}\n" +
            "}\n"

    /**
     * Generates all images from [json] provided in "Page"
     */
    fun generateImages() : LinkedHashMap<String, Image> {
        val imagesResponse = Gson().fromJson(json, ImagesResponse::class.java)
        return imagesResponse.page
    }

    /**
     * Method used to generate link with provided [quality]
     */
    fun generateImageUrl(image: Image, quality: ImageState) : String {
        return image.basePath + quality.quality + "/" + image.fileName
    }
}