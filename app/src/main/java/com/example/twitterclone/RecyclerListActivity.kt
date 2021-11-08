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

        //get data from the view model
        val viewModel: UserListViewModel by viewModels()
        viewModel.getUsers().observe(this, { users ->
            var recyclerViewAdapter = RecyclerViewAdapter(this, users)
            binding.verticalRecyclerView.adapter = recyclerViewAdapter
        })
    }
}