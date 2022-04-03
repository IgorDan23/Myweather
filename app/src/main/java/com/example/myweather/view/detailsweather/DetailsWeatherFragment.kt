package com.example.myweather.view.detailsweather

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myweather.databinding.FragmentDetailsWetherBinding
import com.example.myweather.repository.Weather
import com.example.myweather.repository.utils.KEY_WEATHER

class DetailsWeatherFragment : Fragment() {

    private var _binding: FragmentDetailsWetherBinding? = null
    private val binding: FragmentDetailsWetherBinding
        get() {
            return _binding!!
        }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsWetherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val weather: Weather = arguments?.getParcelable<Weather>(KEY_WEATHER)!!
        renderData(weather)

    }

    @SuppressLint("SetTextI18n")
    private fun renderData(weather: Weather) {
        binding.cityName.text = weather.city.name
        binding.cityCoordinates.text =
            "${weather.city.lat.toString()} ${weather.city.lon}"
        binding.feelsLikeValue.text = weather.feelsLike.toString()
        binding.temperatureValue.text = weather.temperature.toString()
        Toast.makeText(requireContext(), "Исправно", Toast.LENGTH_LONG)
            .show()
    }


    companion object {

        @JvmStatic
        fun newInstance(bundle: Bundle): DetailsWeatherFragment {
            val fragment = DetailsWeatherFragment()
            fragment.arguments = bundle
            return fragment

        }
    }
}