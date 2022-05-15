package com.example.weatherapp.ui.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.base.BaseFragment
import com.example.weatherapp.databinding.FragmentGetLocationBinding
import com.example.weatherapp.view_model.GetLocationViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GetLocationFragment : BaseFragment<FragmentGetLocationBinding, GetLocationViewModel>() {

    private lateinit var binding: FragmentGetLocationBinding
    private lateinit var viewModel: GetLocationViewModel
    private lateinit var navController: NavController

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var latitude = 0.0
    private var longutude = 0.0

    override fun getLayoutId(): Int {
        return R.layout.fragment_get_location
    }

    override fun getViewModel(): GetLocationViewModel {
        viewModel = ViewModelProvider(this)[GetLocationViewModel::class.java]
        return viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = getBinding()
        navController = findNavController()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                latitude = location?.latitude ?: 40.409264
                longutude = location?.longitude ?: 49.867092
            }

        binding.button.setOnClickListener {
            navController.navigate(
                GetLocationFragmentDirections.actionGetLocationFragmentToWeatherFragment(
                    latitude.toFloat(), longutude.toFloat()
                )
            )
        }

        fetchLocation()
    }

    private fun fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
            ) !=
            PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION
            ) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            requestMultiplePermissions.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
            return
        }
    }

    private val requestMultiplePermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.entries.forEach {
                if (it.key == Manifest.permission.ACCESS_FINE_LOCATION &&
                    it.key == Manifest.permission.ACCESS_COARSE_LOCATION &&
                    it.value == true
                ) {
                    fetchLocation()
                }
            }
        }


}