package at.unterhuber.bus_driver.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources

import androidx.fragment.app.Fragment
import com.example.bus_driver.R

import com.example.bus_driver.databinding.FragmentCardsBinding
import at.unterhuber.bus_driver.game.Player

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
                setFrontSide(player, index, cardImage)
            } else {
                setBackside(cardImage)
            }
        }
    }

    private fun setFrontSide(
        player: Player,
        index: Int,
        cardImage: ImageView
    ) {
        val drawableName = player.cards[index].getDrawableName()
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