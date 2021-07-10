package at.unterhuber.bus_driver.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import at.unterhuber.bus_driver.adapters.CardAdapter2
import at.unterhuber.bus_driver.game.Game
import com.example.bus_driver.databinding.PlayerCardsBinding
import com.google.android.flexbox.*

class PlayerCardsActivity : AppCompatActivity() {
    private lateinit var binding: PlayerCardsBinding
    private lateinit var adapter: CardAdapter2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PlayerCardsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = CardAdapter2(Game.instance.players.map { it.cards }, Game.instance.players.map { it.name }, this)
        binding.cardsRecycler.adapter = adapter
        binding.cardsRecycler.layoutManager = FlexboxLayoutManager(this).apply {
            flexWrap = FlexWrap.WRAP
            flexDirection = FlexDirection.ROW
            alignItems = AlignItems.STRETCH
            justifyContent = JustifyContent.CENTER
        }

        binding.close.setOnClickListener{ finish() }
    }
}