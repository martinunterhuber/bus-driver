package at.unterhuber.bus_driver.ui

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

import at.unterhuber.bus_driver.databinding.ActivityGameBinding
import at.unterhuber.bus_driver.fragments.PlayerCardFragment
import at.unterhuber.bus_driver.fragments.ChoiceFragment
import at.unterhuber.bus_driver.choices.Choice
import at.unterhuber.bus_driver.fragments.ChoiceResultFragment
import at.unterhuber.bus_driver.game.Game
import at.unterhuber.bus_driver.game.Player
import at.unterhuber.bus_driver.util.ChoiceListener

class ChoiceActivity : AppCompatActivity(), ChoiceListener {
    private lateinit var binding: ActivityGameBinding
    private lateinit var game: Game
    private lateinit var choiceFragment: ChoiceFragment
    private lateinit var playerCardFragment: PlayerCardFragment
    private lateinit var choiceResultFragment: ChoiceResultFragment
    private val ROUND_CHOICES = listOf(
        listOf(Choice.TREE, Choice.NOT_TREE),
        listOf(Choice.ABOVE, Choice.EQUALS, Choice.BELOW),
        listOf(Choice.IN_BETWEEN, Choice.EQUALS, Choice.OUTSIDE),
        listOf(Choice.HAVE, Choice.NOT_HAVE)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initGame()
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    private fun initGame() {
        intent.getStringArrayListExtra("PLAYERS")!!.map {
            Player(it)
        }.apply {
            game = Game.instance
            game.init(this, intent.getIntExtra("ROUNDS", 4), intent.getIntExtra("PYRAMID_HEIGHT", 4))
        }
        game.shuffleDeck()

        playerCardFragment = PlayerCardFragment(game.getCurrentPlayer())
        supportFragmentManager
            .beginTransaction()
            .add(binding.cardsFragmentContainer.id, playerCardFragment)
            .commit()
        doRound()
    }


    private fun doRound() {
        binding.title.text = game.getCurrentPlayer().name
        choiceFragment = ChoiceFragment(ROUND_CHOICES[game.currentRound])
        supportFragmentManager
            .beginTransaction()
            .add(binding.fragmentContainerView.id, choiceFragment)
            .commit()
    }

    override fun choice(choice: Choice){
        choiceResultFragment = ChoiceResultFragment(game.isCorrectChoice(choice))
        supportFragmentManager
            .beginTransaction()
            .remove(choiceFragment)
            .add(binding.fragmentContainerView.id, choiceResultFragment)
            .commit()
        playerCardFragment.updateCards(game.getCurrentPlayer())
    }

    override fun next() {
        game.updateCurrentPlayer()
        if (game.isLastRound()) {
            startActivity(Intent(this, PyramidActivity::class.java))
            finish()
        } else {
            supportFragmentManager
                .beginTransaction()
                .remove(choiceResultFragment)
                .commit()
            playerCardFragment.updateCards(game.getCurrentPlayer())
            doRound()
        }
    }
}