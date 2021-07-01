package com.example.busfahrer.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.example.busfahrer.databinding.ActivityGameBinding
import com.example.busfahrer.game.Game
import com.example.busfahrer.game.Player

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (intent.getSerializableExtra("PLAYERS") as ArrayList<Player>).apply {
            game = Game(this)
        }

        game.shuffleDeck()
    }
}