package com.ckucukbinar.themoviedb.view.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ckucukbinar.themoviedb.R
import com.ckucukbinar.themoviedb.databinding.FragmentListBinding
import com.ckucukbinar.themoviedb.model.TVShow
import com.ckucukbinar.themoviedb.view.list.adapter.PopularTVShowListAdapter
import com.ckucukbinar.themoviedb.view.list.adapter.viewmodel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list), PopularTVShowListAdapter.OnItemClickListener {
    private val viewModel by viewModels<ListViewModel>()
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: PopularTVShowListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentListBinding.bind(view)
        initView()
        viewModel.popularTVShowList.observe(viewLifecycleOwner) { pagingData ->
            adapter?.run {
                this.submitData(viewLifecycleOwner.lifecycle, pagingData)
            }
        }
    }

    private fun initView() {
        adapter = PopularTVShowListAdapter(this)
        binding.apply {
            rvList.apply {
                setHasFixedSize(true)
                this@ListFragment.adapter?.run {
                    adapter = this
                }
            }
        }
    }

    override fun onItemClick(tvShow: TVShow) {
        val action = ListFragmentDirections.actionNavListToNavDetail(tvShow)
        findNavController().navigate(action)
    }
}
