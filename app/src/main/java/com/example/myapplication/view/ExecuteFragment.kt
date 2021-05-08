package com.example.myapplication.view

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import com.example.myapplication.base.BaseFragment
import com.example.myapplication.databinding.FragmentExecuteBinding
import com.example.myapplication.model.User
import com.example.myapplication.viewmodel.ExecuteFragmentViewModel
import com.example.myapplication.viewmodel.MainFragmentViewModel


class ExecuteFragment : BaseFragment<FragmentExecuteBinding, ExecuteFragmentViewModel>() {

    private val args: ExecuteFragmentArgs by navArgs();
    private lateinit var mUser: User

    override fun getLayoutId(): Int = R.layout.fragment_execute


    override fun getViewModel(): Class<ExecuteFragmentViewModel> =
        ExecuteFragmentViewModel::class.java


    override fun initSubscriber() {

    }

    override fun onViewReady() {
        binding.handleExecuteFrmEvent = this
        mUser = args.user
        Log.i(TAG, mUser.toString())
        if (mUser.userName.length > 0) {
            binding.btnAdd.visibility = View.GONE
            binding.user = mUser
        } else {
            clearText()
            binding.btnDelete.visibility = View.GONE
            binding.btnEdit.visibility = View.GONE
        }
    }

    fun addUser() {
        getUser()
        if (validateText(mUser)) {
            viewModel.insertUser(mUser)
            showToast("Insert succesful")
            backToMainFragment()
        } else {
            showToast("Error: Invalid text")
        }
        clearText()
    }

    fun updateUser() {
        getUser()
        if (validateText(mUser)) {
            viewModel.updateUser(mUser)
            showToast("Update succesful")
            backToMainFragment()
        } else {
            showToast("Error: Invalid text")
        }
        clearText()
    }

    fun deleteUser() {
        viewModel.deleteUser(mUser)
        showToast("User deleted")
        backToMainFragment()
    }

    fun backToMainFragment() {
        view?.let {
            Navigation.findNavController(it).navigate(R.id.action_executeFragment_to_mainFragment)
        }
    }

    private fun getUser() {
        mUser.userName = binding.etUserName.text.toString()
        mUser.userEmail = binding.etUserEmail.text.toString()
        mUser.userAge = Integer.parseInt(binding.etUserAge.text.toString())
    }

    private fun validateText(user: User): Boolean {
        if (user.userName.length > 0 &&
            user.userEmail.length > 0 &&
            user.userAge.toString().length > 0 &&
            user.userAge > 0
        ) {
            return true
        }
        return false
    }

    private fun clearText() {
        binding.etUserName.text.clear()
        binding.etUserEmail.text.clear()
        binding.etUserAge.text.clear()
        view?.let { activity?.hideKeyboard(it) }
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}