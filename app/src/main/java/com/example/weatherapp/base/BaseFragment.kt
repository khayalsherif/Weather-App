package com.example.weatherapp.base

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.weatherapp.R
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<ViewBinding : ViewDataBinding, ViewModel : BaseViewModel> : Fragment() {

    private lateinit var binding: ViewBinding
    private lateinit var viewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, getLayoutId(), container, false)

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel()
    }

    fun getBinding(): ViewBinding {
        return binding
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): ViewModel

    protected fun showErrorSnackBar(message: String) {
        val snackBar = Snackbar.make(binding.root, "", Snackbar.LENGTH_SHORT)
        snackBar.setBackgroundTint(Color.TRANSPARENT)

        val customSnackView = layoutInflater.inflate(R.layout.snackbar_error, null)
        val snackBarLayout = snackBar.view as Snackbar.SnackbarLayout
        val textView = customSnackView.findViewById<TextView>(R.id.text_error)
        textView.text = message
        snackBarLayout.setPadding(0, 0, 0, 0)
        snackBarLayout.addView(customSnackView, 0)

        snackBar.show()
    }
}