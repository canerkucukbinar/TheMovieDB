package com.ckucukbinar.themoviedb.view.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ckucukbinar.themoviedb.R
import com.ckucukbinar.themoviedb.databinding.FragmentListBinding
import com.ckucukbinar.themoviedb.view.list.adapter.PopularTVShowListAdapter
import com.ckucukbinar.themoviedb.view.list.adapter.viewmodel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list) {
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
        adapter = PopularTVShowListAdapter()
        binding.apply {
            rvList.apply {
                setHasFixedSize(true)
                this@ListFragment.adapter?.run {
                    adapter = this
                }
            }
        }
    }

}
