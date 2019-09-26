package com.dev_vlad.wikisearch.repositories

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper

class ArticleDatabaseOpenHelper(context : Context) : ManagedSQLiteOpenHelper(context, "ArticlesDatabase.db",null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        //todo
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
      //todo
    }


}