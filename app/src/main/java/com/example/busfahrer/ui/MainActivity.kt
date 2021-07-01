package com.example.busfahrer.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.busfahrer.databinding.ActivityMainBinding
import com.example.busfahrer.game.Game
import com.example.busfahrer.game.Player

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener{
            val players = ArrayList<Player>()
            players.add(Player(binding.player1.text.toString()))
            players.add(Player(binding.player2.text.toString()))
            players.add(Player(binding.player3.text.toString()))
            players.add(Player(binding.player4.text.toString()))
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("PLAYERS", players)
            startActivity(intent)
        }
    }
}