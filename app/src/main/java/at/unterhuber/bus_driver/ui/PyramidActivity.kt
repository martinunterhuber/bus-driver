package at.unterhuber.bus_driver.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import at.unterhuber.bus_driver.adapters.PyramidAdapter
import at.unterhuber.bus_driver.cards.Card
import at.unterhuber.bus_driver.game.Game
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
                // TODO: start next activity
                finish()
            }
            val card = adapter.nextCard()
            adapter.notifyDataSetChanged()
            getResults(card)
        }
    }

    private fun getResults(card: Card) {
        val results = Game.instance.getPlayersSameRankCardCount(card.rank)
        val text = results.filter { it.count > 0 }.joinToString(separator = "\n") { "${it.playerName} distributes ${it.count} sips!" }
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}