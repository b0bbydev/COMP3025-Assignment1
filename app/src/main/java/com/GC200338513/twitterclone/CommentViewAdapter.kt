package com.GC200338513.twitterclone

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CommentViewAdapter(
    val context: Context,
    val comments: List<Comment>
) : RecyclerView.Adapter<CommentViewAdapter.CommentViewHolder>()
{
    /**
     * This "view holder" is what connects the actual view elements in the item_comment.xml file with this Adapter class
     */
    inner class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val createdByTextView = itemView.findViewById<TextView>(R.id.createdByTextView)
        val commentTextView = itemView.findViewById<TextView>(R.id.commentTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder
    {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_comment, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int)
    {
        val comment = comments[position]
        with(holder) {
            createdByTextView.text = comment.createdBy
            commentTextView.text = comment.commentString
        }
    }

    override fun getItemCount(): Int
    {
        return comments.size
    }
}// end of class.