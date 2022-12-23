package com.my.demo.testlogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.my.demo.testlogin.adapter.SuperHeroAdapter
import com.my.demo.testlogin.databinding.ActivityHomeBinding
import com.my.demo.testlogin.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {

    private val superHeroList = SuperHeroProvider.superHeroList
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.rvSuperHero.layoutManager = LinearLayoutManager(this)
        binding.rvSuperHero.adapter = SuperHeroAdapter(superHeroList);
    }
}