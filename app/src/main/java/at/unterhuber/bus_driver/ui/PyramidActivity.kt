package at.unterhuber.bus_driver.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import at.unterhuber.bus_driver.adapters.PyramidAdapter
import at.unterhuber.bus_driver.cards.Card
import at.unterhuber.bus_driver.game.Game
import com.example.bus_driver.R
import com.example.bus_driver.databinding.ActivityPyramidBinding
import com.google.android.flexbox.*

class PyramidActivity: AppCompatActivity() {
    private lateinit var binding: ActivityPyramidBinding
    private lateinit var adapter: PyramidAdapter
    private val cards = ArrayList<Card>()
    private val count = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPyramidBinding.inflate(layoutInflater)
        setContentView(binding.root)
        for (i in 1..count) {
            cards.add(Game.instance.drawCard())
        }
        adapter = PyramidAdapter(cards, this)
        binding.pyramidRecycler.adapter = adapter
        binding.pyramidRecycler.layoutManager = FlexboxLayoutManager(this).apply {
            flexWrap = FlexWrap.WRAP
            flexDirection = FlexDirection.ROW
            alignItems = AlignItems.STRETCH
            justifyContent = JustifyContent.CENTER
        }
        binding.nextButton.setOnClickListener {
            if (adapter.reachedLimit()) {
                startActivity(Intent(this, BusDriverActivity::class.java))
                finish()
            }
            val card = adapter.nextCard()
            adapter.notifyDataSetChanged()
            getResults(card)
        }
    }

    private fun getResults(card: Card) {
        val results = Game.instance.getPlayersSameRankCardCount(card.rank)
        Game.instance.removePlayersSameRankCards(card.rank)
        var text = results.filter { it.count > 0 }.joinToString(separator = "\n") {
            getString(R.string.distributes_parametrized, it.playerName, it.count)
        }
        if (text.isBlank()) {
            text = "-"
        }
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}