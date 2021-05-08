package com.example.myapplication.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.MyApp
import com.example.myapplication.viewmodel.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment<V : ViewDataBinding, VM : ViewModel> : Fragment(), CoroutineScope {
    protected lateinit var binding: V
    protected lateinit var viewModel: VM

    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    abstract fun getLayoutId(): Int
    abstract fun getViewModel(): Class<VM>
    abstract fun initSubscriber()
    abstract fun onViewReady()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(
                inflater,
                getLayoutId(), container, false
            )
        binding.lifecycleOwner = this
        job = Job()
        viewModel = ViewModelProvider(this, ViewModelFactory()).get(getViewModel())
        return binding.root
    }

    fun showToast(messenger: String){
        Toast.makeText(activity, messenger, Toast.LENGTH_LONG).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onViewReady()
        initSubscriber()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}