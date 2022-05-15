package com.example.weatherapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.adapters.WeatherAdapter
import com.example.weatherapp.base.BaseFragment
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.utils.NetworkResult
import com.example.weatherapp.utils.gone
import com.example.weatherapp.utils.visible
import com.example.weatherapp.view_model.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WeatherFragment : BaseFragment<FragmentWeatherBinding, WeatherViewModel>() {

    private lateinit var binding: FragmentWeatherBinding
    private lateinit var viewModel: WeatherViewModel
    private lateinit var navController: NavController

    private val weatherAdapter by lazy { WeatherAdapter() }
    private val args: WeatherFragmentArgs by navArgs()

    override fun getLayoutId(): Int {
        return R.layout.fragment_weather
    }

    override fun getViewModel(): WeatherViewModel {
        viewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
        return viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = getBinding()
        navController = findNavController()
        viewModel.getWeatherInformation(args.latitude.toDouble(), args.longitude.toDouble())

        configurationRecyclerView()
        collectTheResponse()
    }

    private fun configurationRecyclerView() {
        binding.rcView.adapter = weatherAdapter
        binding.rcView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun collectTheResponse() {
        lifecycleScope.launch {
            viewModel.weatherResponse.collect { response ->
                when (response) {
                    is NetworkResult.Success -> {
                        weatherAdapter.setData(response.data?.daily!!)
                        binding.layoutLoading.gone()
                    }
                    is NetworkResult.Empty -> {
                        showErrorSnackBar("Data is Empty")
                        binding.layoutLoading.gone()
                    }
                    is NetworkResult.Error -> {
                        showErrorSnackBar(response.message.toString())
                        binding.layoutLoading.gone()
                    }
                    is NetworkResult.Loading -> {
                        binding.layoutLoading.visible()
                    }
                }

            }
        }
    }

}