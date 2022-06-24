package com.example.simpleMovieListing.ui.DetailFragment

import androidx.lifecycle.ViewModel
import com.example.simpleMovieListing.network.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailFragmentVM @Inject constructor() : ViewModel() {

    @Inject
    lateinit var apiRepository: ApiRepository
}