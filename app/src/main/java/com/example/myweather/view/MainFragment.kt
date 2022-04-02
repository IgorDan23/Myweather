package com.example.myweather.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myweather.R
import com.example.myweather.databinding.FragmentMainBinding
import com.example.myweather.viewmodel.AppState
import com.example.myweather.viewmodel.MainViewModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding?=null
    private val binding: FragmentMainBinding
    get() {
        return _binding!!
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        initRadioGroup()
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
                binding.cityName.text = data.weatherData.city.name
                binding.cityCoordinates.text =
                    "${data.weatherData.city.lat.toString()} ${data.weatherData.city.lon}"
                binding.feelsLikeValue.text = data.weatherData.feelsLike.toString()
                binding.temperatureValue.text = data.weatherData.temperature.toString()
                Toast.makeText(requireContext(), "Исправно", Toast.LENGTH_LONG).show()
            }
        }

    }

    companion object {

        @JvmStatic
        fun newInstance() = MainFragment()

    }

    private fun initRadioGroup() {
        binding.one.setOnClickListener(lisener)
        binding.two.setOnClickListener(lisener)


    }

    val lisener: View.OnClickListener = View.OnClickListener {
        when (it.getId()) {
            R.id.one -> {
                val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
                viewModel.num = 1
                viewModel.getWeather()
            }
            R.id.two -> {
                val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
                viewModel.num = 2
                viewModel.getWeather()

            }

        }
    }
}