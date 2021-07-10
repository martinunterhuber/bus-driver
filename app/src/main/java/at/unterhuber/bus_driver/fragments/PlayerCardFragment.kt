package at.unterhuber.bus_driver.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import at.unterhuber.bus_driver.adapters.CardAdapter

import at.unterhuber.bus_driver.game.Player
import com.example.bus_driver.databinding.FragmentCardsBinding
import com.google.android.flexbox.*

class PlayerCardFragment(private val startPlayer: Player) : Fragment() {
    private lateinit var binding: FragmentCardsBinding
    private lateinit var adapter: CardAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCardsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CardAdapter(ArrayList(), requireContext())
        binding.cardRecycler.adapter = adapter
        binding.cardRecycler.layoutManager = FlexboxLayoutManager(requireContext()).apply {
            flexWrap = FlexWrap.WRAP
            flexDirection = FlexDirection.ROW
            alignItems = AlignItems.STRETCH
            justifyContent = JustifyContent.CENTER
        }
        adapter.setSelectedPosition(-1)
        updateCards(startPlayer)
    }

    fun updateCards(player: Player) {
        adapter.updateCards(player.cards)
    }
}