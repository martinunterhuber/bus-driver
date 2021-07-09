package at.unterhuber.bus_driver.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources

import androidx.fragment.app.Fragment
import com.example.bus_driver.R

import at.unterhuber.bus_driver.game.Player
import com.example.bus_driver.databinding.FragmentPlayerCardsBinding

class PlayerCardFragment(private val startPlayer: Player) : Fragment() {
    private lateinit var binding: FragmentPlayerCardsBinding
    private lateinit var cardImages: List<ImageView>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayerCardsBinding.inflate(inflater, container, false)
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
                setFrontSide(player.cards[index].getDrawableName(), cardImage)
            } else {
                setBackside(cardImage)
            }
        }
    }

    private fun setFrontSide(
        drawableName: String,
        cardImage: ImageView
    ) {
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