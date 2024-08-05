package com.example.citysearchapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.citysearchapp.data.City
import com.example.citysearchapp.data.CityPagingSource
import com.example.citysearchapp.data.CityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(private val cityRepository: CityRepository) : ViewModel() {
    private val query = MutableStateFlow("")

    val cityFlow = query.flatMapLatest { prefix ->
        Pager(PagingConfig(pageSize = 50)) {
            CityPagingSource(cityRepository.searchCities(prefix))
        }.flow.cachedIn(viewModelScope)
    }

    fun setQuery(prefix: String) {
        query.value = prefix
    }
}