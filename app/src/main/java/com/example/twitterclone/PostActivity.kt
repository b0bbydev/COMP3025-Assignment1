package com.example.twitterclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.twitterclone.databinding.ActivityPostBinding

class PostActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityPostBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }// end of onCreate().
}// end of class.