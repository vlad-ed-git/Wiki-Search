package com.dev_vlad.wikisearch.ui.history

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.dev_vlad.wikisearch.R
import com.dev_vlad.wikisearch.WikiSearchApp
import com.dev_vlad.wikisearch.adapters.ArticleCardAdapter
import com.dev_vlad.wikisearch.adapters.ArticleListItemAdapter
import com.dev_vlad.wikisearch.managers.WikiManager
import org.jetbrains.anko.*


class HistoryFragment : Fragment() {

    private var wikiManager : WikiManager? = null

    private val articleListItemAdapter: ArticleListItemAdapter = ArticleListItemAdapter()


    init {
        //to use this's fragment's menu and not parent's menu
        setHasOptionsMenu(true)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)

        wikiManager = (activity?.applicationContext as WikiSearchApp).wikiManager

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view : View? = inflater.inflate(R.layout.fragment_history, container, false)

        val history_rv: RecyclerView? = view?.findViewById<RecyclerView>(R.id.history_rv)

        history_rv?.layoutManager = LinearLayoutManager(context)
        history_rv?.adapter = articleListItemAdapter


        return view
    }

    override fun onResume() {
        super.onResume()


        doAsync {
            val historyArticles = wikiManager!!.getHistory()
           articleListItemAdapter.currentResults.clear()
            if (historyArticles != null) {
                articleListItemAdapter.currentResults.addAll(historyArticles)
                activity!!.runOnUiThread {
                    articleListItemAdapter.notifyDataSetChanged()
                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.history_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.clear_history){
            activity!!.alert (
                R.string.confirm_clear_msg, R.string.confirm_title
            ){
              yesButton{
                  articleListItemAdapter.currentResults.clear()
                  doAsync {
                      wikiManager!!.clearHistory()
                  }
                  activity?.runOnUiThread{articleListItemAdapter.notifyDataSetChanged()}


              }

              noButton{} //do nothing

            }.show()
        }
        return true

    }


}
