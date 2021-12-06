package com.GC200338513.twitterclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.GC200338513.twitterclone.databinding.ActivityCommentBinding
import com.google.firebase.firestore.FirebaseFirestore

class CommentActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityCommentBinding
    private lateinit var viewModel: CommentViewModel
    private lateinit var viewModelFactory: CommentViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.postTextView.text = intent.getStringExtra("postString")
        val postID = intent.getStringExtra("postID")
        val createdBy = intent.getStringExtra("createdBy")

        binding.saveCommentButton.setOnClickListener {

            val commentString = binding.commentEditText.text.toString()

            // create the ability to save a comment.
            if (commentString.isNotEmpty())
            {
                val db = FirebaseFirestore.getInstance().collection("Comments")
                val commentID = db.document().id

                postID?.let {
                    val newComment = Comment(commentID, createdBy, commentString, postID)
                    db.document().set(newComment)
                        .addOnSuccessListener {
                            Toast.makeText(
                                this,
                                "Added to DB",
                                Toast.LENGTH_LONG
                            ).show()
                        }// end of addOnSuccessListener.
                        .addOnFailureListener {
                            Toast.makeText(
                                this,
                                "Failed to add comment",
                                Toast.LENGTH_LONG
                            ).show()
                        }// end of addOnFailureListener.
                }
            } else
            {
                Toast.makeText(this, "Comment cannot be empty", Toast.LENGTH_LONG)
                    .show()
            }// end of if-else.
        }// end of setOnClickListener().

        // this code connects the RecyclerView with the ViewAdapter and List of Comment objects
        postID?.let {
            viewModelFactory = CommentViewModelFactory(postID)
            viewModel = ViewModelProvider(this, viewModelFactory).get(CommentViewModel::class.java)
            viewModel.getComments().observe(this, { comments ->
                var recyclerAdapter = CommentViewAdapter(this, comments)
                binding.commentsRecyclerView.adapter = recyclerAdapter
            })
        }
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
                startActivity(Intent(applicationContext, GridRecyclerActivity::class.java))
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
}// end of class.