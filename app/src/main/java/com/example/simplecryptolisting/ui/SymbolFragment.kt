/** TagHive Android Dev Demo - Created by Ka Ho Cheung at 28/Apr/2022 **/

package com.example.simplecryptolisting.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.simplecryptolisting.databinding.FragmentSymbolBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SymbolFragment : Fragment() {

    private var _binding: FragmentSymbolBinding? = null
    private val binding get() = _binding!!
    private val args: SymbolFragmentArgs by navArgs()

    val vm: SymbolFragmentVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (_binding == null){
            _binding = FragmentSymbolBinding.inflate(layoutInflater)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch{
            vm.getSymbol(args.symbol)
            vm.priceLiveData.observe(viewLifecycleOwner){
                binding.symbol.text = it.symbol
                binding.baseAsset.text = it.baseAsset
                binding.quoteAsset.text = it.quoteAsset
                binding.openPrice.text = it.openPrice
                binding.lowPrice.text = it.lowPrice
                binding.highPrice.text = it.highPrice
                binding.lastPrice.text = it.lastPrice
                binding.volume.text = it.volume
                binding.bidPrice.text = it.bidPrice
                binding.askPrice.text = it.askPrice
                binding.at.text = it.at
            }
        }
    }
}