package dev.rivu.githubrepositories.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import dev.rivu.githubrepositories.presentation.base.MviIntent
import dev.rivu.githubrepositories.presentation.base.MviState
import dev.rivu.githubrepositories.presentation.base.MviView

abstract class BaseMviActivity<I : MviIntent, S : MviState> : AppCompatActivity(),
    MviView<I, S> {

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
        setContentView(layoutId())
        initView()
        bind()
    }

    fun observeLiveData(liveData: LiveData<S>) {
        liveData.observe(this, Observer(::render))
    }

    protected abstract fun initView()

    protected abstract fun bind()

    protected abstract fun injectDependencies()

    @LayoutRes
    protected abstract fun layoutId(): Int
}