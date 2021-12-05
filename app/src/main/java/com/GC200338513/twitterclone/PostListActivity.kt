package com.GC200338513.twitterclone

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.GC200338513.twitterclone.databinding.ActivityPostListBinding

class PostListActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityPostListBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityPostListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //create an instance of our PostListViewModel
        val viewModel: PostListViewModel by viewModels()

        viewModel.getPosts().observe(this, { posts ->
            binding.linearLayout.removeAllViews()

            for (post in posts)
            {
                // Add restaurant to the LinearList
                val textView = TextView(this)
                textView.text = post.postString
                textView.textSize = 20f

                binding.linearLayout.addView(textView)
            }// end of for.
        })
    }// end of onCreate().
}// end of class.