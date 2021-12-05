package com.GC200338513.twitterclone

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(
    val context: Context,
    val posts: List<Post>
) : RecyclerView.Adapter<RecyclerViewAdapter.PostViewHolder>()
{

    /**
     * This class is used to allow us to access the item_post.xml objects
     */
    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val postTextView = itemView.findViewById<TextView>(R.id.postTextView)
    }// end of PostViewHolder.

    /**
     * This connects (aka inflates) the individual ViewHolder (which is the link to the item_post.xml)
     * with the RecyclerView
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder
    {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }// end of onCreateViewHolder().

    /**
     * This method will bind the viewHolder with a specific post object
     */
    override fun onBindViewHolder(viewHolder: PostViewHolder, position: Int)
    {
        val post = posts[position]
        viewHolder.postTextView.text = post.postString
    }// end of onBindViewHolder().

    override fun getItemCount(): Int
    {
        return posts.size
    }// end of getItemCount().
}// end of class.