package com.my.demo.testlogin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.my.demo.testlogin.constant.Constants
import com.my.demo.testlogin.databinding.ActivityMainBinding
import com.my.demo.testlogin.entity.DataResponse
import com.my.demo.testlogin.service.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding;
    private var backPressedTime: Long = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener { submitAuth() }
    }


    private fun redirectToHome(dataResponse: DataResponse<String>) {
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("token", dataResponse.data)
        startActivity(intent)
    }

    private fun cleanFields(){
        binding.etUsername.text.clear()
        binding.etPassword.text.clear()
    }

    fun submitAuth() {

        val username = binding.etUsername.text.trim().toString();
        val password = binding.etPassword.text.trim().toString();
        val isEmptyFields = username.isEmpty() || password.isEmpty()

        if (isEmptyFields) {
            showMessage("Usuario y/o contraseña vacío")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitInstance().authService.authUser(username, password)
            val response = call.execute()
            val dataResponse = response.body()

            runOnUiThread {
                val isNotSuccess = !response.isSuccessful

                if (isNotSuccess || dataResponse?.data == null) {
                    showMessage(dataResponse?.message)
                    return@runOnUiThread
                }
                // Guardar en local storage(shared preferences)
                setPreferences(dataResponse.data)
                redirectToHome(dataResponse)
                cleanFields()
                binding.etUsername.requestFocus()
            }

        }


    }

    private fun showMessage(text: String? = "Ha ocurrrido un error") {
        Toast.makeText(this, text, Toast.LENGTH_SHORT)
            .show()
    }

    private fun setPreferences(token: String) {
        val KEY_TOKEN = Constants.sharedPreference.TOKEN

        val pref = getSharedPreferences("user_preference", Context.MODE_PRIVATE);
        val edit = pref.edit()
        edit.putString(KEY_TOKEN, token)
        edit.apply()
    }

    private fun getPreference(): String {
        val KEY_TOKEN = Constants.sharedPreference.TOKEN

        val pref = getSharedPreferences("user_preference", Context.MODE_PRIVATE)
        val token = pref.getString(KEY_TOKEN, "") ?: ""

        return token
    }

    override fun onBackPressed() {
        if (backPressedTime + 3000 > System.currentTimeMillis()) {
            super.onBackPressed()
            finish()
        }
        backPressedTime = System.currentTimeMillis()
        showMessage("Presiona atrás nuevamente para salir")

    }

}