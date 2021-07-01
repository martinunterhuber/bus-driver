package com.example.busfahrer.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.example.busfahrer.R
import com.example.busfahrer.databinding.FragmentChoiceResultBinding
import com.example.busfahrer.ui.GameActivity

class ChoiceResultFragment(private val result: Boolean): Fragment() {
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
            (requireActivity() as GameActivity).next()
        }
        binding.resultImage.setImageDrawable(AppCompatResources.getDrawable(requireContext(), if (result) R.drawable.ic_baseline_check_24 else R.drawable.ic_baseline_close_24))
    }
}