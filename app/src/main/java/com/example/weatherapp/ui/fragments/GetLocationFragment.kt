package com.example.weatherapp.ui.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.base.BaseFragment
import com.example.weatherapp.databinding.FragmentGetLocationBinding
import com.example.weatherapp.view_model.GetLocationViewModel
import com.google.android.gms.location.*
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class GetLocationFragment : BaseFragment<FragmentGetLocationBinding, GetLocationViewModel>() {

    private lateinit var binding: FragmentGetLocationBinding
    private lateinit var viewModel: GetLocationViewModel
    private lateinit var navController: NavController

    private var latitude = 0.0
    private var longitude = 0.0

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

        val permission = ContextCompat.checkSelfPermission(
            requireActivity(),
            Manifest.permission.ACCESS_FINE_LOCATION,
        )

        binding.button.setOnClickListener {
            if (permission == PackageManager.PERMISSION_GRANTED) {
                navController.navigate(
                    GetLocationFragmentDirections.actionGetLocationFragmentToWeatherFragment(
                        latitude.toFloat(), longitude.toFloat()
                    )
                )
            } else {
                fetchLocation()
            }
        }

        requestLocationUpdates()
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

    private fun requestLocationUpdates() {
        val locationRequest = LocationRequest.create().apply {
            interval = TimeUnit.MINUTES.toMillis(2000)
            fastestInterval = TimeUnit.MINUTES.toMillis(1000)
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        val client: FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())

        val permission = ContextCompat.checkSelfPermission(
            requireActivity(),
            Manifest.permission.ACCESS_FINE_LOCATION,
        )
        if (permission == PackageManager.PERMISSION_GRANTED) {
            client.requestLocationUpdates(locationRequest, object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    val location: Location = locationResult.lastLocation
                    latitude = location.latitude
                    longitude = location.longitude
                }
            }, Looper.myLooper()!!)
        }
    }


}