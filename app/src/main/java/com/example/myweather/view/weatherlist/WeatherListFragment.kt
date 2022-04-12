package com.example.myweather.view.weatherlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myweather.R
import com.example.myweather.databinding.FragmentWeatherListBinding
import com.example.myweather.repository.Weather
import com.example.myweather.repository.utils.KEY_WEATHER
import com.example.myweather.view.detailsweather.DetailsWeatherFragment
import com.example.myweather.viewmodel.AppState
import com.example.myweather.viewmodel.MainViewModel

class WeatherListFragment : Fragment(), OnClick {

    private var _binding: FragmentWeatherListBinding? = null
    private val binding: FragmentWeatherListBinding
        get() {
            return _binding!!
        }

    private val adapter = WeatherListAdapter(this)


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherListBinding.inflate(inflater, container, false)
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
        viewModel.russianWeather()


    }


    private fun renderData(data: AppState) {
        when (data) {
            is AppState.Error -> binding.loadingLayout.visibility = View.GONE
            is AppState.Loadind -> binding.loadingLayout.visibility = View.VISIBLE
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                data.weatherList
                binding.recyclerView.adapter = adapter
                adapter.setWeather(data.weatherList)
                Toast.makeText(requireContext(), "Исправно", Toast.LENGTH_LONG).show()
            }
        }

    }

    companion object {

        @JvmStatic
        fun newInstance() = WeatherListFragment()

    }

    private fun initRadioGroup() {
        binding.also {
            it.russian.setOnClickListener(lisener)
            it.world.setOnClickListener(lisener)
            it.worldServer.setOnClickListener(lisener)
        }
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

    override fun OnItemClick(weather: Weather) {
        val bundle = Bundle()
        bundle.putParcelable(KEY_WEATHER, weather)
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.container, DetailsWeatherFragment.newInstance(bundle)).addToBackStack("")
            .commit()

    }
}