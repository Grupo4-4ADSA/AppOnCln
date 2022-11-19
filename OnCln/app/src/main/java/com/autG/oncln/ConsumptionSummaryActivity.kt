package com.autG.oncln

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.autG.oncln.databinding.ActivityConsumptionSummaryBinding

class ConsumptionSummaryActivity : Fragment() {
    private lateinit var binding: ActivityConsumptionSummaryBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityConsumptionSummaryBinding.inflate(inflater,container,false)

        binding.includeText.textTitulo.text = getText(R.string.title_consumption_summary)

        return binding.root
    }
}