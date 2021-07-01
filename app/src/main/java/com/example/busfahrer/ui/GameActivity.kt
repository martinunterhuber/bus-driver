package com.example.busfahrer.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import com.example.busfahrer.databinding.ActivityGameBinding
import com.example.busfahrer.fragments.CardFragment
import com.example.busfahrer.fragments.ChoiceFragment
import com.example.busfahrer.choices.Choice
import com.example.busfahrer.fragments.ChoiceResultFragment
import com.example.busfahrer.game.Game
import com.example.busfahrer.game.Player

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private lateinit var game: Game
    private lateinit var choiceFragment: ChoiceFragment
    private lateinit var cardFragment: CardFragment
    private lateinit var choiceResultFragment: ChoiceResultFragment
    private val ROUND_CHOICES = listOf(
        listOf(Choice.TREE, Choice.NOT_TREE),
        listOf(Choice.ABOVE, Choice.BELOW),
        listOf(Choice.IN_BETWEEN, Choice.OUTSIDE),
        listOf(Choice.HAVE, Choice.NOT_HAVE)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initGame()
    }

    private fun initGame() {
        intent.getStringArrayListExtra("PLAYERS")!!.map {
            Player(it)
        }.apply {
            game = Game(this)
        }

        game.shuffleDeck()
        doRound()
    }


    private fun doRound() {
        binding.title.text = game.getCurrentPlayer().name
        choiceFragment = ChoiceFragment(ROUND_CHOICES[game.currentRound][0], ROUND_CHOICES[game.currentRound][1])
        cardFragment = CardFragment(game.getCurrentPlayer())
        supportFragmentManager
            .beginTransaction()
            .add(binding.fragmentContainerView.id, choiceFragment)
            .add(binding.cardsFragmentContainer.id, cardFragment)
            .commit()
    }

    fun choice(choice: Choice){
        choiceResultFragment = ChoiceResultFragment(game.isCorrectChoice(choice))
        supportFragmentManager
            .beginTransaction()
            .remove(choiceFragment)
            .remove(cardFragment)
            .add(binding.fragmentContainerView.id, choiceResultFragment)
            .commit()
    }

    fun next() {
        game.updateCurrentPlayer()
        supportFragmentManager
            .beginTransaction()
            .remove(choiceResultFragment)
            .commit()
        doRound()
    }
}