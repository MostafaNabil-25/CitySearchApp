package com.example.citysearchapp.ui


import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.citysearchapp.R
import com.example.citysearchapp.data.City

class CityAdapter : PagingDataAdapter<City, CityAdapter.CityViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<City>() {
            override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
                return oldItem._id == newItem._id
            }

            override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = getItem(position)
        if (city != null) {
            holder.bind(city)
        }
    }

    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cityName: TextView = itemView.findViewById(R.id.cityName)
        private val coordinates: TextView = itemView.findViewById(R.id.coordinates)

        fun bind(city: City) {
            cityName.text = "${city.name}, ${city.country}"
            coordinates.text = "Lat: ${city.coord.lat}, Lon: ${city.coord.lon}"

            itemView.setOnClickListener {
                val uri = "geo:${city.coord.lat},${city.coord.lon}"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                itemView.context.startActivity(intent)
            }
        }
    }
}