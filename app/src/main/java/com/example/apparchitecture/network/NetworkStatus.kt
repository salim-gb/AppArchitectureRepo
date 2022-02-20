package com.example.apparchitecture.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.core.content.getSystemService
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject

class NetworkStatus(context: Context) {

    private val connectivityManager = context.getSystemService<ConnectivityManager>()
    private val request = NetworkRequest.Builder().build()
    val networkStatusSubject: BehaviorSubject<Boolean> = BehaviorSubject.create()

    init {
        networkStatusSubject.onNext(false)

        connectivityManager?.registerNetworkCallback(
            request,
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    networkStatusSubject.onNext(true)
                }

                override fun onLost(network: Network) {
//                    super.onLost(network)
                    networkStatusSubject.onNext(false)
                }

                override fun onUnavailable() {
//                    super.onUnavailable()
                    networkStatusSubject.onNext(false)
                }
            }
        )
    }

    fun isOnlineSingle(): Single<Boolean> = networkStatusSubject.first(true)
}