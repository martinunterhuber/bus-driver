package at.unterhuber.bus_driver.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.example.bus_driver.R
import com.example.bus_driver.databinding.FragmentChoiceResultBinding
import at.unterhuber.bus_driver.ui.ChoiceActivity
import at.unterhuber.bus_driver.util.ChoiceListener

class ChoiceResultFragment(private val result: Boolean): Fragment() {
    private lateinit var binding: FragmentChoiceResultBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChoiceResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.resultImage.setOnClickListener{
            (requireActivity() as ChoiceListener).next()
        }
        binding.resultImage.setImageDrawable(AppCompatResources.getDrawable(requireContext(), if (result) R.drawable.ic_baseline_check_24 else R.drawable.ic_baseline_close_24))
    }
}