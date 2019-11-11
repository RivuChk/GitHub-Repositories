package dev.rivu.githubrepositories.trendingprojects

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.jakewharton.rxbinding3.view.clicks
import dev.rivu.githubrepositories.R
import dev.rivu.githubrepositories.presentation.model.TrendingProjectPresentation
import dev.rivu.githubrepositories.utils.load
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_trending_project.view.*

class TrendingProjectListAdapter : RecyclerView.Adapter<TrendingProjectListAdapter.ViewHolder>() {

    var itemList: List<TrendingProjectPresentation> = listOf()
        private set

    private val clickObservable: PublishSubject<ItemClickData> = PublishSubject.create()

    val clickEvent: Observable<ItemClickData> = clickObservable.hide()

    fun updateItems(itemList: List<TrendingProjectPresentation>) {
        val diffResult =
            DiffUtil.calculateDiff(TrendingProjectDiffCallback(itemList, this.itemList))
        diffResult.dispatchUpdatesTo(this)
        this.itemList = itemList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_trending_project,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(itemList[position], position)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindView(trendingProject: TrendingProjectPresentation, position: Int) {

            itemView.clicks()
                .map {
                    ItemClickData(
                        position, trendingProject
                    )
                }
                .subscribe(clickObservable)

            itemView.ivAvatar.load(
                trendingProject.avatar,
                RequestOptions()
                    .centerCrop()
                    .format(DecodeFormat.PREFER_ARGB_8888)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.circle)
                    .error(android.R.drawable.ic_menu_close_clear_cancel),
                CircleCrop()
            )

            itemView.tvAuthor.text = trendingProject.author
            itemView.tvName.text = trendingProject.name
        }
    }

    data class ItemClickData(
        val position: Int,
        val item: TrendingProjectPresentation
    )
}