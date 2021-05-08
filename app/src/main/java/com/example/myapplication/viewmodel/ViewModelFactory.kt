package com.example.myapplication.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.base.BaseFragment

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainFragmentViewModel::class.java)) {
            return MainFragmentViewModel() as T
        }else if (modelClass.isAssignableFrom(ExecuteFragmentViewModel::class.java)){
            return ExecuteFragmentViewModel() as T
        }
        throw IllegalAccessException("Unable construct viewModel")
    }
}