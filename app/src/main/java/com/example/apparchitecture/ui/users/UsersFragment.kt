package com.example.apparchitecture.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apparchitecture.App
import com.example.apparchitecture.adapter.AdapterDelegate
import com.example.apparchitecture.databinding.FragmentUsersBinding
import com.example.apparchitecture.domain.model.GithubUserModel
import com.example.apparchitecture.domain.users.GithubUsersRepository
import com.example.apparchitecture.network.ApiHolder
import com.example.apparchitecture.network.NetworkStatus
import com.example.apparchitecture.ui.common.BackButtonListener
import com.example.apparchitecture.ui.common.ImageLoaderImpl
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            App.instance.router,
            GithubUsersRepository(
                ApiHolder.gitHubApiService,
                App.instance.database.userDao,
                NetworkStatus(requireContext())
            )
        )
    }

    private val adapter by lazy {
        AdapterDelegate(listOf(UsersDelegate(ImageLoaderImpl()))) { user ->
            presenter.onUserClicked(user as GithubUserModel)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun init() {
        with(binding) {
            usersRecycler.layoutManager = LinearLayoutManager(context)
            usersRecycler.adapter = adapter
        }
    }

    override fun updateList(users: List<GithubUserModel>) {
        adapter.submitList(users)
    }

    override fun showError(message: String?) {
        Toast.makeText(requireContext(), message.orEmpty(), Toast.LENGTH_LONG).show()
    }

    override fun stopShimmer() {
        binding.shimmerUsers.stopShimmer()
        binding.shimmerUsers.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }
}