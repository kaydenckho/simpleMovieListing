package com.example.simpleMovieListing.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simpleMovieListing.model.PriceModel
import com.example.simpleMovieListing.network.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailFragmentVM @Inject constructor() : ViewModel() {

    @Inject
    lateinit var apiRepository: ApiRepository
}