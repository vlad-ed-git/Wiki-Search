package com.dev_vlad.wikisearch.managers

import com.dev_vlad.wikisearch.content_providers.ArticleDataProvider
import com.dev_vlad.wikisearch.models.WikiPage
import com.dev_vlad.wikisearch.models.WikiResult
import com.dev_vlad.wikisearch.repositories.FavoritesRepository
import com.dev_vlad.wikisearch.repositories.HistoryRepository

class WikiManager( private val articleDataProvider: ArticleDataProvider, private val favoritesRepository: FavoritesRepository, private val historyRepository: HistoryRepository) {

    //cache so that we do not always hit the sqlite
    var historyCache : ArrayList<WikiPage>? = null
    var favoritesCache : ArrayList<WikiPage>? = null

    /////////INSERTIONS

    fun addHistory(wikiPage:WikiPage){
        historyCache?.add(wikiPage)
        historyRepository.addHistory(wikiPage)
    }

    fun addFavorite(wikiPage: WikiPage){
        favoritesCache?.add(wikiPage)
        favoritesRepository.addFavorite(wikiPage)
    }


    /////////FETCHING

    fun getHistory() : ArrayList<WikiPage>?{
        if(historyCache == null)
            historyCache = historyRepository.getAllHistory()
        return historyCache
    }

    fun getFavorites() : ArrayList<WikiPage>?{
        if(favoritesCache == null)
            favoritesCache = favoritesRepository.getAllFavorites()
        return favoritesCache
    }


    fun searchArticles(term:String, skip:Int, take:Int, responseHandler: (result : WikiResult) -> Unit?) {
        return articleDataProvider.search(term, skip, take, responseHandler )
    }

    fun randomArticlesFetch(take:Int, responseHandler: (result : WikiResult) -> Unit?) {
        return articleDataProvider.getRandom(take, responseHandler)
    }


    ///////DELETING
    fun removeArticleFromHistory(pageId: Int){
        historyRepository.removeFromHistory(pageId)
        if(historyCache != null)
            historyCache =  historyCache!!.filter { it.pageid != pageId } as ArrayList<WikiPage>
    }

    fun removeArticleFromFavorites(pageId:Int){
        favoritesRepository.removeFavorite(pageId)
        if(favoritesCache != null)
            favoritesCache =  favoritesCache!!.filter { it.pageid != pageId } as ArrayList<WikiPage>
    }

    fun clearHistory(){
        historyCache?.clear()
        val history : ArrayList<WikiPage>?  = historyRepository.getAllHistory()
        history?.forEach{ historyRepository.removeFromHistory(it.pageid!!) }
    }


    ///////CHECKING
    fun checkIfArticleIsFavorite(pageId:Int): Boolean{
        return favoritesRepository.isArticleFavorite(pageId)
    }



}