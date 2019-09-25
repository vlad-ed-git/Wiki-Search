package com.dev_vlad.wikisearch.content_providers

import com.dev_vlad.wikisearch.R
import com.dev_vlad.wikisearch.models.Urls
import com.dev_vlad.wikisearch.models.WikiResult
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.httpGet
import com.google.gson.Gson
import java.io.Reader

class ArticleDataProvider {


    //set user agent header
    init {
        FuelManager.instance.baseHeaders = mapOf("User-Agent" to "Wiki Search by Vlad")
    }


    /*
    ***subclass allows fuel lib to realize what json data it is serializing and
    * how (gson) it should serialize it
     */
    class WikipediaDataDeserializer : ResponseDeserializable<WikiResult>{
        override fun deserialize(reader: Reader): WikiResult? {
            return Gson().fromJson(reader, WikiResult::class.java)
        }
    }


    // in kotlin _ means these objects /parameters are not used and should not be processed
    fun search(term:String, skip:Int, take:Int, responseHandler: (result : WikiResult) -> Unit?){
        Urls.getSearchUrl(term, skip, take).httpGet().responseObject(WikipediaDataDeserializer()){
                _, response : Response, result ->

            if(response.statusCode != 200){
                throw Exception("unable to get articles")
            }
            val(data, _) = result
            responseHandler.invoke(data as WikiResult)
        }
    }

    fun getRandom(take:Int, responseHandler: (result : WikiResult) -> Unit?){
        Urls.getRandomUrl(take).httpGet().responseObject(WikipediaDataDeserializer()){
                _, response : Response, result ->

            if(response.statusCode != 200){
                throw Exception("unable to get articles")
            }

            val(data, _) = result
            responseHandler.invoke(data as WikiResult)
        }
    }
}