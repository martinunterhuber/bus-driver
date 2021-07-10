package at.unterhuber.bus_driver.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Spinner

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import at.unterhuber.bus_driver.adapters.PlayerAdapter

import com.example.bus_driver.databinding.ActivityMainBinding

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
    }

    private fun startGame() {
        var players = (binding.players.adapter!! as PlayerAdapter).players
        players = players.filter { it.isNotBlank() } as ArrayList<String>
        if (players.isEmpty()) {
            players.add("")
        }
        val intent = Intent(this, ChoiceActivity::class.java)
        intent.putExtra("PLAYERS", players)
        startActivity(intent)
    }
}