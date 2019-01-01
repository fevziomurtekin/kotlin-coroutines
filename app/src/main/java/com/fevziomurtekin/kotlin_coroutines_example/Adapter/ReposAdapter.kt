package com.fevziomurtekin.kotlin_coroutines_example.Adapter

import android.app.Activity
import android.opengl.Visibility
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.fevziomurtekin.kotlin_coroutines_example.Model.Repos.ReposResponse
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import com.fevziomurtekin.kotlin_coroutines_example.R
import android.widget.LinearLayout
import android.widget.TextView


class ReposAdapter: RecyclerView.Adapter<ReposAdapter.ViewHolder> {

    private var repoList: List<ReposResponse>? = null

    constructor(repoList:List<ReposResponse>){
        this.repoList=repoList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.repos_item, parent, false)
        return ReposAdapter.ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return repoList!!.size
    }

    fun getItem(pos: Int): ReposResponse {return repoList!!.get(pos)}

    override fun onBindViewHolder(holder: ReposAdapter.ViewHolder, pos: Int) {
        val item = getItem(pos)
        holder.name.setText(item.name)
        if(item.description==null) holder.descriptions.visibility= GONE
        else  holder.descriptions.text= item.description as CharSequence?
        holder.fork.setText(item.forks.toString())
        holder.star.setText(item.stargazers_count.toString())

    }

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        lateinit var name :TextView
        lateinit var descriptions:TextView
        lateinit var star :TextView
        lateinit var fork :TextView
        lateinit var itemViews : LinearLayout

        init {
            name = itemView.findViewById(R.id.name)
            descriptions = itemView.findViewById(R.id.description)
            star = itemView.findViewById(R.id.star)
            fork = itemView.findViewById(R.id.fork)
            itemViews = itemView.findViewById(R.id.item)

        }
    }

}