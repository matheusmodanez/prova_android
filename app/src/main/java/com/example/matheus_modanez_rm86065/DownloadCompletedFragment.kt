package com.example.matheus_modanez_rm86065

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.matheus_modanez_rm86065.databinding.FragmentDownloadCompletedBinding

class DownloadCompletedFragment : Fragment() {
    private lateinit var binding: FragmentDownloadCompletedBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDownloadCompletedBinding.inflate(inflater, container, false)
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_downloadCompletedFragment_to_progress)
        }

        return binding.root
    }

}