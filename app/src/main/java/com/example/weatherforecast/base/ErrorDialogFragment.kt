package com.example.weatherforecast.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.DialogFragment
import com.example.weatherforecast.R
import com.example.weatherforecast.databinding.DialogErrorBinding

class ErrorDialogFragment: DialogFragment() {
    companion object {

        const val ERROR_MESSAGE_EXTRA = "ERROR MESSAGE EXTRA"

        const val TAG = "ErrorDialogFragment"
    }

    private var _binding: DialogErrorBinding? = null
    private val binding: DialogErrorBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogErrorBinding.inflate(inflater, container, false)
        val message =
            arguments?.getString(ERROR_MESSAGE_EXTRA) ?: getString(R.string.something_went_wrong)
        binding.setVariable(BR.errorMessage, message)
        binding.btnOk.setOnClickListener { dismiss() }
        return binding.root
    }
}