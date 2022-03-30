package org.dt.bexon.topics.ui.main

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import org.dt.bexon.topics.R
import org.dt.bexon.topics.model.TopicData


class TopicAdapter(arrayData: List<TopicData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listData: List<TopicData> = arrayData

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return TopicViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder: ${listData[position].title}")
        if (holder is TopicViewHolder) {
            holder.textView?.text = listData[position].title
            holder.itemView.tag = position
            val builder: CustomTabsIntent.Builder = CustomTabsIntent.Builder()
            val customTabsIntent: CustomTabsIntent = builder.build()
            holder.itemView.setOnClickListener {
                val uri = if (listData[position].link.startsWith("//")){
                    Uri.parse("https:${listData[position].link}")
                } else {
                    Uri.parse(listData[position].link)
                }
                customTabsIntent.launchUrl(holder.itemView.context, uri)
            }
        }
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    companion object {
        private val TAG = TopicAdapter::class.java.name
    }
}

class TopicViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var textView: TextView? = null

    init {
        textView = view.findViewById(R.id.item_text)
    }
}