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
import kotlinx.coroutines.delay
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
                currentProgress = 0
                startProgress()
            }

            buttonStopProgress.setOnClickListener {
                if (buttonStopProgress.text == "PAUSAR") {
                    buttonStopProgress.text = "RETOMAR"
                    stopProgress()
                } else if (buttonStopProgress.text == "RETOMAR") {
                    buttonStopProgress.text = "PAUSAR"
                    startProgress()
                }
            }
        }
    }

    private fun startProgress() {

        launch = lifecycleScope.launch(Dispatchers.Main) {
            while (currentProgress < 100) {
               currentProgress =
                    CoroutineFactory.calculateProgress(currentProgress)
                binding.launchProgressBar.progress = currentProgress
                binding.labelPercentage.text = "$currentProgress%"
                if (currentProgress < 100){
                    delay(CoroutineFactory.getDelay())

                }

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