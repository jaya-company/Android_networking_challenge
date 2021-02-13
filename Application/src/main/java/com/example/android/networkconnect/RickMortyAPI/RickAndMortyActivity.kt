package com.example.android.networkconnect.RickMortyAPI

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.networkconnect.R
import com.example.android.networkconnect.RickMortyAPI.Service.CharacterService
import com.example.android.networkconnect.RickMortyAPI.adapters.Character
import com.example.android.networkconnect.RickMortyAPI.adapters.CharactersAdapter
import kotlinx.android.synthetic.main.activity_rick_and_morty.*

class RickAndMortyActivity: AppCompatActivity() {
    private lateinit var service: CharacterService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rick_and_morty)

        supportActionBar?.title = resources.getString(R.string.titleActivity)
        service = CharacterService(this)
        fetchaData()
    }

    private fun fetchaData() {
        progressView.visibility = View.VISIBLE

        service.fetchData { response, error ->
            progressView.visibility = View.GONE

            if (response == null || error != null) {
                return@fetchData
            }

            val model: List<Character> = response.results
            charactersRecyclerView.adapter = CharactersAdapter(this, model)
            charactersRecyclerView.layoutManager = LinearLayoutManager(this)
        }
    }
}