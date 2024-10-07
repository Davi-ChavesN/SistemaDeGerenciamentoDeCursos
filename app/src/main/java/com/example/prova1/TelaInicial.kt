package com.example.prova1

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TelaInicial : AppCompatActivity() {

    lateinit var titulo: TextView
    lateinit var adicionar: Button
    lateinit var atualizar: Button
    lateinit var buscar: Button
    lateinit var relatorio: Button
    lateinit var log_relatorios: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_inicial)

        titulo = findViewById(R.id.tv_titulo)
        adicionar = findViewById(R.id.bt_tela_adicionar)
        atualizar = findViewById(R.id.bt_tela_atualizar)
        buscar = findViewById(R.id.bt_tela_buscar)
        relatorio = findViewById(R.id.bt_tela_relatorio)
        log_relatorios = findViewById(R.id.bt_tela_log_relatorio)

        var chamarTelaAdicionarCurso = Intent(applicationContext, TelaAdicionarCurso::class.java)
        var chamarTelaAtualizarDeletarCurso = Intent(applicationContext, TelaAtualizarDeletarCurso::class.java)
        var chamarTelaBuscarCurso = Intent(applicationContext, TelaBuscarCurso::class.java)
        var chamarTelaRelatorio = Intent(applicationContext, TelaRelatorio::class.java)
        var chamarTelaLogRelatorios = Intent(applicationContext, TelaLogRelatorios::class.java)

        val dataBase = Banco(applicationContext)
        val cursodao = CursoDAO(dataBase)

        adicionar.setOnClickListener {
            startActivity(chamarTelaAdicionarCurso)
        }

        atualizar.setOnClickListener {
            startActivity(chamarTelaAtualizarDeletarCurso)
        }

        buscar.setOnClickListener {
            startActivity(chamarTelaBuscarCurso)
        }

        relatorio.setOnClickListener {
            startActivity(chamarTelaRelatorio)
        }

        log_relatorios.setOnClickListener {
            startActivity(chamarTelaLogRelatorios)
        }
    }
}