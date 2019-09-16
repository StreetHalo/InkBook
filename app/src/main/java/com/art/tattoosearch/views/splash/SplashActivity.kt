package com.art.tattoosearch.views.splash

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.art.tattoosearch.App
import com.art.tattoosearch.R
import com.art.tattoosearch.views.main.MainActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashActivity : MvpAppCompatActivity(), SplashInterface {

    private val compositeDisposable = CompositeDisposable()
    private val rxPermissions = RxPermissions(this)

    @Inject
    @InjectPresenter
    lateinit var presenter: SplashPresenter

    @ProvidePresenter
    internal fun providePresenter(): SplashPresenter {
        return presenter
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        App.daggerMainComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

    }

    override fun onResume() {
        super.onResume()
        checkPermissions()
    }

    private fun checkPermissions() {
        val permission = rxPermissions
            .request(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            .delay(750, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { granted ->

                presenter.checkPermissions(granted)

            }
        compositeDisposable.add(permission)
    }

    override fun needPermissions() {
        Toast.makeText(this, R.string.need_permissions, Toast.LENGTH_LONG).show()
    }

    override fun openMainActivity() {
        startActivity(object : Intent(this, MainActivity::class.java) {})
        finish()
    }

    override fun error() {
        Toast.makeText(this, R.string.splash_error, Toast.LENGTH_LONG).show()
    }
}
