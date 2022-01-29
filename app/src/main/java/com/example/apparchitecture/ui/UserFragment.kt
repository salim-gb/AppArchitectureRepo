package com.example.apparchitecture.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.apparchitecture.App
import com.example.apparchitecture.R
import com.example.apparchitecture.databinding.UserFragmentBinding
import com.example.apparchitecture.model.GithubUser
import com.example.apparchitecture.mvp.user.UserPresenter
import com.example.apparchitecture.mvp.user.UserView
import com.example.apparchitecture.repository.GithubUserRepo
import com.example.apparchitecture.ui.common.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

private const val USER_POSITION = "user_position"

class UserFragment :
    MvpAppCompatFragment(R.layout.user_fragment),
    UserView,
    BackButtonListener {

    private var _binding: UserFragmentBinding? = null
    private val binding get() = _binding!!

    private var userPosition: Int? = null

    companion object {
        fun newInstance(position: Int) =
            UserFragment().apply {
                arguments = Bundle().apply {
                    putInt(USER_POSITION, position)
                }
            }
    }

    val presenter: UserPresenter by moxyPresenter {
        UserPresenter(GithubUserRepo(), App.instance.router, userPosition)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.let {
            userPosition = it.getInt(USER_POSITION)
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

    override fun displayUser(user: GithubUser) {
        binding.userImage.setImageResource(user.img)
        binding.tvLogin.text = user.login
    }

    override fun backPressed() = presenter.backPressed()
}
