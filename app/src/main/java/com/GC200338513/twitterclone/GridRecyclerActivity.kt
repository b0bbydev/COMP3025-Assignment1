package com.GC200338513.twitterclone

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

        // get data from the view model
        val viewModel: PostListViewModel by viewModels()
        viewModel.getPosts().observe(this, { posts ->
            var gridAdapter = GridAdapter(this, posts, this)
            binding.gridRecyclerView.adapter = gridAdapter
        })

        // include toolbar.
        setSupportActionBar(binding.mainToolbar.toolbar)
    }// end of onCreate().

    // this method will add the menu to the toolbar.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }// end of onCreateOptionsMenu().

    // enable the navigation through menu items on toolbar.
    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when(item.itemId){
            // add post button.
            R.id.action_add -> {
                startActivity(Intent(applicationContext, PostActivity::class.java))
                return true
            }
            // post list button.
            R.id.action_post_list -> {
                //startActivity(Intent(applicationContext, GridRecyclerActivity::class.java))
                return true
            }
            // profile button.
            R.id.action_profile -> {
                startActivity(Intent(applicationContext, ProfileActivity::class.java))
                return true
            }
        }// end of when.
        return super.onOptionsItemSelected(item)
    }// end of onOptionsItemSelected().

    override fun postSelected(post: Post)
    {
        val intent = Intent(this, CommentActivity::class.java)
        intent.putExtra("postID", post.postID)
        intent.putExtra("postString", post.postString)
        intent.putExtra("createdBy", post.createdBy)
        startActivity(intent)
    }// end of postSelected().
}// end of class.