package com.example.apparchitecture.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.apparchitecture.App
import com.example.apparchitecture.R
import com.example.apparchitecture.databinding.UserFragmentBinding
import com.example.apparchitecture.model.User
import com.example.apparchitecture.mvp.user.UserPresenter
import com.example.apparchitecture.mvp.user.UserView
import com.example.apparchitecture.ui.common.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

private const val USER = "user"

class UserFragment :
    MvpAppCompatFragment(R.layout.user_fragment),
    UserView,
    BackButtonListener {

    private var _binding: UserFragmentBinding? = null
    private val binding get() = _binding!!

    private var user: User? = null

    companion object {
        fun newInstance(user: User) =
            UserFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(USER, user)
                }
            }
    }

    val presenter: UserPresenter by moxyPresenter {
        UserPresenter(App.instance.router, user)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.let {
            user = it.getParcelable(USER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = UserFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun displayUser(user: User) = with(binding) {
        userImage.setImageResource(user.img)
        tvLogin.text = user.login
        tvAuthorized.text = if (user.isAuthorized) {
            getString(R.string.is_authorized)
        } else {
            getString(R.string.is_not_authorized)
        }
    }

    override fun backPressed() = presenter.backPressed()
}
