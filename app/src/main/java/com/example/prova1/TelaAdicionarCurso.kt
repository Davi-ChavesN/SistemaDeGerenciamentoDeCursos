package com.example.prova1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class TelaAdicionarCurso : AppCompatActivity() {

    lateinit var codigo: EditText
    lateinit var nome: EditText
    lateinit var numero_alunos: EditText
    lateinit var nota_mec: EditText
    lateinit var agrupador_area: RadioGroup
    lateinit var humanas: RadioButton
    lateinit var exatas: RadioButton
    lateinit var biologicas: RadioButton
    lateinit var adicionar: Button
    lateinit var voltar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_adicionar_curso)

        codigo = findViewById(R.id.et_codigo)
        nome = findViewById(R.id.et_nome)
        numero_alunos = findViewById(R.id.et_num_alunos)
        nota_mec = findViewById(R.id.et_nota_mec)
        agrupador_area = findViewById(R.id.rg_area)
        humanas = findViewById(R.id.rb_humanas)
        exatas = findViewById(R.id.rb_exatas)
        biologicas = findViewById(R.id.rb_biologicas)
        adicionar = findViewById(R.id.bt_adicionar)
        voltar = findViewById(R.id.bt_voltar_adicionar)

        val dataBase = Banco(applicationContext)
        val cursodao = CursoDAO(dataBase)

        voltar.setOnClickListener {
            finish()
        }

        adicionar.setOnClickListener{
            var area = ""
            if(humanas.isChecked){
                area = "Humanas"
            }else if(exatas.isChecked){
                area = "Exatas"
            }else if(biologicas.isChecked){
                area = "Biológicas"
            }
            val curso = Curso(codigo.text.toString().toInt(), nome.text.toString(), numero_alunos.text.toString().toInt(), nota_mec.text.toString().toFloat(), area)
            cursodao.insert(curso)
            limparCampos()
        }
    }

    fun limparCampos() {
        codigo.text.clear()
        nome.text.clear()
        numero_alunos.text.clear()
        nota_mec.text.clear()
        agrupador_area.clearCheck()
    }

}