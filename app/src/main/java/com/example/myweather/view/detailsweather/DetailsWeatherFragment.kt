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
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_details_wether.*

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
    ): View {
        _binding = FragmentDetailsWetherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Weather>(KEY_WEATHER)?.let {
            renderData(it)
        }

    }

    @SuppressLint("SetTextI18n")
    private fun renderData(weather: Weather) {
        with(binding) {
            cityName.text = weather.city.name
            cityCoordinates.text =
                "${weather.city.lat.toString()} ${weather.city.lon}"
            feelsLikeValue.text = weather.feelsLike.toString()
            temperatureValue.text = weather.temperature.toString()
        }
        mainView.showSnackBar()
    }

    fun View.showSnackBar() {
        Snackbar.make(mainView, "Исправно", Snackbar.LENGTH_LONG).show();
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