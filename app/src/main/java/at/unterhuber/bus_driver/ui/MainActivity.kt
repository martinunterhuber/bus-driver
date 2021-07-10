package at.unterhuber.bus_driver.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import at.unterhuber.bus_driver.adapters.PlayerAdapter
import com.example.bus_driver.R
import com.example.bus_driver.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PlayerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = PlayerAdapter(this)
        binding.players.adapter = adapter
        binding.players.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.addPlayer.setOnClickListener{adapter.addPlayer()}
        binding.removePlayer.setOnClickListener{adapter.removePlayer()}
        binding.startButton.setOnClickListener{startGame()}

        binding.rounds.adapter = ArrayAdapter(this, R.layout.item_int_spinner, (1..4).toList())
        binding.rounds.setSelection(3)
        binding.pyramidHeight.adapter = ArrayAdapter(this, R.layout.item_int_spinner, (1..5).toList())
        binding.pyramidHeight.setSelection(3)
    }

    private fun startGame() {
        val rounds = binding.rounds.selectedItem.toString().toInt()
        val height = binding.pyramidHeight.selectedItem.toString().toInt()
        val players = filterPlayers()
        if (hasEnoughCards(players.size)) {
            val intent = Intent(this, ChoiceActivity::class.java)
            intent.putExtra("PLAYERS", players)
            intent.putExtra("ROUNDS", rounds)
            intent.putExtra("PYRAMID_HEIGHT", height)
            startActivity(intent)
        } else {
            Snackbar.make(binding.root, getString(R.string.not_enough_cards), Snackbar.LENGTH_LONG).show()
        }
    }

    private fun hasEnoughCards(playerCount: Int): Boolean {
        val rounds = binding.rounds.selectedItem.toString().toInt()
        val height = binding.pyramidHeight.selectedItem.toString().toInt()
        val cardsRequired = playerCount * rounds + height * (height + 1) / 2
        return cardsRequired <= 36
    }

    private fun filterPlayers(): ArrayList<String> {
        var players = (binding.players.adapter!! as PlayerAdapter).players
        players = players.filter { it.isNotBlank() } as ArrayList<String>
        if (players.isEmpty()) {
            players.add("")
        }
        return players
    }
}