package com.my.demo.testlogin.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.my.demo.testlogin.R
import com.my.demo.testlogin.SuperHero
import com.my.demo.testlogin.databinding.ItemSuperheroBinding

class SuperHeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemSuperheroBinding.bind(view)

    fun render(superHeroModel: SuperHero, onClickListener: (SuperHero) -> Unit) {
        binding.tvName.text = superHeroModel.superhero
        binding.tvSubtitle.text = superHeroModel.realName
        binding.tvFecha.text = superHeroModel.publisher
        Glide.with(binding.ivPhoto.context).load(superHeroModel.photo).into(binding.ivPhoto)

        itemView.setOnClickListener {
            onClickListener(superHeroModel)
        }

    }

}