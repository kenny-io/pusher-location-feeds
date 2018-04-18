package com.projects.ekene.pusher_location_feeds

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by EKENE on 4/12/2018.
 */
class Adapter (private val mContext: Context)
    : RecyclerView.Adapter<Adapter.MyViewHolder>() {

    private var arrayList: ArrayList<String> = ArrayList()

    override fun getItemCount(): Int {
        TODO("not implemented")
        return arrayList.size
    }

    override fun onBindViewHolder(holder: Adapter.MyViewHolder, position: Int) {
        TODO("not implemented")
        holder.text.setText(arrayList.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, parent, false)
        return MyViewHolder(view)
        TODO("not implemented")
    }
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),

            View.OnClickListener {
        var text: TextView = itemView.findViewById<View>(android.R.id.text1) as
                TextView
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {


        }
    }
    fun setList(arrayList: ArrayList<String>) {
        this.arrayList = arrayList
        notifyDataSetChanged()
    }


}