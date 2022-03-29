package com.example.myweather.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myweather.R
import com.example.myweather.databinding.FragmentMainBinding
import com.example.myweather.viewmodel.AppState
import com.example.myweather.viewmodel.MainViewModel

class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        // return inflater.inflate(R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val observer = object : Observer<AppState> {
            override fun onChanged(t: AppState) {
                renderData(t)

            }

        }
        viewModel.getData().observe(viewLifecycleOwner, observer)
        viewModel.getWeather()

    }

    @SuppressLint("SetTextI18n")
    private fun renderData(data: AppState) {
        when (data) {
            is AppState.Error -> binding.loadingLayout.visibility = View.GONE
            is AppState.Loadind -> binding.loadingLayout.visibility = View.VISIBLE
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                binding.cityName.text=data.weatherData.city.name
                binding.cityCoordinates.text="${data.weatherData.city.lat.toString()} ${data.weatherData.city.lon}"
                binding.feelsLikeValue.text=data.weatherData.feelsLike.toString()
                binding.temperatureValue.text=data.weatherData.temperature.toString()
                Toast.makeText(requireContext(), "Исправно", Toast.LENGTH_LONG).show()
            }
        }

    }

    companion object {

        @JvmStatic
        fun newInstance() = MainFragment()

    }
}