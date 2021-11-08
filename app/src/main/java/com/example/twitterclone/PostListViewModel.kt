package com.example.twitterclone

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class PostListViewModel : ViewModel()
{

    //this will hold a mutable list of Restaurant objects
    private val posts = MutableLiveData<List<Post>>()

    init
    {
        loadPosts()
    }

    /**
     * This method will load the Post objects from Firebase.FireStore
     * https://firebase.google.com/docs/firestore/query-data/listen
     */
    private fun loadPosts()
    {
        val db = FirebaseFirestore.getInstance().collection("Posts")
            .orderBy("postString", Query.Direction.ASCENDING)

        db.addSnapshotListener { documents, exception ->

            //if there is an exception - let's log it
            exception?.let {
                Log.i("DB_Response", "Listen failed : $exception")
                return@addSnapshotListener
            }

            Log.i("DB_Response", "# of elements returned: ${documents?.size()}")

            // create an array list of Post objects that will be used to
            // populate the MutableLiveData variable called posts
            val postList = ArrayList<Post>()

            // loop over the documents from the DB and create Post objects
            documents?.let {
                for (document in documents)
                {
                    try
                    {
                        val post = document.toObject(Post::class.java)
                        postList.add(post)
                    } catch (e: Exception)
                    {
                        Log.i("DB_Response", document.toString())
                    }// end of try-catch.
                }// end of for.
            }
            posts.value = postList
        }
    }// end of loadPosts().

    fun getPosts(): LiveData<List<Post>>
    {
        return posts
    }
}// end of class.