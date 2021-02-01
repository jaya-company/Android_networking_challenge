package com.example.android.networkconnect.datafragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.networkconnect.R
import com.example.android.networkconnect.datafragment.adapter.CharacterListAdapter
import com.example.android.networkconnect.datafragment.viewmodel.ListViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.networkconnect.datafragment.datainj.DaggerApiComponent
import kotlinx.android.synthetic.main.data_fragment.*


class DataFragment : Fragment() {

    // instance of fragment
    companion object {
        fun newInstance() = DataFragment()
    }

    lateinit var viewModel: ListViewModel
    private var charactersAdapter = CharacterListAdapter(arrayListOf())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.data_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        charactersList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = charactersAdapter
        }

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
        }

        observeViewModel()
    }

    fun observeViewModel() {

        viewModel.characters.observe(this, Observer { countries ->
            countries?.let {
                charactersList.visibility = View.VISIBLE
                charactersAdapter.updateCharacters(it) }
        })

        viewModel.characterLoadError.observe(this, Observer { isError ->
            isError?.let { list_error.visibility = if(it) View.VISIBLE else View.GONE }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loading_view.visibility = if(it) View.VISIBLE else View.GONE
                if(it) {
                    list_error.visibility = View.GONE
                    charactersList.visibility = View.GONE
                }
            }
        })
    }
}