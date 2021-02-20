package com.example.android.networkconnect.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.networkconnect.R
import com.example.android.networkconnect.viewmodel.CharacterViewModel
import kotlinx.android.synthetic.main.fragment_second.*
import org.koin.android.viewmodel.ext.android.sharedViewModel


class SecondFragment : Fragment() {

    private val characterViewModel: CharacterViewModel by sharedViewModel<CharacterViewModel>()

    private lateinit var characterAdapter: CharacterAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.findViewById<Toolbar>(R.id.toolbar)?.apply {
            title = "RickAndMorty List"
            setNavigationIcon(R.drawable.ic_back_action)
            setNavigationOnClickListener {
                findNavController().navigateUp()
            }
        }

        characterAdapter = CharacterAdapter()

        val characters = characterViewModel.getLastCharacters()

        rvSchedule.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = characterAdapter
        }

        characterAdapter.updateData(characters)

        observerViewModel()
    }

    private fun observerViewModel() {
        characterViewModel.listCharacters.observe(viewLifecycleOwner, { schedule ->
            characterAdapter.updateData(schedule)
        })
    }
}
