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

class CardFragment(private val startPlayer: Player) : Fragment() {
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
        updateCards(startPlayer)
    }

    fun updateCards(player: Player) {
        for ((index, cardImage) in cardImages.withIndex()) {
            if (index < player.cards.size) {
                setFrontside(player, index, cardImage)
            } else {
                setBackside(cardImage)
            }
        }
    }

    private fun setFrontside(
        player: Player,
        index: Int,
        cardImage: ImageView
    ) {
        val drawableName =
            "${player.cards[index].suite.name.lowercase()}_${player.cards[index].rank.name.lowercase()}"
        val drawable =
            resources.getIdentifier(drawableName, "drawable", requireActivity().packageName)
        cardImage.setImageDrawable(
            AppCompatResources.getDrawable(
                requireContext(),
                drawable
            )
        )
    }

    private fun setBackside(cardImage: ImageView) {
        cardImage.setImageDrawable(
            AppCompatResources.getDrawable(
                requireContext(),
                R.drawable.backside
            )
        )
    }


}