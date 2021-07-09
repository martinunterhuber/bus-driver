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
    private var depth = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPyramidBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // TODO: make this more generic
        if (Game.instance.numberOfCardsLeft() < 14) {
            depth = 3
        }
        for (i in 1..getCountFromDepth()) {
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
            } else {
                val card = adapter.nextCard()
                adapter.notifyDataSetChanged()
                getResults(card)
            }
        }
    }

    private fun getCountFromDepth(): Int {
        var sum = 0
        for (i in 1..depth) {
            sum += i
        }
        return sum
    }

    private fun getResults(card: Card) {
        val results = Game.instance.getPlayersSameRankCardCount(card.rank)
        Game.instance.removePlayersSameRankCards(card.rank)
        val text = results.filter { it.count > 0 }.joinToString(separator = "\n") {
            getString(R.string.distributes_parametrized, it.playerName, it.count * getHeight(adapter.displayedCards))
        }
        binding.resultText.text = text
    }

    private fun getHeight(displayedCards: Int): Int {
        var temp = displayedCards
        var height = 0
        var i = depth
        while (temp > 0) {
            temp -= i
            height++
            i--
        }
        return height
    }
}