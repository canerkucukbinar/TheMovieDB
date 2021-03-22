package com.ckucukbinar.themoviedb.view.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ckucukbinar.themoviedb.R
import com.ckucukbinar.themoviedb.data.service.TMDBService
import com.ckucukbinar.themoviedb.databinding.ItemTvshowBinding
import com.ckucukbinar.themoviedb.extension.withMustache
import com.ckucukbinar.themoviedb.model.TVShow

class PopularTVShowListAdapter(private val listener: OnItemClickListener) :
    PagingDataAdapter<TVShow, PopularTVShowListAdapter.TVShowViewHolder>(
        COMPARATOR
    ) {

    interface OnItemClickListener {
        fun onItemClick(tvShow: TVShow)
    }

    inner class TVShowViewHolder(private val binding: ItemTvshowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.apply {
                    setOnClickListener {
                        val position = bindingAdapterPosition
                        if(position != RecyclerView.NO_POSITION){
                            getItem(position)?.let { tvShow ->
                                listener.onItemClick(tvShow)
                            }
                        }
                    }
                }
            }
        }

        fun bind(tvShow: TVShow) {
            with(binding) {
                val imageURL = "${TMDBService.Endpoint.imageURLPrefix}${tvShow.imagePath}"
                Glide.with(itemView).load(imageURL)
                    .fitCenter()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_launcher_foreground)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(ivPoster)
                tvTitle.apply {
                    text = tvShow.name
                }

                tvRating.apply {
                    text = context.resources.getString(R.string.rating)
                        .withMustache("rating", tvShow.voteAverage.toString())
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowViewHolder {
        val binding = ItemTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TVShowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let {
            holder.bind(it)
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<TVShow>() {
            override fun areItemsTheSame(oldItem: TVShow, newItem: TVShow): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TVShow, newItem: TVShow): Boolean {
                return oldItem == newItem
            }
        }
    }
}