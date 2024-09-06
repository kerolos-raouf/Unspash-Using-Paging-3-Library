package com.example.testpaging3library.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.testpaging3library.databinding.FragmentSearchBinding
import com.example.testpaging3library.ui.home.RecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var mAdapter : RecyclerViewAdapter

    private val viewModel : SearchViewModel by viewModels()

    private var lastSearch: String = ""
    private var searchJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        init()
        setUp()

    }

    private fun init()
    {
        mAdapter = RecyclerViewAdapter()
    }

    private fun setUp()
    {
        binding.searchRecyclerView.adapter = mAdapter


        binding.searchEditText.doOnTextChanged { text, start, before, count ->
            val newText = text.toString()

            searchJob?.cancel()
            searchJob = lifecycleScope.launch {
                delay(1000)
                if(newText != lastSearch && newText.isNotBlank())
                {
                    lastSearch = newText
                    viewModel.searchForImages(newText)
                }
            }
        }

        viewModel.searchResult.observe(viewLifecycleOwner){
            lifecycleScope.launch {
                mAdapter.submitData(it)
            }
        }
    }

}