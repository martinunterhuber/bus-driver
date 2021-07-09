package at.unterhuber.bus_driver.util

import at.unterhuber.bus_driver.choices.Choice

interface ChoiceListener {
    fun choice(choice: Choice)
}