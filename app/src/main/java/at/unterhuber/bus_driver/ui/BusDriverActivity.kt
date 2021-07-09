package at.unterhuber.bus_driver.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import at.unterhuber.bus_driver.cards.Card
import at.unterhuber.bus_driver.choices.Choice
import at.unterhuber.bus_driver.fragments.CardFragment
import at.unterhuber.bus_driver.fragments.ChoiceFragment
import at.unterhuber.bus_driver.fragments.ChoiceResultFragment
import at.unterhuber.bus_driver.game.Game
import at.unterhuber.bus_driver.game.Player
import at.unterhuber.bus_driver.util.ChoiceListener
import com.example.bus_driver.databinding.ActivityGameBinding

class BusDriverActivity: AppCompatActivity(), ChoiceListener {
    private lateinit var binding: ActivityGameBinding
    private lateinit var game: Game
    private lateinit var cardFragment: CardFragment
    private lateinit var choiceResultFragment: ChoiceResultFragment
    private lateinit var player: Player

    private val choiceFragment = ChoiceFragment(Choice.BELOW, Choice.ABOVE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        game = Game.instance
        player = game.getLosingPlayer()
        game.initBusDriver()

        binding.title.text = player.name

        cardFragment = CardFragment(game.busDriverCards)
        supportFragmentManager
            .beginTransaction()
            .add(binding.cardsFragmentContainer.id, cardFragment)
            .add(binding.fragmentContainerView.id, choiceFragment)
            .commit()
    }

    private fun doRound() {
        supportFragmentManager
            .beginTransaction()
            .add(binding.fragmentContainerView.id, choiceFragment)
            .commit()
    }

    override fun choice(choice: Choice){
        choiceResultFragment = ChoiceResultFragment(game.busDriverChoice(choice))
        cardFragment.cardHasChanged()

        supportFragmentManager
            .beginTransaction()
            .remove(choiceFragment)
            .add(binding.fragmentContainerView.id, choiceResultFragment)
            .commit()
    }

    fun next() {
        supportFragmentManager
            .beginTransaction()
            .remove(choiceResultFragment)
            .commit()
        if (game.busDriverHasFinished()) {
            finish()
        } else {
            doRound()
        }
    }
}