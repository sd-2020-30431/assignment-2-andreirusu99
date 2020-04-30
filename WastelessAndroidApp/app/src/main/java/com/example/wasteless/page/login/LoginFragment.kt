package com.example.wasteless.page.login

import androidx.navigation.fragment.findNavController
import com.example.wasteless.LoginBinding
import com.example.wasteless.R
import com.example.wasteless.page.main.MainActivityViewModel
import com.example.wasteless.shared.base.BaseFragment
import com.example.wasteless.shared.utils.extensions.observeNonNull
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment :
    BaseFragment<LoginBinding, LoginViewModel>(R.layout.fragment_login) {

    override val viewModel by viewModel<LoginViewModel>()

    override fun setupViews() {
        viewModel.navigateToGroceryListsFragment.observeNonNull(this) {
            if(it)
                findNavController()
                    .navigate(LoginFragmentDirections.actionLoginFragmentToGroceryListsFragment())
        }
    }
}