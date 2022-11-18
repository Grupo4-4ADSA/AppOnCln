package com.autG.oncln

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.autG.oncln.databinding.ActivityConfiguracoesBinding
import com.autG.oncln.databinding.ActivityConfiguracoesPopUpBinding

class ConfiguracoesActivity : Fragment() {

    private lateinit var dialogBuilder: AlertDialog.Builder
    private lateinit var dialogo: AlertDialog
    private lateinit var binding: ActivityConfiguracoesBinding
    private lateinit var bindingJanela:ActivityConfiguracoesPopUpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityConfiguracoesBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.botaoAlterarSenha.setOnClickListener {
            janelaDeAlteracaoDeSenha()
        }

        binding.includeText.textTitulo.text = getText(R.string.title_settings)
    }

    fun janelaDeAlteracaoDeSenha() {
        dialogBuilder = AlertDialog.Builder(requireContext())
        val chamandoJanela: View =
            layoutInflater.inflate(R.layout.activity_configuracoes_pop_up, null)

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