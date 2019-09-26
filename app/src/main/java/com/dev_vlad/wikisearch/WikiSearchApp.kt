package com.dev_vlad.wikisearch

import android.app.Application
import com.dev_vlad.wikisearch.content_providers.ArticleDataProvider
import com.dev_vlad.wikisearch.managers.WikiManager
import com.dev_vlad.wikisearch.repositories.ArticleDatabaseOpenHelper
import com.dev_vlad.wikisearch.repositories.FavoritesRepository
import com.dev_vlad.wikisearch.repositories.HistoryRepository

class WikiSearchApp: Application() {
    private var dbOpenHelper : ArticleDatabaseOpenHelper? = null
    private var favoritesRepository:FavoritesRepository? = null
    private var historyRepository:HistoryRepository? = null
    private var articleDataProvider:ArticleDataProvider? = null
    public var wikiManager:WikiManager? = null

    override fun onCreate() {
        super.onCreate()

        dbOpenHelper = ArticleDatabaseOpenHelper(applicationContext)
        favoritesRepository = FavoritesRepository(dbOpenHelper!!)
        historyRepository = HistoryRepository(dbOpenHelper!!)
        articleDataProvider = ArticleDataProvider()
        wikiManager = WikiManager(articleDataProvider!!, favoritesRepository!!, historyRepository!!)

    }

}