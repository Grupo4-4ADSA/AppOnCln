package com.autG.oncln

import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.autG.oncln.databinding.ActivitySettingsBinding
import com.autG.oncln.databinding.ActivitySettingsPopUpBinding
import com.squareup.picasso.Picasso

class SettingsActivity : Fragment() {

    private lateinit var dialogBuilder: AlertDialog.Builder
    private lateinit var dialogo: AlertDialog
    private lateinit var binding: ActivitySettingsBinding
    private lateinit var bindingJanela:ActivitySettingsPopUpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivitySettingsBinding.inflate(inflater, container,false)

        Picasso.get().load("http://servidordb.ddns.net:9651/profile/SteveProfile.png").into(binding.fotoGestor)
        binding.includeText.textTitulo.text = getText(R.string.title_settings)
        TransitionManager.beginDelayedTransition(container, Fade())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.botaoAlterarSenha.setOnClickListener {
            janelaDeAlteracaoDeSenha()
        }


    }

    fun janelaDeAlteracaoDeSenha() {
        dialogBuilder = AlertDialog.Builder(requireContext())
        val chamandoJanela: View =
            layoutInflater.inflate(R.layout.activity_settings_pop_up, null)

        var campoSenhaAntiga: EditText = chamandoJanela.findViewById(R.id.edit_senha_antiga)
        var campoNovaSenha: EditText = chamandoJanela.findViewById(R.id.edit_nova_senha)
        var campoNovaSenhaConfirmacao: EditText =
            chamandoJanela.findViewById(R.id.edit_nova_senha_confirmacao)
        val botaoSalvarAlteracao: Button = chamandoJanela.findViewById(R.id.botao_salvar_alteracao)
        val botaoCancelarAlteracao: Button =
            chamandoJanela.findViewById(R.id.botao_salvar_alteracao)

        dialogBuilder.setView(chamandoJanela)
        dialogo = dialogBuilder.create()
        dialogo.show()

        botaoSalvarAlteracao.setOnClickListener {
            acaoBotao("alterar")
        }
        botaoCancelarAlteracao.setOnClickListener {
            acaoBotao("cancelar")
        }
    }

    fun acaoBotao(acao: String) {
        when {
            acao == "alterar" -> {
                Toast.makeText(requireContext(), "Alterar", Toast.LENGTH_LONG).show()
            }
            acao == "cancelar" -> {
                dialogo.dismiss()
                Toast.makeText(requireContext(), "Cancelar", Toast.LENGTH_LONG).show()
            }
        }
    }

}