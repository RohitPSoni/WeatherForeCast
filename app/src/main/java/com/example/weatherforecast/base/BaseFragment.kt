package com.example.weatherforecast.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.weatherforecast.BR
import com.example.weatherforecast.R

abstract class BaseFragment<VB : ViewDataBinding>: Fragment() {
    private var _viewBinder: VB? = null
    protected val viewBinder get() = _viewBinder!!

    abstract fun getViewModel(): ViewModel?

    @LayoutRes
    abstract fun getLayoutId(): Int

    open fun bindData() {

    }

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinder?.let {} ?: run {
            _viewBinder = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        }
        getViewModel()?.let {
            with(viewBinder) {
                setVariable(BR.viewModel, it)
                lifecycleOwner = this@BaseFragment
            }
        }
        getViewModel()?.let { it ->
            if (it is BaseViewModel) {
                it.errorMessage.observe(viewLifecycleOwner) { error ->
                    showError(errorCause = error)
                    it.hideProgress()
                }
            }
        }
        bindData()
        return viewBinder.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _viewBinder = null
    }

    private fun showError(errorCause: String?) {
        val errorDialog = ErrorDialogFragment().apply {
            arguments = bundleOf(
                Pair(
                    ErrorDialogFragment.ERROR_MESSAGE_EXTRA,
                    errorCause ?: getString(R.string.something_went_wrong)
                )
            )
        }
        errorDialog.show(childFragmentManager, ErrorDialogFragment.TAG)
    }
}