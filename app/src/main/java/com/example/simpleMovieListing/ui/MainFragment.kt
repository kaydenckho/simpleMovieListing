/** Movie6 Android Dev Demo - Created by Ka Ho Cheung at 23/06/2022 **/

package com.example.simpleMovieListing.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.simpleMovieListing.adapter.PagingAdapter
import com.example.simpleMovieListing.adapter.PagingLoadStateAdapter
import com.example.simpleMovieListing.databinding.FragmentMainBinding
import com.example.simpleMovieListing.util.InternalDeepLink
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    val vm: MainFragmentVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (_binding == null){
            _binding = FragmentMainBinding.inflate(layoutInflater)
        }
        val adapter = PagingAdapter(vm)
        val callback = object: PagingAdapter.PagingAdapterCallback{
            override fun onSymbolClick(symbol: String) {
                InternalDeepLink(this@MainFragment).goSymbolFragment(symbol)
            }
        }
        adapter.callback = callback
        binding.listing.adapter = adapter.withLoadStateHeaderAndFooter(
            header = PagingLoadStateAdapter(adapter),
            footer = PagingLoadStateAdapter(adapter)
        )
        binding.listing.itemAnimator = null
        lifecycleScope.launchWhenCreated {
            vm.pagingListData().collect {
                adapter.submitData(it)
            }
        }
        return binding.root
    }
}