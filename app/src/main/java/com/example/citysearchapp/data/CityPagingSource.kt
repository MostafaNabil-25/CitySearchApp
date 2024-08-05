package com.example.citysearchapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState

class CityPagingSource(private val cityList: List<City>) : PagingSource<Int, City>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, City> {
        val position = params.key ?: 0
        val loadSize = params.loadSize
        val endPosition = (position + loadSize).coerceAtMost(cityList.size)

        return LoadResult.Page(
            data = cityList.subList(position, endPosition),
            prevKey = if (position == 0) null else position - loadSize,
            nextKey = if (endPosition == cityList.size) null else endPosition
        )
    }

    override fun getRefreshKey(state: PagingState<Int, City>): Int? {
        return state.anchorPosition
    }
}