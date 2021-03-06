package dev.rivu.githubrepositories.trendingprojects

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.lifecycle.ViewModelProviders
import com.jakewharton.rxbinding3.swiperefreshlayout.refreshes
import com.jakewharton.rxbinding3.view.clicks
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
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_trending_projects.*
import kotlinx.android.synthetic.main.content_trending_projects.*
import kotlinx.android.synthetic.main.layout_error.*
import kotlinx.android.synthetic.main.layout_menu.view.*
import timber.log.Timber
import javax.inject.Inject

class TrendingProjectsActivity : BaseMviActivity<TrendingProjectsIntent, TrendingProjectsState>() {

    @Inject
    lateinit var trendingProjectsViewModelFactory: TrendingProjectsViewModelFactory

    @Inject
    lateinit var adapter: TrendingProjectListAdapter

    private val viewModel: TrendingProjectsViewModel by lazy {
        ViewModelProviders.of(this, trendingProjectsViewModelFactory)
            .get(TrendingProjectsViewModel::class.java)
    }

    private val clearClickPublisher: PublishSubject<TrendingProjectsIntent.ClearClickIntent> by lazy {
        PublishSubject.create<TrendingProjectsIntent.ClearClickIntent>()
    }

    private val sortPublisher: PublishSubject<TrendingProjectsIntent.SortIntent> by lazy {
        PublishSubject.create<TrendingProjectsIntent.SortIntent>()
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
            clearClickPublisher,
            btnRetry.clicks()
                .map {
                    TrendingProjectsIntent.LoadIntent("", "")
                },
            sortPublisher
        )
    }

    override fun render(state: TrendingProjectsState) {
        Timber.d("New State: $state")
        if (state.isLoading) {
            showLoading()
        } else {
            hideLoading()
        }
        if (state.error == null) {
            hideError()
        } else {
            showError(state.error)
        }
        if (state.data.isNotEmpty()) {
            showData(state.data)
        }
        if (state.clickedViewPosition in adapter.itemList.indices) {
            propagateClickAndClear(state.clickedViewPosition)
        }
        if (state.resetScrollState) {
            rvTrendingProjects.postDelayed({
                rvTrendingProjects.smoothScrollToPosition(0)
            }, 500)
            //Reset scroll after 500 milliseconds, so that if adapter is sorting the result, reset'll be after that
        }
    }

    override fun injectDependencies() {
        inject()
    }

    private fun showPopupMenu(view: View) {
        if (adapter.itemList.isNotEmpty() && !swipeRefresh.isRefreshing) { //TODO: Add check, if not loading, or data is not empty
            val layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val popupView = layoutInflater.inflate(R.layout.layout_menu, null)

            val popupWindow = PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            popupWindow.isOutsideTouchable = true
            popupView.tvMenuName.setOnClickListener {
                adapter.detailPosition = -1
                sortPublisher.onNext(TrendingProjectsIntent.SortIntent.ByName(adapter.itemList))
                popupWindow.dismiss()
            }
            popupView.tvMenuStars.setOnClickListener {
                adapter.detailPosition = -1
                sortPublisher.onNext(TrendingProjectsIntent.SortIntent.ByStars(adapter.itemList))
                popupWindow.dismiss()
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                popupWindow.elevation = 10f
            }

            popupWindow.showAsDropDown(view)
        }
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
        swipeRefresh.isRefreshing = false
        shimmerLayout.stopShimmer()
        shimmerLayout.gone()
    }

    private fun showError(error: Throwable?) {
        contentTrendingProjects.gone()
        swipeRefresh.gone()
        errorLayout.visible()
        Timber.e(error)
    }

    private fun hideError() {
        contentTrendingProjects.visible()
        errorLayout.gone()
    }

    private fun showData(data: List<TrendingProjectPresentation>) {
        swipeRefresh.visible()
        adapter.updateItems(data)
    }

    private fun propagateClickAndClear(clickedItemPosition: Int) {
        adapter.detailPosition = clickedItemPosition
        clearClickPublisher.onNext(TrendingProjectsIntent.ClearClickIntent)
    }
}
