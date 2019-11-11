package dev.rivu.githubrepositories.trendingprojects

import androidx.lifecycle.ViewModelProviders
import dev.rivu.githubrepositories.R
import dev.rivu.githubrepositories.base.BaseMviActivity
import dev.rivu.githubrepositories.presentation.trendingprojects.TrendingProjectsIntent
import dev.rivu.githubrepositories.presentation.trendingprojects.TrendingProjectsState
import dev.rivu.githubrepositories.presentation.trendingprojects.TrendingProjectsViewModel
import dev.rivu.githubrepositories.presentation.trendingprojects.TrendingProjectsViewModelFactory
import dev.rivu.githubrepositories.trendingprojects.injection.inject
import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject

class TrendingProjectsActivity : BaseMviActivity<TrendingProjectsIntent, TrendingProjectsState>() {

    @Inject
    lateinit var trendingProjectsViewModelFactory: TrendingProjectsViewModelFactory

    private val viewModel: TrendingProjectsViewModel by lazy {
        ViewModelProviders.of(this, trendingProjectsViewModelFactory)
            .get(TrendingProjectsViewModel::class.java)
    }

    override fun layoutId(): Int = R.layout.activity_trending_projects

    override fun initView() {

    }

    override fun bind() {
        observeLiveData(viewModel.states())
        viewModel.processIntents(intents())
    }

    override fun intents(): Observable<TrendingProjectsIntent> {
        return Observable.just(TrendingProjectsIntent.InitialIntent)
    }

    override fun render(state: TrendingProjectsState) {
        Timber.d("State: $state")
        if(state.error != null) {
            Timber.e(state.error)
        }
    }

    override fun injectDependencies() {
        inject()
    }
}
