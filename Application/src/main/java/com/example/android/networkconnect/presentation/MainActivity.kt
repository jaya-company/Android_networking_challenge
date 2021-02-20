package com.example.android.networkconnect.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.android.networkconnect.R
import com.example.android.networkconnect.viewmodel.CharacterViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val characterViewModel: CharacterViewModel by viewModel<CharacterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.fetch_action -> {
                characterViewModel.getCharacters()
            }
            R.id.clear_action -> {
                characterViewModel.clearCharacters()
            }
        }
        return false
    }
}