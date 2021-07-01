package com.example.busfahrer.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.example.busfahrer.R
import com.example.busfahrer.databinding.FragmentChoiceResultBinding

class ChoiceResultFragment: Fragment() {
    private lateinit var binding: FragmentChoiceResultBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChoiceResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nextButton.setOnClickListener{
            // call next on activity
        }
        binding.resultImage.setImageDrawable(AppCompatResources.getDrawable(requireContext(), R.drawable.ic_baseline_check_24))
    }
}