package dev.rivu.githubrepositories.trendingprojects

import android.view.*
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
import android.widget.PopupWindow
import android.view.ViewGroup
import android.view.LayoutInflater
import android.content.Context
import android.os.Build
import com.jakewharton.rxbinding3.swiperefreshlayout.refreshes
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.layout_menu.view.*


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

    private val clearClickPublisher: PublishSubject<TrendingProjectsIntent.ClearClickIntent> by lazy {
        PublishSubject.create<TrendingProjectsIntent.ClearClickIntent>()
    }

    override fun layoutId(): Int = R.layout.activity_trending_projects

    override fun initView() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        rvTrendingProjects.adapter = adapter
        ivMenu.setOnClickListener(::showPopupMenu)
    }

    override fun bind() {
        observeLiveData(viewModel.states())
        viewModel.processIntents(intents())
    }

    override fun intents(): Observable<TrendingProjectsIntent> {
        return Observable.mergeArray(
            Observable.just(TrendingProjectsIntent.InitialIntent),
            swipeRefresh.refreshes()
                .map {
                    TrendingProjectsIntent.RefreshIntent("", "")
                },
            adapter.clickEvent
                .map {
                    TrendingProjectsIntent.ClickIntent(it.position)
                },
            clearClickPublisher
        )
    }

    override fun render(state: TrendingProjectsState) {
        Timber.d("New State: $state")
        if (state.isLoading) {
            showLoading()
        } else {
            hideLoading()
        }
        if (state.error != null) {
            Timber.e(state.error)
        }
        if (state.data.isNotEmpty()) {
            showData(state.data)
        }
    }

    private fun showPopupMenu(view: View) {
        val layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = layoutInflater.inflate(R.layout.layout_menu, null)

        val popupWindow = PopupWindow(
            popupView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        popupWindow.isOutsideTouchable = true
        popupView.tvMenuName.setOnClickListener {
            //TODO: Fire Sort Intent
            popupWindow.dismiss()
        }
        popupView.tvMenuStars.setOnClickListener {
            //TODO: Fire Sort Intent
            popupWindow.dismiss()
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.elevation = 10f
        }

        popupWindow.showAsDropDown(view)
    }

    private fun showLoading() {
        if (swipeRefresh.isVisible()) {
            swipeRefresh.isRefreshing = true
        } else {
            shimmerLayout.visible()
            shimmerLayout.startShimmer()
        }
    }

    private fun hideLoading() {
        if (swipeRefresh.isVisible()) {
            swipeRefresh.isRefreshing = false
        } else {
            shimmerLayout.stopShimmer()
            shimmerLayout.gone()
            swipeRefresh.visible()
        }
    }

    private fun performClickAndClear() {
        //TODO: Expand Item
        clearClickPublisher.onNext(TrendingProjectsIntent.ClearClickIntent)
    }

    private fun showData(data: List<TrendingProjectPresentation>) {
        adapter.updateItems(data)
    }

    override fun injectDependencies() {
        inject()
    }
}
