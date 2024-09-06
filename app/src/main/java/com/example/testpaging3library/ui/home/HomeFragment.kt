package com.example.testpaging3library.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.map
import com.example.testpaging3library.R
import com.example.testpaging3library.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    @OptIn(ExperimentalPagingApi::class)
    private val viewModel: HomeViewModel by viewModels()
    private val mAdapter = RecyclerViewAdapter(object : RecyclerViewListener {
        override fun stopShimmerEffect() {
            stopShimmerAndShowRecyclerView()
        }

    })

    @OptIn(ExperimentalPagingApi::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        //binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

    }

    @OptIn(ExperimentalPagingApi::class)
    private fun init()
    {
        binding.shimmerFrameLayout.startShimmer()
        binding.homeRecyclerView.adapter = mAdapter
        binding.homeFloatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_navigation_search)
        }

        lifecycleScope.launch {
            viewModel.getAllImages.collectLatest { it ->
                mAdapter.submitData(it)
            }
        }
    }

    fun stopShimmerAndShowRecyclerView()
    {
        binding.shimmerFrameLayout.stopShimmer()
        binding.shimmerFrameLayout.visibility = View.GONE
    }



}