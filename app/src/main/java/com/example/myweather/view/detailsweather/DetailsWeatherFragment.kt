package com.example.myweather.view.detailsweather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myweather.R
import com.example.myweather.databinding.FragmentDetailsWetherBinding
import com.example.myweather.viewmodel.MainViewModel

class DetailsWeatherFragment : Fragment() {

    private var _binding: FragmentDetailsWetherBinding?=null
    private val binding: FragmentDetailsWetherBinding
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
        _binding = FragmentDetailsWetherBinding.inflate(inflater, container, false)
        initRadioGroup()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

/* @SuppressLint("SetTextI18n")
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

 } */

 companion object {

     @JvmStatic
     fun newInstance() = DetailsWeatherFragment()

 }

 private fun initRadioGroup() {
     binding.russian.setOnClickListener(lisener)
     binding.world.setOnClickListener(lisener)
     binding.worldServer.setOnClickListener(lisener)


 }

 val lisener: View.OnClickListener = View.OnClickListener {
     when (it.getId()) {
         R.id.russian -> {
             val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
             viewModel.russianWeather()
         }
         R.id.world -> {
             val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
             viewModel.worldWeather()

         }
         R.id.world_server -> {
             val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
             viewModel.weatherFromServer()


         }

     }
 }
}