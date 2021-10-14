package com.example.twitterclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.twitterclone.databinding.ActivityMainBinding
import com.example.twitterclone.databinding.ActivityUserListBinding

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

        setContentView(R.layout.activity_user_list)
    }// end of onCreate().
}// end of class.