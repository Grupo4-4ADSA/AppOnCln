package com.autG.oncln

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.autG.oncln.databinding.ActivityEquipmentConsumptionBinding

class EquipmentConsumptionActivity : Fragment() {
    private lateinit var binding: ActivityEquipmentConsumptionBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityEquipmentConsumptionBinding.inflate(inflater, container, false)

        binding.includeUnderlinedText.textTitulo.text =
            getText(R.string.text_example_air_conditioning)
        binding.includeUnderlinedText.textTitulo.text =
            getText(R.string.text_example_air_conditioning)
        binding.includeOneText.textTitulo.text = getText(R.string.text_example_room)
        binding.includeTwoText.textTitulo.text = getText(R.string.text_example_floor)

        return binding.root
    }
}