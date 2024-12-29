package at.unterhuber.bus_driver.ui

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import at.unterhuber.bus_driver.adapters.PlayersCardAdapter
import at.unterhuber.bus_driver.game.Game
import at.unterhuber.bus_driver.databinding.ActivityPlayerCardsBinding
import com.google.android.flexbox.*

class PlayerCardsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerCardsBinding
    private lateinit var adapter: PlayersCardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerCardsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = PlayersCardAdapter(Game.instance.players.map { it.cards }, Game.instance.players.map { it.name }, this)
        binding.cardsRecycler.adapter = adapter
        binding.cardsRecycler.layoutManager = FlexboxLayoutManager(this).apply {
            flexWrap = FlexWrap.WRAP
            flexDirection = FlexDirection.ROW
            alignItems = AlignItems.STRETCH
            justifyContent = JustifyContent.CENTER
        }

        binding.close.setOnClickListener{ finish() }
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }
}