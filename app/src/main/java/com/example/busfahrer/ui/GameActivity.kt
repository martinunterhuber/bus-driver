package com.example.busfahrer.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.busfahrer.cards.Card
import com.example.busfahrer.cards.Rank
import com.example.busfahrer.cards.Suite

import com.example.busfahrer.databinding.ActivityGameBinding
import com.example.busfahrer.fragments.CardFragment
import com.example.busfahrer.fragments.ChoiceFragment
import com.example.busfahrer.game.Game
import com.example.busfahrer.game.Player

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initGame()
        firstRound()
    }

    private fun firstRound() {
        binding.title.text = game.getCurrentPlayer().name
        supportFragmentManager
            .beginTransaction()
            .add(binding.fragmentContainerView.id, ChoiceFragment())
            .add(binding.cardsFragmentContainer.id, CardFragment(game.getCurrentPlayer()))
            .commit()
    }

    private fun initGame() {
        intent.getStringArrayListExtra("PLAYERS")!!.map {
            Player(it)
        }.apply {
            game = Game(this)
        }

        game.shuffleDeck()
    }
}