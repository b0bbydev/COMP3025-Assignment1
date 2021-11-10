package com.example.twitterclone

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.twitterclone.databinding.ActivityRecyclerListBinding

class RecyclerListActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityRecyclerListBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // get data from the view model
        val viewModel: PostListViewModel by viewModels()
        viewModel.getPosts().observe(this, { posts ->
            var recyclerViewAdapter = RecyclerViewAdapter(this, posts)
            binding.verticalRecyclerView.adapter = recyclerViewAdapter
        })
    }// end of onCreate().
}// end of class.