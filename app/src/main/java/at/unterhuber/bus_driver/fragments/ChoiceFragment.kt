package at.unterhuber.bus_driver.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.example.bus_driver.databinding.FragmentChoiceBinding
import at.unterhuber.bus_driver.choices.Choice
import at.unterhuber.bus_driver.ui.ChoiceActivity
import at.unterhuber.bus_driver.util.ChoiceListener
import com.example.bus_driver.databinding.ChoiceViewBinding

class ChoiceFragment(private val choices: List<Choice>) : Fragment() {
    private lateinit var binding: FragmentChoiceBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChoiceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setChoice(binding.choice1, choices[0])
        setChoice(binding.choice2, choices[1])
        if (choices.size > 2) {
            setChoice(binding.choice3, choices[2])
            binding.choice3.root.visibility = View.VISIBLE
        } else {
            binding.choice3.root.visibility = View.GONE
        }
    }

    private fun setChoice(choiceBinding: ChoiceViewBinding, choice: Choice) {
        choiceBinding.box.setOnClickListener{
            (requireActivity() as ChoiceListener).choice(choice)
        }
        choiceBinding.symbolBox.setImageDrawable(AppCompatResources.getDrawable(requireContext(), choice.drawable))
        choiceBinding.textBox.setText(choice.text)
    }
}