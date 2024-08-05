package com.example.citysearchapp.data

import android.content.Context
import com.example.citysearchapp.utils.loadJSONFromAsset
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class CityRepository @Inject constructor(private val context: Context) {
    private val trie = Trie()

    init {
        val jsonString = loadJSONFromAsset(context, "cities.json")
        val cities: List<City> = Gson().fromJson(jsonString, object : TypeToken<List<City>>() {}.type)
        cities.forEach { trie.insert(it) }
    }

    fun searchCities(prefix: String): List<City> {
        return trie.search(prefix)
    }
}