package com.my.demo.testlogin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.my.demo.testlogin.R
import com.my.demo.testlogin.entity.Movie

class MovieAdapter(private val movies: List<Movie>) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val movieLayout = R.layout.item_movie
        val layoutInflater = LayoutInflater.from(parent.context)
        val movieView = layoutInflater.inflate(movieLayout, parent, false)
        return MovieViewHolder(movieView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.render(movie)
    }

    override fun getItemCount(): Int = movies.size
}