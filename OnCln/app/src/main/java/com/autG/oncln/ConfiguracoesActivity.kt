package com.autG.oncln

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.autG.oncln.databinding.ActivityConfiguracoesBinding
import com.autG.oncln.databinding.ActivityPopUpConfiguracoesBinding

class ConfiguracoesActivity : AppCompatActivity() {

    private lateinit var dialogBuilder: AlertDialog.Builder
    private lateinit var dialogo: AlertDialog
    private lateinit var binding: ActivityConfiguracoesBinding
    private lateinit var bindingJanela: ActivityPopUpConfiguracoesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfiguracoesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botaoAlterarSenha.setOnClickListener {
            janelaDeAlteracaoDeSenha()
        }

        binding.include.textTitulo.text = "Configurações"
    }

    fun janelaDeAlteracaoDeSenha() {
        dialogBuilder = AlertDialog.Builder(this)
        val chamandoJanela: View =
            layoutInflater.inflate(R.layout.activity_pop_up_configuracoes, null)

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
                Toast.makeText(this, "Alterar", Toast.LENGTH_LONG).show()
            }
            acao == "cancelar" -> {
                dialogo.dismiss()
                Toast.makeText(this, "Cancelar", Toast.LENGTH_LONG).show()
            }
        }
    }

}