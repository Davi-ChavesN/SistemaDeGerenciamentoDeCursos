package com.example.prova1

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class TelaBuscarCurso : AppCompatActivity() {

    lateinit var codigo_busca: EditText
    lateinit var view: LinearLayout
    lateinit var tv_nome_curso: TextView
    lateinit var tv_codigo_curso: TextView
    lateinit var tv_qtd_alunos: TextView
    lateinit var tv_nota_mec: TextView
    lateinit var tv_area: TextView
    lateinit var buscar: Button
    lateinit var voltar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_buscar_curso)

        codigo_busca = findViewById(R.id.et_busca_codigo)
        view = findViewById(R.id.ll_item)
        tv_nome_curso = findViewById(R.id.tv_nome_curso)
        tv_codigo_curso = findViewById(R.id.tv_codigo_curso)
        tv_qtd_alunos = findViewById(R.id.tv_qtd_alunos)
        tv_nota_mec = findViewById(R.id.tv_nota_mec)
        tv_area = findViewById(R.id.tv_area)
        buscar = findViewById(R.id.bt_buscar_curso)
        voltar = findViewById(R.id.bt_voltar_buscar)

        val dataBase = Banco(applicationContext)
        val cursodao = CursoDAO(dataBase)

        voltar.setOnClickListener {
            finish()
        }

        buscar.setOnClickListener{
            var listaCursos = cursodao.selectComWhere(codigo_busca.text.toString().toLong())
            var dadosSeparados = listaCursos[0].split(" - ")
            tv_codigo_curso.setText("Código: ${dadosSeparados[0]}")
            tv_nome_curso.setText("${dadosSeparados[1]}")
            tv_qtd_alunos.setText("N de Alunos: ${dadosSeparados[2]}")
            tv_nota_mec.setText("Nota MEC: ${dadosSeparados[3]}")
            tv_area.setText("Área: ${dadosSeparados[4]}")
            limparCampos()
        }
    }

    fun limparCampos() {
        codigo_busca.text.clear()
    }


}