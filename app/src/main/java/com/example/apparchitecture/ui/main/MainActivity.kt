package com.example.apparchitecture.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.apparchitecture.App
import com.example.apparchitecture.R
import com.example.apparchitecture.databinding.ActivtiyMainBinding
import com.example.apparchitecture.network.NetworkStatus
import com.example.apparchitecture.ui.common.BackButtonListener
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(R.layout.activtiy_main), MainView {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigationHolder: NavigatorHolder

    @Inject
    lateinit var networkStatus: NetworkStatus

    private lateinit var binding: ActivtiyMainBinding

    private val navigator = AppNavigator(this, R.id.container)

    private val presenter by moxyPresenter { App.instance.appComponent.provideMainPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.instance.appComponent.inject(this)

        binding = ActivtiyMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        networkStatus.networkStatusSubject
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { isOnline ->

                Log.d("TAG", "network: $isOnline")

                if (isOnline) {
                    presenter.hideNoInternetMessage()
                } else {
                    presenter.showNoInternetMessage()
                }
            }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigationHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            } else {
                super.onBackPressed()
            }
        }
    }

    override fun showNoInternetMessage() {
        binding.tvNoInternet.visibility = View.VISIBLE
    }

    override fun hideNoInternetMessage() {
        binding.tvNoInternet.visibility = View.GONE
    }
}
