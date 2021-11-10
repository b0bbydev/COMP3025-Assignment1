package com.example.twitterclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.twitterclone.databinding.ActivityCommentBinding
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
                Toast.makeText(this, "Both user name and comment are req'd", Toast.LENGTH_LONG)
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
    }// end of onCreate().
}// end of class.