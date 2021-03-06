package com.example.myapplication.ui.fragment

import android.view.View
import android.widget.EditText
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import com.example.myapplication.ui.base.BaseFragment
import com.example.myapplication.databinding.FragmentExecuteBinding
import com.example.myapplication.model.User
import com.example.myapplication.utils.ReusableFunctionForEdittext.clearAllEdittext
import com.example.myapplication.utils.ReusableFunctionForEdittext.getAllEditText
import com.example.myapplication.utils.ReusableFunctionForEdittext.hideKeyboardInFragment
import com.example.myapplication.utils.ReusableFunctionForEdittext.isTextFullfill
import com.example.myapplication.viewmodel.ExecuteFragmentViewModel
import com.example.myapplication.viewmodel.MainFragmentViewModel


class ExecuteFragment : BaseFragment<FragmentExecuteBinding, ExecuteFragmentViewModel>() {

    private val mArgs: ExecuteFragmentArgs by navArgs();
    private lateinit var mUser: User
    private lateinit var mEditTextList: ArrayList<EditText>

    override fun getLayoutId(): Int = R.layout.fragment_execute


    override fun getViewModel(): Class<ExecuteFragmentViewModel> =
        ExecuteFragmentViewModel::class.java

    override fun initSubscriber() {

    }

    override fun onViewReady() {
        mUser = mArgs.user
        binding.user = mUser
        binding.handleExecuteFrmEvent = this
        mEditTextList = ArrayList()
        if (mUser.userName.length > 0) {
            setToolbarTitle("List User")
            binding.btnAdd.visibility = View.GONE
            setToolbarTitle("Edit User")
        } else {
            setToolbarTitle("Add User")
            binding.fabAddStudent.visibility = View.GONE
            binding.btnDelete.visibility = View.GONE
            binding.btnEdit.visibility = View.GONE
        }
    }

    fun addUser() {
        getUser()
        mEditTextList.clear()
        if (isTextFullfill(getAllEditText(binding.relativeContainer,mEditTextList))) {
            viewModel.insertUser(mUser)
            showToast("Insert successful")
            backToMainFragment()
            view?.let { activity?.hideKeyboardInFragment(it) }
        } else {
            showToast("Error: Invalid text")
        }
        clearText()
    }

    fun updateUser() {
        getUser()
        mEditTextList.clear()
        if (isTextFullfill(getAllEditText(binding.relativeContainer,mEditTextList))) {
            viewModel.updateUser(mUser)
            showToast("Update successful")
            backToMainFragment()
            view?.let { activity?.hideKeyboardInFragment(it) }
        } else {
            showToast("Error: Invalid text")
        }
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

        if (binding.etUserAge.text.toString().isEmpty()) {
            mUser.userAge = 0;
        } else {
            mUser.userAge = Integer.parseInt(binding.etUserAge.text.toString())
        }
    }

    private fun clearText() {
        clearAllEdittext(binding.relativeContainer)
    }
}