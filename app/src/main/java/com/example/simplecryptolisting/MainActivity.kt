package com.example.simplecryptolisting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.simplecryptolisting.adapter.PagingAdapter
import com.example.simplecryptolisting.adapter.PagingLoadStateAdapter
import com.example.simplecryptolisting.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    val vm: MainActivityVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val adapter = PagingAdapter(vm)
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
    }
}