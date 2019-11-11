package dev.rivu.githubrepositories.trendingprojects

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import dev.rivu.githubrepositories.R
import dev.rivu.githubrepositories.base.BaseMviActivity
import dev.rivu.githubrepositories.presentation.model.TrendingProjectPresentation
import dev.rivu.githubrepositories.presentation.trendingprojects.TrendingProjectsIntent
import dev.rivu.githubrepositories.presentation.trendingprojects.TrendingProjectsState
import dev.rivu.githubrepositories.presentation.trendingprojects.TrendingProjectsViewModel
import dev.rivu.githubrepositories.presentation.trendingprojects.TrendingProjectsViewModelFactory
import dev.rivu.githubrepositories.trendingprojects.injection.inject
import dev.rivu.githubrepositories.utils.gone
import dev.rivu.githubrepositories.utils.isVisible
import dev.rivu.githubrepositories.utils.visible
import io.reactivex.Observable
import kotlinx.android.synthetic.main.content_trending_projects.*
import kotlinx.android.synthetic.main.activity_trending_projects.*
import timber.log.Timber
import javax.inject.Inject

class TrendingProjectsActivity : BaseMviActivity<TrendingProjectsIntent, TrendingProjectsState>() {

    @Inject
    lateinit var trendingProjectsViewModelFactory: TrendingProjectsViewModelFactory

    private val viewModel: TrendingProjectsViewModel by lazy {
        ViewModelProviders.of(this, trendingProjectsViewModelFactory)
            .get(TrendingProjectsViewModel::class.java)
    }

    private val adapter: TrendingProjectListAdapter by lazy {
        TrendingProjectListAdapter()
    }

    override fun layoutId(): Int = R.layout.activity_trending_projects

    override fun initView() {
        setSupportActionBar(toolbar)
        toolbar.overflowIcon = ContextCompat.getDrawable(this, R.drawable.more_black)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        rvTrendingProjects.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.trending, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.item_sort_name -> {
                //TODO
                true
            }
            R.id.item_sort_star -> {
                //TODO
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun bind() {
        observeLiveData(viewModel.states())
        viewModel.processIntents(intents())
    }

    override fun intents(): Observable<TrendingProjectsIntent> {
        return Observable.just(TrendingProjectsIntent.InitialIntent)
    }

    override fun render(state: TrendingProjectsState) {
        Timber.d("New State: $state")
        if(state.isLoading) {
            showLoading()
        } else {
            hideLoading()
        }
        if(state.error != null) {
            Timber.e(state.error)
        }
        if(state.data.isNotEmpty()) {
            showData(state.data)
        }
    }

    private fun showLoading() {
        if(swipeRefresh.isVisible()) {
            swipeRefresh.isRefreshing = true
        } else {
            shimmerLayout.visible()
            shimmerLayout.startShimmer()
        }
    }

    private fun hideLoading() {
        if(swipeRefresh.isVisible()) {
            swipeRefresh.isRefreshing = false
        } else {
            shimmerLayout.stopShimmer()
            shimmerLayout.gone()
            swipeRefresh.visible()
        }
    }

    private fun showData(data: List<TrendingProjectPresentation>) {
        adapter.updateItems(data)
    }

    override fun injectDependencies() {
        inject()
    }
}
