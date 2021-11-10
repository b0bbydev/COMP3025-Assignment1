package com.example.twitterclone

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GridAdapter(
    val context: Context,
    val posts: List<Post>,
    val itemListener: PostItemListener
) : RecyclerView.Adapter<GridAdapter.UserViewHolder>()
{

    /**
     * This class is used to allow us to access the item_post.xml objects
     */
    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val postTextView = itemView.findViewById<TextView>(R.id.textView)
    }

    /**
     * This connects (aka inflates) the individual ViewHolder (which is the link to the item_post.xml)
     * with the RecyclerView
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder
    {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_grid_post, parent, false)
        return UserViewHolder(view)
    }// end of onCreateViewHolder().

    /**
     * This method will bind the viewHolder with a specific post object
     */
    override fun onBindViewHolder(viewHolder: UserViewHolder, position: Int)
    {
        val post = posts[position]
        with(viewHolder) {
            postTextView.text = post.postString
            itemView.setOnClickListener {
                itemListener.postSelected(post)
            }
        }
    }// end of onBindViewHolder().

    override fun getItemCount(): Int
    {
        return posts.size
    }// end of getItemCount().

    interface PostItemListener
    {
        fun postSelected(post: Post)
    }// end of PostItemListener.
}// end of class.