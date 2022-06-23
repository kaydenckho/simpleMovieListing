/** TagHive Android Dev Demo - Created by Ka Ho Cheung at 28/Apr/2022 **/

package com.example.simpleMovieListing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.simpleMovieListing.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (_binding == null){
            _binding = ActivityMainBinding.inflate(layoutInflater)
        }
        setContentView(binding.root)
    }
}