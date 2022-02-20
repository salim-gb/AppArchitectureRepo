package com.example.apparchitecture.ui.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.apparchitecture.App
import com.example.apparchitecture.R
import com.example.apparchitecture.databinding.FragmentDetailsBinding
import com.example.apparchitecture.domain.model.GithubRepoModel
import com.example.apparchitecture.ui.common.BackButtonListener
import com.example.apparchitecture.ui.common.ImageLoaderImpl
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

private const val GITHUB_REPO = "github_repo_details"

class RepoDetailsFragment : MvpAppCompatFragment(R.layout.fragment_details), RepoDetailsView,
    BackButtonListener {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private var githubRepo: GithubRepoModel? = null

    private val presenter by moxyPresenter {
        App.instance.appComponent.provideReposDetailsPresenterFactory().presenter(githubRepo)
    }

    private val imageLoader by lazy {
        ImageLoaderImpl()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.let {
            githubRepo = it.getParcelable(GITHUB_REPO)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun showDetails(repo: GithubRepoModel) {
        with(binding) {
            repo.apply {
                owner.avatarUrl?.let { url ->
                    imageLoader.loadInto(url, userImage)
                }
                tvName.text = getString(R.string.repo_name, name)
                tvOwner.text = getString(R.string.login, owner.login)
                if (description == null) {
                    tvDescription.visibility = View.GONE
                } else {
                    tvDescription.text = getString(R.string.description, description)
                }
                tvForks.text = getString(R.string.forks, forks.toString())
                tvVisibility.text = getString(R.string.visibility, visibility.toString())
                language?.let {
                    tvLanguage.text = getString(R.string.language, language)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }

    companion object {
        fun newInstance(repo: GithubRepoModel) =
            RepoDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(GITHUB_REPO, repo)
                }
            }
    }
}