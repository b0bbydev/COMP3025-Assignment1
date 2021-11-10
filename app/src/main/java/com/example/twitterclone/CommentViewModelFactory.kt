package com.example.twitterclone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class CommentViewModelFactory(private val postID: String) : ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
    {
        if (modelClass.isAssignableFrom(CommentViewModel::class.java))
            return CommentViewModel(postID) as T
        else
            throw IllegalArgumentException("Unknown View Model")
    }// end of create().
}// end of class.