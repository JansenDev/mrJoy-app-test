package com.my.demo.testlogin.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.my.demo.testlogin.databinding.ItemMovieBinding
import com.my.demo.testlogin.entity.Movie

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemMovieBinding.bind(view)

    fun render(movie: Movie) {
        val imageName = movie.poster_path
        val url = "https://www.themoviedb.org/t/p/w220_and_h330_face/$imageName"
        binding.tvDescription.text = movie.overview
        binding.tvTitle.text = movie.title
        binding.tvRelease.text = movie.release_date

        Glide.with(binding.ivMovie.context)
            .load(url)
            .into(binding.ivMovie)
    }
}