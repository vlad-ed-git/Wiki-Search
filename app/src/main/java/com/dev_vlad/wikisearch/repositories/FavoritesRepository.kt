package com.dev_vlad.wikisearch.repositories

import com.dev_vlad.wikisearch.models.WikiPage
import com.dev_vlad.wikisearch.models.WikiThumbnail
import com.google.gson.Gson
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.rowParser


class FavoritesRepository(private val articleDatabaseOpenHelper: ArticleDatabaseOpenHelper) {
    private val  TABLE_NAME = "Favorites"

    fun addFavorite(wikiPage: WikiPage){
        articleDatabaseOpenHelper.use {
                insert(TABLE_NAME ,
                    "id" to wikiPage.pageid,
                    "title" to wikiPage.title,
                    "url" to wikiPage.fullurl,
                    "thumbnailjson" to Gson().toJson(wikiPage.thumbnail))
            }

    }

    fun removeFavorite(pageId: Int){
        articleDatabaseOpenHelper.use {
            delete(TABLE_NAME, "id={pageId}", "pageId" to pageId)
        }

    }

    //NOTE there is a better way (performance - wise) to check if favorite
    fun isArticleFavorite(pageId: Int) : Boolean{
        val allFavorites = getAllFavorites()
        //if all favorites is null return false else check if page id -> favorites pages
        return allFavorites?.any { favoritePage ->
            favoritePage.pageid == pageId
        } ?: false
    }

    fun getAllFavorites():ArrayList<WikiPage>?{

        val allAllFavorites : ArrayList<WikiPage> = ArrayList()

        val articleRowParser = rowParser { id: Int, title: String, url: String, thumbnailJson: String ->

            val page = WikiPage()
            page.pageid = id
            page.title = title
            page.fullurl = url
            page.thumbnail = Gson().fromJson(thumbnailJson, WikiThumbnail::class.java)

            allAllFavorites.add(page)
        }

        return allAllFavorites
    }

}