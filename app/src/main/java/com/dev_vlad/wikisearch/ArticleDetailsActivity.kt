package com.dev_vlad.wikisearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View.GONE
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.dev_vlad.wikisearch.models.WikiPage
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_article_details.*

class ArticleDetailsActivity : AppCompatActivity() {

    private var currentPage: WikiPage? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_details)

        setSupportActionBar(article_details_toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        val wikiPagejsonstr:String? = intent.getStringExtra("wiki_page")
        currentPage  = Gson().fromJson<WikiPage>(wikiPagejsonstr, WikiPage::class.java)

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
            }
        }

        //display in webview
        article_details_wv.loadUrl(currentPage!!.fullurl)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        //handle back button
        if(item.itemId == android.R.id.home)
            finish()
        return true

    }


}
