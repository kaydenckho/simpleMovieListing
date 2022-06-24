/** Movie6 Android Dev Demo - Created by Ka Ho Cheung at 23/Jun/2022 **/

package com.example.simpleMovieListing.ui.DetailFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.simpleMovieListing.R
import com.example.simpleMovieListing.databinding.FragmentDetailBinding
import com.example.simpleMovieListing.model.Movie
import com.example.simpleMovieListing.util.InternalDeepLink
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args: DetailFragmentArgs by navArgs()

    val vm: DetailFragmentVM by viewModels()
    var movie: Movie?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (_binding == null){
            _binding = FragmentDetailBinding.inflate(layoutInflater)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movie = InternalDeepLink.convertStringToModel(args.pageBundleModel, Movie::class.java)
        initViews()
    }

    fun initViews(){
        binding.run {
            headerBackTxt.setOnClickListener(this@DetailFragment)
            headerText.text = movie?.name
            avatar.setImageURI(movie?.poster)
            duration.text = "時長: " + movie?.duration + "小時"
        }
    }

    override fun onClick(v: View?) {
        when (v){
            binding.headerBackTxt -> findNavController().navigateUp()
        }
    }
}