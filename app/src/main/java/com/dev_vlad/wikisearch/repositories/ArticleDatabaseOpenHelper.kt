package com.dev_vlad.wikisearch.repositories

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class ArticleDatabaseOpenHelper(context : Context) : ManagedSQLiteOpenHelper(context, "ArticlesDatabase.db",null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable("Favorites",
            true,
            "id" to INTEGER + PRIMARY_KEY,
            "title" to TEXT,
            "url" to TEXT,
            "thumbnailjson" to TEXT)

        db?.createTable("History",
            true,
            "id" to INTEGER + PRIMARY_KEY,
            "title" to TEXT,
            "url" to TEXT,
            "thumbnailjson" to TEXT)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
      //todo update schema if needed
    }


}