package com.example.android.networkconnect.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.networkconnect.R
import com.example.android.networkconnect.adapters.CharactersAdapter
import com.example.android.networkconnect.viewmodels.RickMortyViewModel
import kotlinx.android.synthetic.main.rickmorty_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * Fragment that shows characters from Rick and Morty
 */
class RickMortyFragment : Fragment() {

    companion object {
        /**
         * Method to create instance of the fragment
         */
        fun newInstance() = RickMortyFragment()
    }

    /**
     * MainViewModel to listen to search events
     */
    private val rickMortyViewModel: RickMortyViewModel by viewModel()

    private lateinit var layoutManager: LinearLayoutManager

    private lateinit var adapter: CharactersAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.rickmorty_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager

        adapter = CharactersAdapter()
        recyclerView.adapter = adapter

        rickMortyViewModel.characters.observe(viewLifecycleOwner, Observer {
            progressView.visibility = View.GONE
            adapter.setCharacters(it)
        })

        rickMortyViewModel.charactersError.observe(viewLifecycleOwner, Observer {
            progressView.visibility = View.GONE
            Toast.makeText(requireContext(), getString(R.string.error), Toast.LENGTH_SHORT).show()
        })

        rickMortyViewModel.charactersException.observe(viewLifecycleOwner, Observer {
            progressView.visibility = View.GONE
            Toast.makeText(requireContext(), getString(R.string.exception), Toast.LENGTH_SHORT)
                    .show()
        })

        progressView.visibility = View.VISIBLE
        rickMortyViewModel.characters()
    }
}