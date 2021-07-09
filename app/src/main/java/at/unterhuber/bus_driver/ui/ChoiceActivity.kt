package at.unterhuber.bus_driver.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.example.bus_driver.databinding.ActivityGameBinding
import at.unterhuber.bus_driver.fragments.CardFragment
import at.unterhuber.bus_driver.fragments.ChoiceFragment
import at.unterhuber.bus_driver.choices.Choice
import at.unterhuber.bus_driver.fragments.ChoiceResultFragment
import at.unterhuber.bus_driver.game.Game
import at.unterhuber.bus_driver.game.Player

class ChoiceActivity : AppCompatActivity() {
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
            game = Game.instance
            game.init(this)
        }
        game.shuffleDeck()

        startActivity(Intent(this, PyramidActivity::class.java))
        finish()

//        cardFragment = CardFragment(game.getCurrentPlayer())
//        supportFragmentManager
//            .beginTransaction()
//            .add(binding.cardsFragmentContainer.id, cardFragment)
//            .commit()
//        doRound()
    }


    private fun doRound() {
        binding.title.text = game.getCurrentPlayer().name
        choiceFragment = ChoiceFragment(ROUND_CHOICES[game.currentRound][0], ROUND_CHOICES[game.currentRound][1])
        supportFragmentManager
            .beginTransaction()
            .add(binding.fragmentContainerView.id, choiceFragment)
            .commit()
    }

    fun choice(choice: Choice){
        choiceResultFragment = ChoiceResultFragment(game.isCorrectChoice(choice))
        supportFragmentManager
            .beginTransaction()
            .remove(choiceFragment)
            .add(binding.fragmentContainerView.id, choiceResultFragment)
            .commit()
        cardFragment.updateCards(game.getCurrentPlayer())
    }

    fun next() {
        game.updateCurrentPlayer()
        supportFragmentManager
            .beginTransaction()
            .remove(choiceResultFragment)
            .commit()
        cardFragment.updateCards(game.getCurrentPlayer())
        if (game.isLastRound()) {
            startActivity(Intent(this, PyramidActivity::class.java))
            finish()
        } else {
            doRound()
        }
    }
}