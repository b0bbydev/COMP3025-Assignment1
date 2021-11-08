package com.example.twitterclone

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GridAdapter(
    val context: Context,
    val users: List<User>,
    val itemListener: UserItemListener
) : RecyclerView.Adapter<GridAdapter.UserViewHolder>()
{

    /**
     * This class is used to allow us to access the item_user.xml objects
     */
    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val emailTextView = itemView.findViewById<TextView>(R.id.textView)
    }

    /**
     * This connects (aka inflates) the individual ViewHolder (which is the link to the item_user.xml)
     * with the RecyclerView
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder
    {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_grid_user, parent, false)
        return UserViewHolder(view)
    }

    /**
     * This method will bind the viewHolder with a specific user object
     */
    override fun onBindViewHolder(viewHolder: UserViewHolder, position: Int)
    {
        val user = users[position]
        with(viewHolder) {
            emailTextView.text = user.email
            itemView.setOnClickListener {
                itemListener.userSelected(user)
            }
        }
    }

    override fun getItemCount(): Int
    {
        return users.size
    }

    interface UserItemListener
    {
        fun userSelected(user: User)
    }

}