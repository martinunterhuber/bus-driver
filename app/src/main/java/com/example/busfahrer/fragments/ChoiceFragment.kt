package com.example.busfahrer.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.example.busfahrer.R
import com.example.busfahrer.databinding.FragmentChoiceBinding
import com.example.busfahrer.choices.Choice
import com.example.busfahrer.ui.GameActivity

class ChoiceFragment(private val choice1: Choice, private val choice2: Choice) : Fragment() {
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
            (requireActivity() as GameActivity).choice(choice1)
        }
        binding.box2.setOnClickListener{
            (requireActivity() as GameActivity).choice(choice2)
        }
        binding.symbolBox1.setImageDrawable(AppCompatResources.getDrawable(requireContext(), choice1.drawable))
        binding.symbolBox2.setImageDrawable(AppCompatResources.getDrawable(requireContext(), choice2.drawable))
    }
}