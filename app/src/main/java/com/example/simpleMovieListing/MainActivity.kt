/** Movie6 Android Dev Demo - Created by Ka Ho Cheung at 23/Jun/2022 **/

package com.example.simpleMovieListing

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.simpleMovieListing.databinding.ActivityMainBinding
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.facebook.imagepipeline.core.ImageTranscoderType
import com.facebook.imagepipeline.core.MemoryChunkType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (_binding == null) {
            _binding = ActivityMainBinding.inflate(layoutInflater)
        }
        setContentView(binding.root)
        Fresco.initialize(
            this,
            ImagePipelineConfig.newBuilder(this)
                .setMemoryChunkType(MemoryChunkType.BUFFER_MEMORY)
                .setImageTranscoderType(ImageTranscoderType.JAVA_TRANSCODER)
                .experiment().setNativeCodeDisabled(true)
                .build()
        )
    }
}