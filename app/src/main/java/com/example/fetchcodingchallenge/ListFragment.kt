package com.example.fetchcodingchallenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.fetchcodingchallenge.databinding.FragmentListBinding
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_INDEFINITE
import com.google.android.material.snackbar.Snackbar

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding

    private val viewModel by viewModels<ListItemViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.recyclerView.adapter = ListItemAdapter()

        observeStatus()

        return binding.root
    }

    private fun observeStatus() {
        viewModel.statusMessage.observe(viewLifecycleOwner, Observer { event ->
            event?.getContentIfNotHandled()?.let {
                Snackbar.make(requireView(), it, LENGTH_INDEFINITE)
                    .setAction(R.string.retry_action){
                        viewModel.retrieveListItems()
                    }
                    .show()
            }
        })
    }
}