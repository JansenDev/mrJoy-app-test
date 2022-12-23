package com.my.demo.testlogin.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.my.demo.testlogin.R
import com.my.demo.testlogin.SuperHero

class SuperHeroViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val superHero = view.findViewById<TextView>(R.id.tvName)
    private val realName = view.findViewById<TextView>(R.id.tvSubtitle)
    private val publisher = view.findViewById<TextView>(R.id.tvFecha)
    private val photo = view.findViewById<ImageView>(R.id.ivPhoto)

    fun render(superHeroModel: SuperHero) {
        superHero.text = superHeroModel.superhero
        realName.text = superHeroModel.realName
        publisher.text = superHeroModel.publisher
        Glide.with(photo.context).load(superHeroModel.photo).into(photo)
    }

}