package com.autG.oncln

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.autG.oncln.databinding.ActivityHelpCenterBinding


class HelpCenterActivity:Fragment() {

    private lateinit var binding: ActivityHelpCenterBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityHelpCenterBinding.inflate(inflater,container,false)

        binding.includeText.textTitulo.text = getText(R.string.title_help_center)

        binding.btnBlue.botaoAzul.text = getText(R.string.txt_btn_support)

        TransitionManager.beginDelayedTransition(container, Fade())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBlue.botaoAzul.setOnClickListener {
            sendMessage("https://api.whatsapp.com/send?phone=5511911460587&text=Boa%20pizza%20quero%20uma%20noite")
        }
    }

    fun sendMessage(message:String){

        val intent = Intent(Intent.ACTION_VIEW)

        intent.setPackage("com.whatsapp")

        intent.data = Uri.parse(message)

        if (intent.resolveActivity(requireActivity().packageManager) == null) {
            Toast.makeText(requireContext(),
                "Please install whatsapp first.",
                Toast.LENGTH_SHORT).show()
            return
        }

        startActivity(intent)
    }

}