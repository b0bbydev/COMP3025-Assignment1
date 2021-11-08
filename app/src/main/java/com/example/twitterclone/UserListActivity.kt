package com.example.twitterclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import com.example.twitterclone.databinding.ActivityRestaurantListBinding

class UserListActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityRestaurantListBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //create an instance of our RestaurantListViewModel
        val viewModel: UserListViewModel by viewModels()

        viewModel.getUsers().observe(this, { users ->
            binding.linearLayout.removeAllViews()

            for (user in users)
            {
                //Add restaurant to the LinearList
                val textView = TextView(this)
                textView.text = user.email
                textView.textSize = 20f

                binding.linearLayout.addView(textView)
            }
        })
    }
}