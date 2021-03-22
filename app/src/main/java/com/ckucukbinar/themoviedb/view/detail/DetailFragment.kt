package com.ckucukbinar.themoviedb.view.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ckucukbinar.themoviedb.R
import com.ckucukbinar.themoviedb.data.service.TMDBService
import com.ckucukbinar.themoviedb.databinding.FragmentDetailBinding
import com.ckucukbinar.themoviedb.extension.withMustache
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val args by navArgs<DetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)
        binding.apply {
            val tvShow = args.tvShow
            val imageURL = "${TMDBService.Endpoint.imageURLPrefix}${tvShow.imagePath}"
            Glide.with(ivPoster).load(imageURL)
                .fitCenter()
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.ic_launcher_foreground)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivPoster)

            tvShow.name?.let {
                tvTitle.apply {
                    text = it
                }
            }

            tvShow.overview?.let {
                tvOverview.apply {
                    text = it
                }
            }

            tvShow.voteAverage?.let {
                tvRating.apply {
                    text = context.resources.getString(R.string.rating)
                        .withMustache("rating", it.toString())
                }
            }

            tvShow.voteCount?.let {
                tvVoteCount.apply {
                    text = context.resources.getString(R.string.vote_count)
                        .withMustache("voteCount", it.toString())
                }
            }

            tvShow.originalName?.let {
                tvOriginalName.apply {
                    text = context.resources.getString(R.string.original_name)
                        .withMustache("originalName", it.toString())
                }
            }
        }
    }

}
