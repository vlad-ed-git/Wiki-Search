package com.dev_vlad.wikisearch.models

import com.dev_vlad.wikisearch.R

// kotlin has no concept of static
object Urls {

    private const val BASE_URL = "https://en.wikipedia.org/w/api.php"

    fun getSearchUrl(term : String, skip : Int, take : Int ) : String {
        return BASE_URL + "?action=query" +
                "&formatversion=2" +
                "&generator=prefixsearch" +
                "&gpssearch=$term" +
                "&gpslimit=$take" +
                "&gpsoffset=$skip" +
                "&prop=pageimages|info" +
                "&piprop=thumbnail|url" +
                "&pithumbsize=200" +
                "&pilimit=$take" +
                "&wbtterms=description" +
                "&format=json" +
                "&inprop=url"

    }

    fun getRandomUrl(take : Int) : String{
        return BASE_URL + "?action=query" +
                "&formatversion=2" +
                "&generator=random" +
                "&grnnamespace=0" +
                "&grnlimit=$take" +
                "&prop=pageimages|info" +
                "&pithumbsize=200" +
                "&format=json" +
                "&inprop=url"
    }
}