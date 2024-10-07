package com.example.prova1

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TelaAtualizarDeletarCurso : AppCompatActivity() {

    lateinit var codigo: EditText
    lateinit var nome: EditText
    lateinit var numero_alunos: EditText
    lateinit var nota_mec: EditText
    lateinit var agrupador_area: RadioGroup
    lateinit var humanas: RadioButton
    lateinit var exatas: RadioButton
    lateinit var biologicas: RadioButton
    lateinit var lista_cursos: ListView
    lateinit var atualizar: Button
    lateinit var deletar: Button
    lateinit var voltar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_atualizar_deletar_curso)

        codigo = findViewById(R.id.et_codigo)
        nome = findViewById(R.id.et_nome)
        numero_alunos = findViewById(R.id.et_num_alunos)
        nota_mec = findViewById(R.id.et_nota_mec)
        agrupador_area = findViewById(R.id.rg_area)
        humanas = findViewById(R.id.rb_humanas)
        exatas = findViewById(R.id.rb_exatas)
        biologicas = findViewById(R.id.rb_biologicas)
        lista_cursos = findViewById(R.id.lv_cursos_atualizar)
        atualizar = findViewById(R.id.bt_atualizar)
        deletar = findViewById(R.id.bt_deletar)
        voltar = findViewById(R.id.bt_voltar_atualizar)

        val dataBase = Banco(applicationContext)
        val cursodao = CursoDAO(dataBase)

        mostrarCursos(cursodao)

        codigo.isEnabled = false

        voltar.setOnClickListener{
            finish()
        }

        atualizar.setOnClickListener {
            var area = ""
            if(humanas.isChecked){
                area = "Humanas"
            }else if(exatas.isChecked){
                area = "Exatas"
            }else if(biologicas.isChecked){
                area = "Biológicas"
            }
            val curso = Curso(codigo.text.toString().toInt(), nome.text.toString(), numero_alunos.text.toString().toInt(), nota_mec.text.toString().toFloat(), area)
            cursodao.update(curso)
            mostrarCursos(cursodao)
            limparCampos()
        }

        deletar.setOnClickListener {
            cursodao.delete(codigo.text.toString().toInt())
            mostrarCursos(cursodao)
            limparCampos()
        }

        lista_cursos.onItemClickListener = AdapterView.OnItemClickListener{
                parent, view, position, id ->
            var texto: String = parent.getItemAtPosition(position).toString()
            var dadosSeparados = texto.split(" - ").toTypedArray()
            codigo.setText(dadosSeparados[0])
            nome.setText(dadosSeparados[1])
            numero_alunos.setText(dadosSeparados[2])
            nota_mec.setText(dadosSeparados[3])
            if(dadosSeparados[4].equals("Humanas")){
                agrupador_area.check(humanas.id)
            }else if(dadosSeparados[4].equals("Exatas")){
                agrupador_area.check(exatas.id)
            }else if(dadosSeparados[4].equals("Biológicas")){
                agrupador_area.check(biologicas.id)
            }
        }
    }

    fun mostrarCursos(cursodao: CursoDAO){
        var listaCursos = cursodao.select()
        var adaptador = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, listaCursos)
        lista_cursos.adapter = adaptador
    }

    fun limparCampos() {
        codigo.text.clear()
        nome.text.clear()
        numero_alunos.text.clear()
        nota_mec.text.clear()
        agrupador_area.clearCheck()
    }
}