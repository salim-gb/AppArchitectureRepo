package com.example.apparchitecture.ui.repos

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apparchitecture.App
import com.example.apparchitecture.R
import com.example.apparchitecture.adapter.AdapterDelegate
import com.example.apparchitecture.databinding.FragmentReposBinding
import com.example.apparchitecture.domain.model.GithubRepoModel
import com.example.apparchitecture.domain.model.GithubUserModel
import com.example.apparchitecture.domain.repos.GithubRepoRepository
import com.example.apparchitecture.network.ApiHolder
import com.example.apparchitecture.network.NetworkStatus
import com.example.apparchitecture.ui.common.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

private const val GITHUB_USER_MODEL = "github_user"

class ReposFragment : MvpAppCompatFragment(R.layout.fragment_repos), ReposView, BackButtonListener {

    private var _binding: FragmentReposBinding? = null
    private val binding get() = _binding!!

    private var githubUser: GithubUserModel? = null

    private val presenter by moxyPresenter {
        ReposPresenter(
            githubUser,
            GithubRepoRepository(ApiHolder.gitHubApiService),
            App.instance.router
        )
    }

    private val adapter by lazy {
        AdapterDelegate(listOf(ReposDelegate())) {
            presenter.onRepoClick(it as GithubRepoModel)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.let {
            githubUser = it.getParcelable(GITHUB_USER_MODEL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReposBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun init() {
        binding.reposRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.reposRecycler.adapter = adapter
    }

    override fun showRepos(repos: List<GithubRepoModel>) {
        adapter.submitList(repos)
    }

    override fun stopShimmer() {
        binding.shimmerRepos.stopShimmer()
        binding.shimmerRepos.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun backPressed():Boolean {
        presenter.backPressed()
        return true
    }

    companion object {
        fun newInstance(githubUser: GithubUserModel) =
            ReposFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(GITHUB_USER_MODEL, githubUser)
                }
            }
    }
}