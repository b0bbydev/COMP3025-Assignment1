package com.GC200338513.twitterclone

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.GC200338513.twitterclone.databinding.ActivityGridRecyclerBinding

class GridRecyclerActivity : AppCompatActivity(), GridAdapter.PostItemListener
{
    private lateinit var binding: ActivityGridRecyclerBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityGridRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // switch to PostActivity when addPostButton is clicked.
        val addPostButton: View = findViewById(R.id.addPostButton)
        val intent = Intent(this, PostActivity::class.java)
        addPostButton.setOnClickListener { view ->
            startActivity(intent)
        }// end of addPostButton onClickListener.

        // get data from the view model
        val viewModel: PostListViewModel by viewModels()
        viewModel.getPosts().observe(this, { posts ->
            var gridAdapter = GridAdapter(this, posts, this)
            binding.gridRecyclerView.adapter = gridAdapter
        })
    }// end of onCreate().

    override fun postSelected(post: Post)
    {
        val intent = Intent(this, CommentActivity::class.java)
        intent.putExtra("postID", post.postID)
        intent.putExtra("postString", post.postString)
        intent.putExtra("createdBy", post.createdBy)
        startActivity(intent)
    }// end of postSelected().
}// end of class.