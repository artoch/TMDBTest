package com.example.advancetest.ui.main.view_movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.advancetest.common.extension.urlPost
import com.example.advancetest.common.model.GlideHelper
import com.example.advancetest.data.domain.MovieItemDomain
import com.example.advancetest.databinding.ItemPosterBinding
import javax.inject.Inject

class PagingMovieAdapter @Inject constructor(
    private val action: (MovieItemDomain) -> Unit
) :
    PagingDataAdapter<MovieItemDomain, PagingMovieAdapter.ViewHolder>(MovieComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemPosterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class ViewHolder(private val binding: ItemPosterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MovieItemDomain) = with(binding) {
            tvTitle.text = item.title
            tvStar.text = item.voteAverage.toString()
            GlideHelper.invoke(item.posterPath.urlPost(),ivPoster)
            cvButton.setOnClickListener{
                action.invoke(item)
            }
        }
    }

    object MovieComparator : DiffUtil.ItemCallback<MovieItemDomain>() {
        override fun areItemsTheSame(oldItem: MovieItemDomain, newItem: MovieItemDomain) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MovieItemDomain, newItem: MovieItemDomain) =
            oldItem == newItem
    }
}