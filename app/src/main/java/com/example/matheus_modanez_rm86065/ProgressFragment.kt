package com.example.matheus_modanez_rm86065

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.matheus_modanez_rm86065.databinding.FragmentProgressBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ProgressFragment : Fragment() {
    private lateinit var binding: FragmentProgressBinding
    private var launch: Job? = null
    var currentProgress = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProgressBinding.inflate(inflater, container, false)
        setupButtons()
        return binding.root
    }

    private fun setupButtons() {

        with(binding) {
            buttonStartProgress.setOnClickListener {
                startProgress(currentProgress)
            }

            buttonStopProgress.setOnClickListener {
                if (buttonStopProgress.text == "PAUSAR") {
                    buttonStopProgress.text = "RETOMAR"
                    stopProgress()
                    this@ProgressFragment.currentProgress = binding.launchProgressBar.progress
                } else if (buttonStopProgress.text == "RETOMAR") {
                    buttonStopProgress.text = "PAUSAR"
                    startProgress(binding.launchProgressBar.progress)
                }
            }
        }
    }

    private fun startProgress(currentProgress: Int) {
        var countDelay = 0L
        var countDelayTotal = 0L

        launch = lifecycleScope.launch(Dispatchers.Main) {
            while (currentProgress < 100) {
                this@ProgressFragment.currentProgress =
                    CoroutineFactory.calculateProgress(currentProgress)
                countDelay = CoroutineFactory.getDelay()
                countDelayTotal += countDelay
                binding.launchProgressBar.progress = currentProgress
                binding.labelPercentage.text = "$currentProgress%"

            }
            findNavController().navigate(R.id.action_progress_to_downloadCompletedFragment)
        }
    }

    private fun stopProgress() {
        launch?.cancel()
    }


    companion object {
        fun buildIntent(context: Context) = Intent(
            context,
            ProgressFragment::class.java
        )
    }
}