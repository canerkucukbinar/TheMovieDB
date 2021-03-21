package com.ckucukbinar.themoviedb.view.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ckucukbinar.themoviedb.R
import com.ckucukbinar.themoviedb.data.service.TMDBService
import com.ckucukbinar.themoviedb.databinding.ItemTvshowBinding
import com.ckucukbinar.themoviedb.model.TVShow

class PopularTVShowListAdapter : PagingDataAdapter<TVShow, PopularTVShowListAdapter.TVShowViewHolder>(
    COMPARATOR) {
    inner class TVShowViewHolder(private val binding: ItemTvshowBinding)
        : RecyclerView.ViewHolder(binding.root){
            fun bind(tvShow: TVShow){
                with(binding){
                    val imageURL = "${TMDBService.Endpoint.imageURLPrefix}${tvShow.imagePath}"
                    Glide.with(itemView).load(imageURL)
                        .centerCrop()
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .error(R.drawable.ic_launcher_foreground)
                        .into(ivPoster)
                    tvTitle.apply {
                        text = tvShow.name
                    }

                    tvRating.apply {
                        text = tvShow.voteAverage.toString()
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

    companion object{
        private val COMPARATOR = object: DiffUtil.ItemCallback<TVShow>(){
            override fun areItemsTheSame(oldItem: TVShow, newItem: TVShow): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TVShow, newItem: TVShow): Boolean {
                return oldItem == newItem
            }
        }
    }
}