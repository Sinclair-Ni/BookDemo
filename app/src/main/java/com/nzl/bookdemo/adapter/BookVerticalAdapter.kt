package com.nzl.bookdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nzl.bookdemo.R
import com.nzl.bookdemo.bean.Book

/**
 * Author: nizonglong
 * C_Time: 2019/11/19 19:20
 */
class BookVerticalAdapter(private val mContext: Context?, private val data: List<Book>) :
    RecyclerView.Adapter<BookVerticalAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.book_item_vertical, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = data[position]
        holder.bookName.text = book.bookid
        if (mContext != null) {
            Glide.with(mContext).setDefaultRequestOptions(RequestOptions().fitCenter())
                .load(book.bookMainPic).into(holder.bookImage)
        }
        holder.bookDesc.text = book.bookDesc
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var bookImage: ImageView = view.findViewById(R.id.book_pic) as ImageView
        var bookName: TextView = view.findViewById(R.id.book_name) as TextView
        var bookDesc: TextView = view.findViewById(R.id.book_desc) as TextView

    }


}