package com.example.android.networkconnect.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import com.example.android.networkconnect.R
import com.example.android.networkconnect.viewmodel.CharacterViewModel
import kotlinx.android.synthetic.main.fragment_first.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class FirstFragment: Fragment() {

    private val characterViewModel: CharacterViewModel by sharedViewModel<CharacterViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnOpenDialog.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        observerViewModel()
    }

    override fun onResume() {
        super.onResume()
        activity?.findViewById<Toolbar>(R.id.toolbar)?.apply {
            title = "NetworkConnect"
            navigationIcon = null
        }
    }

    private fun observerViewModel() {
        characterViewModel.listCharacters.observe(viewLifecycleOwner, { schedule ->
            schedule?.let {
                llnextscreen.visibility = VISIBLE
            }
        })

        characterViewModel.isFetching.observe(viewLifecycleOwner, { schedule ->
            if (schedule) {
                llnextscreen2.visibility = VISIBLE
            } else {
                llnextscreen2.visibility = GONE
            }
        })
    }
}
