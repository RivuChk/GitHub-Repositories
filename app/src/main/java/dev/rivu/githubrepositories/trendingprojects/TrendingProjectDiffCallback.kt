package dev.rivu.githubrepositories.trendingprojects

import androidx.recyclerview.widget.DiffUtil
import dev.rivu.githubrepositories.presentation.model.TrendingProjectPresentation


class TrendingProjectDiffCallback(
    private val newList: List<TrendingProjectPresentation>,
    private val oldList: List<TrendingProjectPresentation>
) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size


    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val equality =
            oldList[oldItemPosition].url.equals(newList[newItemPosition].url, ignoreCase = false)
        //If urls are same of two projects, then other details are also same
        return equality
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true//if items are same, contents are same
    }
}