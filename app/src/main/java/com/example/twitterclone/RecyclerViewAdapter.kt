package com.example.twitterclone

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(
    val context: Context,
    val users: List<User>
) : RecyclerView.Adapter<RecyclerViewAdapter.UserViewHolder>()
{

    /**
     * This class is used to allow us to access the item_user.xml objects
     */
    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val emailTextView = itemView.findViewById<TextView>(R.id.emailTextView)
    }

    /**
     * This connects (aka inflates) the individual ViewHolder (which is the link to the item_user.xml)
     * with the RecyclerView
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder
    {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    /**
     * This method will bind the viewHolder with a specific user object
     */
    override fun onBindViewHolder(viewHolder: UserViewHolder, position: Int)
    {
        val user = users[position]
        viewHolder.emailTextView.text = user.email
    }

    override fun getItemCount(): Int
    {
        return users.size
    }
}// end of class.