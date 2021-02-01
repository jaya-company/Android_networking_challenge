package com.example.android.networkconnect.datafragment.model

import android.R
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.android.networkconnect.datafragment.datainj.DaggerApiComponent
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class DataService {
    @Inject
    lateinit var api: DataApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getCharacters(): Single<DataResponse> {
        return api.getCharacters()
    }
//    fun getCharacters(): Single<List<Character>> {
//        val call = api.getCharacters()
//        call. enqueue(object : Callback<List<Character>> {
//            override fun onResponse(call: Call<List<Character>>, response: Response<List<Character>>) {
//                val countryList = response.body()
//
//                //Creating an String array for the ListView
//                val countries = arrayOfNulls<String>(countryList!!.size)
//
//                //looping through all the countries and inserting the names inside the string array
//                for (i in countryList.indices) {
//                    countries[i] = countryList[i].getName()
//                }
//
//                //displaying the string array into listview
//                listView.adapter =
//                    ArrayAdapter(applicationContext, R.layout.simple_list_item_1, countries)
//            }
//
//            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
//                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
//            }
//        })
//        return api.getCharacters()
//    }
}