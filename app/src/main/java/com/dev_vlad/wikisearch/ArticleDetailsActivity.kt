package com.dev_vlad.wikisearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.dev_vlad.wikisearch.managers.WikiManager
import com.dev_vlad.wikisearch.models.WikiPage
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_article_details.*
import java.lang.Exception

class ArticleDetailsActivity : AppCompatActivity() {

    private var wikiManager : WikiManager? = null

    private var isArticleFavorite  = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_details)

        setSupportActionBar(article_details_toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        wikiManager = (applicationContext as WikiSearchApp).wikiManager

        val wikiPagejsonstr:String? = intent.getStringExtra("wiki_page")
        val currentPage: WikiPage  = Gson().fromJson<WikiPage>(wikiPagejsonstr, WikiPage::class.java)

        //make sure android uses this webview
        article_details_wv.webViewClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                article_details_progressbar.visibility = GONE

                //add to history
                wikiManager?.addHistory(currentPage)
            }
        }



        //check if favorite
        if(wikiManager!!.checkIfArticleIsFavorite(currentPage.pageid!!)){
            favorites_fab.drawable.mutate().setTint(ContextCompat.getColor(this, android.R.color.white))
            isArticleFavorite = true
        }


        favorites_fab.setOnClickListener{

            var msg : String? = null

           try {

               isArticleFavorite = if (isArticleFavorite) {
                   //remove from favorites
                   favorites_fab.drawable.mutate()
                       .setTint(ContextCompat.getColor(this, android.R.color.darker_gray))
                   wikiManager!!.removeArticleFromFavorites(currentPage.pageid!!)

                   msg = "removed from favorites"

                   false
               } else {
                   //add to favorites
                   favorites_fab.drawable.mutate()
                       .setTint(ContextCompat.getColor(this, android.R.color.white))
                   wikiManager!!.addFavorite(currentPage)

                   msg = "added to favorites"

                   true
               }

           }catch (exc : Exception){
               Log.d("exc", exc.message!!)
               msg = "oops! something went wrong"
           }finally {
               Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
           }

        }

        //display in webview
        article_details_wv.loadUrl(currentPage.fullurl)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        //handle back button
        if(item.itemId == android.R.id.home)
            finish()
        return true

    }


}
