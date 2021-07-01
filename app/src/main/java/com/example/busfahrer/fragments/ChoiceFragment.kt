package com.example.busfahrer.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.example.busfahrer.R
import com.example.busfahrer.databinding.FragmentChoiceBinding

class ChoiceFragment : Fragment() {
    private lateinit var binding: FragmentChoiceBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChoiceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.box1.setOnClickListener{
            // call next on activity
        }
        binding.box2.setOnClickListener{
            // call next on activity
        }
        binding.symbolBox1.setImageDrawable(AppCompatResources.getDrawable(requireContext(), R.drawable.ic_baseline_arrow_upward_24))
        binding.symbolBox2.setImageDrawable(AppCompatResources.getDrawable(requireContext(), R.drawable.ic_baseline_arrow_downward_24))
    }
}