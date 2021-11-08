package com.example.twitterclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.twitterclone.databinding.ActivityMainBinding
import com.example.twitterclone.databinding.ActivityUserListBinding
import com.google.firebase.firestore.FirebaseFirestore

class UserListActivity : AppCompatActivity()
{
    // binding.
    private lateinit var binding: ActivityUserListBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        // binding.
        binding = ActivityUserListBinding.inflate(layoutInflater)
        // set contentView to the binding.
        setContentView(binding.root)

        // connect to db.
        val db = FirebaseFirestore.getInstance().collection("Users")

        val query = db.get().addOnSuccessListener { documents ->

            // loop over Users.
            for(document in documents)
            {
                Log.i("DB_Response", "$(document.data)")

                // create a User object from db.
                val user = document.toObject(User::class.java)

                // add restaurant to LinearList
                val textView = TextView(this)
                textView.text = user.email
                textView.textSize = 20f

                binding.LinearLayout.addView(textView)
            }// end of for.
        }// end of query.

    }// end of onCreate().
}// end of class.