package com.GC200338513.twitterclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.GC200338513.twitterclone.databinding.ActivityPostBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuth

class PostActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityPostBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // get the current user for createdBy field.
        val createdBy = FirebaseAuth.getInstance().currentUser?.email.toString()

        // when postButton is clicked, save the post to the db.
        binding.postButton.setOnClickListener {

            // saving what user enters.
            val postString = binding.enterPost.text.toString()

            // create the ability to save a post.
            if (postString.isNotEmpty())
            {
                val db = FirebaseFirestore.getInstance().collection("Posts")
                val postID = db.document().id

                createdBy?.let {
                    val newPost = Post(postID, createdBy, postString)
                    db.document().set(newPost)
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
                                "Failed to add post",
                                Toast.LENGTH_LONG
                            ).show()
                        }// end of addOnFailureListener.
                }
            } else
            {
                Toast.makeText(this, "Post cannot be empty", Toast.LENGTH_LONG)
                    .show()
            }// end of if-else.
        }// end of setOnClickListener().

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
                //startActivity(Intent(applicationContext, PostActivity::class.java))
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