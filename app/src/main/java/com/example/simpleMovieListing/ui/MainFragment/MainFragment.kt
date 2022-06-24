/** Movie6 Android Dev Demo - Created by Ka Ho Cheung at 23/Jun/2022 **/

package com.example.simpleMovieListing.ui.MainFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.simpleMovieListing.R
import com.example.simpleMovieListing.databinding.FragmentMainBinding
import com.example.simpleMovieListing.model.Movie
import com.example.simpleMovieListing.ui.MainFragment.adapter.PagingAdapter
import com.example.simpleMovieListing.ui.MainFragment.adapter.PagingLoadStateAdapter
import com.example.simpleMovieListing.util.InternalDeepLink
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private var layoutManager: GridLayoutManager? = null
    val vm: MainFragmentVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                val callback = object : PagingAdapter.PagingAdapterCallback {
                    override fun onMovieClick(movie: Movie) {
                        InternalDeepLink(this@MainFragment).goDetailFragment(movie)
                    }
                }
                val adapter = PagingAdapter(vm)
                adapter.callback = callback
                binding.listing.adapter = adapter.withLoadStateHeaderAndFooter(
                    header = PagingLoadStateAdapter(adapter),
                    footer = PagingLoadStateAdapter(adapter)
                )
                layoutManager = GridLayoutManager(requireContext(), vm.spanCount.value ?: 3)
                binding.listing.layoutManager = layoutManager
                binding.viewType.setOnClickListener { imageView ->
                    binding.listing.layoutManager.let { lm ->
                        if (lm is GridLayoutManager) {
                            if (lm.spanCount == 3) {
                                vm.setSpanCount(1)
                                imageView.isActivated = true
                            } else {
                                vm.setSpanCount(3)
                                imageView.isActivated = false
                            }

                            adapter.notifyItemRangeChanged(0, adapter.itemCount)
                        }
                    }
                }
                vm.spanCount.observe(viewLifecycleOwner) {
                    layoutManager?.spanCount = it
                }
                vm.movies.observe(viewLifecycleOwner) {
                    lifecycleScope.launch {
                        adapter.submitData(it)
                    }
                }
                vm.pagingListData().collect {
                    vm.movies.value = it
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}