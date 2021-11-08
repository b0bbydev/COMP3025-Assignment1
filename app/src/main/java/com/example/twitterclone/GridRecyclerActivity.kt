package com.example.twitterclone

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.twitterclone.databinding.ActivityGridRecyclerBinding

class GridRecyclerActivity : AppCompatActivity(), GridAdapter.UserItemListener
{
    private lateinit var binding: ActivityGridRecyclerBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityGridRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //get data from the view model
        val viewModel: UserListViewModel by viewModels()
        viewModel.getUsers().observe(this, { users ->
            var gridAdapter = GridAdapter(this, users, this)
            binding.gridRecyclerView.adapter = gridAdapter
        })
    }

    override fun userSelected(user: User)
    {
        TODO("Not yet implemented")
    }
}