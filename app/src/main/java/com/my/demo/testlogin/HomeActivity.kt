package com.my.demo.testlogin

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.my.demo.testlogin.adapter.MovieAdapter
import com.my.demo.testlogin.adapter.SuperHeroAdapter
import com.my.demo.testlogin.constant.Constants
import com.my.demo.testlogin.databinding.ActivityHomeBinding
import com.my.demo.testlogin.entity.Movie
import com.my.demo.testlogin.service.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

class HomeActivity : AppCompatActivity() {

    private val superHeroList = SuperHeroProvider.superHeroList
    private val movieList = mutableListOf<Movie>()
    private lateinit var binding: ActivityHomeBinding
    private lateinit var movieAdapter: MovieAdapter;
    private var backPressedTime: Long = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        initRecyclerView()
        initRvPopularMovies()
        getPopularMovies()
    }

    private fun initRecyclerView() {
        val manager = LinearLayoutManager(this)
//        val decoration = DividerItemDecoration(this, manager.orientation)
//        binding.rvSuperHero.addItemDecoration(decoration)
        binding.rvSuperHero.layoutManager = manager
        binding.rvSuperHero.adapter = SuperHeroAdapter(superHeroList) { superHero ->
            onSelectedSuperHero(superHero)
        };
    }

    fun initRvPopularMovies() {
        val manager = LinearLayoutManager(this)
        movieAdapter = MovieAdapter(movieList)
        binding.rvMovies.layoutManager = manager
        binding.rvMovies.adapter = movieAdapter

    }

    private fun onSelectedSuperHero(superHero: SuperHero) {
        Toast.makeText(this, "Hero: " + superHero.superhero, Toast.LENGTH_SHORT).show()
    }


    private fun getPopularMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            val api_key = getString(R.string.api_key)

            val call = RetrofitInstance().movieDBService
                .getAllMovies(api_key)

            val exec = call.execute()
            val body = exec.body()

            runOnUiThread {
                if (exec.isSuccessful) {
                    val movies = body?.results ?: emptyList()
                    movieList.clear()
                    movieList.addAll(movies)
                    movieAdapter.notifyDataSetChanged()
//                    binding.rvMovies.notifySubtreeAccessibilityStateChanged()
                    Log.i("HomeActivity", "DATA: ${body?.results}")
                } else {
                    showError()
                }
            }


        }

    }

    private fun getPreference(): String {
        val KEY_TOKEN = Constants.sharedPreference.TOKEN

        val pref = getSharedPreferences("user_preference", Context.MODE_PRIVATE)
        val token = pref.getString(KEY_TOKEN, "") ?: ""

        return token
    }

    private fun showError() {
        Toast.makeText(this, "Ha Ocurrido algun error", Toast.LENGTH_SHORT)
            .show()
    }

    private fun showMessage(text: String? = "Ha ocurrrido un error") {
        Toast.makeText(this, text, Toast.LENGTH_SHORT)
            .show()
    }

    override fun onBackPressed() {
        if (backPressedTime + 3000 > System.currentTimeMillis()) {
            moveTaskToBack(true)
//            exitProcess(-1)
            
//            super.onBackPressed()
//            finish()
        }

        backPressedTime = System.currentTimeMillis()
        showMessage("Presiona atrás nuevamente para salir")

    }
}