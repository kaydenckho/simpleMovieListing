/** TagHive Android Dev Demo - Created by Ka Ho Cheung at 28/Apr/2022 **/

package com.example.simplecryptolisting.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.simplecryptolisting.adapter.PagingAdapter
import com.example.simplecryptolisting.adapter.PagingLoadStateAdapter
import com.example.simplecryptolisting.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SymbolFragment : Fragment() {

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

            }
        }
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}