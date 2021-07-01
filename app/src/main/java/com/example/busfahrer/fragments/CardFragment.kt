package com.example.busfahrer.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources

import androidx.fragment.app.Fragment
import com.example.busfahrer.R

import com.example.busfahrer.databinding.FragmentCardsBinding
import com.example.busfahrer.game.Player

class CardFragment(private val player: Player) : Fragment() {
    private lateinit var binding: FragmentCardsBinding
    private lateinit var cardImages: List<ImageView>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCardsBinding.inflate(inflater, container, false)
        cardImages = listOf(binding.card1, binding.card2, binding.card3, binding.card4)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        for ((index, cardImage) in cardImages.withIndex()) {
            if (index < player.cards.size) {
                cardImage.setImageDrawable(AppCompatResources.getDrawable(requireContext(), player.cards[index].drawableId))
            } else {
                cardImage.setImageDrawable(AppCompatResources.getDrawable(requireContext(), R.drawable.seven_bells))
            }
        }
    }
}