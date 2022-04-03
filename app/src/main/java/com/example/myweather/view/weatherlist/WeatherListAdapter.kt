package com.example.myweather.view.weatherlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myweather.databinding.FragmentWeatherListRecyclerItemBinding
import com.example.myweather.repository.Weather

class WeatherListAdapter(
    private val onClick: OnClick,
    private var weather: List<Weather> = listOf()
) :
    RecyclerView.Adapter<WeatherListAdapter.MyCityHolder>() {

    fun setWeather(weather: List<Weather>) {
        this.weather = weather
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCityHolder {
        val binding = FragmentWeatherListRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyCityHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MyCityHolder, position: Int) {
        holder.bind(weather.get(position))
    }

    override fun getItemCount() = weather.size

    inner class MyCityHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(weather: Weather) {
            val binding = FragmentWeatherListRecyclerItemBinding.bind(itemView)
            binding.tvNameCity.text = weather.city.name
            binding.root.setOnClickListener {
                onClick.OnItemClick(weather)
            }

        }
    }
}
