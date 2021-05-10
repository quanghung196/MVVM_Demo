package com.example.myapplication.ui.fragment

import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.myapplication.R
import com.example.myapplication.adapter.UserAdapter
import com.example.myapplication.ui.base.BaseFragment
import com.example.myapplication.databinding.FragmentMainBinding
import com.example.myapplication.ui.listener.UserListener
import com.example.myapplication.model.User
import com.example.myapplication.viewmodel.UserViewModel


class MainFragment : BaseFragment<FragmentMainBinding, UserViewModel>(), UserListener {
    private lateinit var mUserAdapter: UserAdapter
    private lateinit var mUser: User

    override fun getLayoutId(): Int = R.layout.fragment_main

    override fun getViewModel(): Class<UserViewModel> = UserViewModel::class.java

    override fun initSubscriber() {
        viewModel.getAllUser()
            .observe(viewLifecycleOwner, Observer<List<User>> {
                mUserAdapter.submitList(it)
            })
    }

    override fun onViewReady() {
        binding.handleMainFrmEvent = this
        viewModel.setUserListener(this)
        mUserAdapter = UserAdapter(viewModel)
        binding.rvUserList.adapter = mUserAdapter

        setToolbarTitle("List User")
    }

    fun addUser() {
        mUser = User("", "", 0)
        val action =
            MainFragmentDirections.actionMainFragmentToExecuteFragment(
                mUser
            )
        view?.let { Navigation.findNavController(it).navigate(action) }
    }

    override fun onUserClicked(user: User) {
        //showToast(user.toString())
        val action =
            MainFragmentDirections.actionMainFragmentToExecuteFragment(
                user
            )
        view?.let { Navigation.findNavController(it).navigate(action) }
    }
}