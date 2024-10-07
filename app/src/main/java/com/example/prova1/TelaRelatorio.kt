package com.example.prova1

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.FileNotFoundException
import java.io.FileOutputStream

class TelaRelatorio : AppCompatActivity() {

    lateinit var qtd_alunos_relatorio: TextView
    lateinit var curso_mais_alunos_relatorio: TextView
    lateinit var et_salvar_arquivo: EditText
    lateinit var bt_salvar_arquivo: Button
    lateinit var voltar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_relatorio)

        qtd_alunos_relatorio = findViewById(R.id.tv_relatorio_qtd_alunos)
        curso_mais_alunos_relatorio = findViewById(R.id.tv_relatorio_curso_mais_alunos)
        et_salvar_arquivo = findViewById(R.id.et_salvar_arquivo)
        bt_salvar_arquivo = findViewById(R.id.bt_salvar_arquivo)
        voltar = findViewById(R.id.bt_voltar_relatorio)

        val dataBase = Banco(applicationContext)
        val cursodao = CursoDAO(dataBase)

        var listaCursos = cursodao.select()
        var curso_mais_alunos: String = "Sem cursos cadastrados"
        var qtd_aluno_curso: Int = 0
        var qtd_total_alunos: Int = 0

        for (curso in listaCursos){
            var dadosSeparados = curso.split(" - ").toTypedArray()
            qtd_total_alunos += dadosSeparados[2].toInt()
            if(dadosSeparados[2].toInt() > qtd_aluno_curso){
                curso_mais_alunos = dadosSeparados[1]
                qtd_aluno_curso = dadosSeparados[2].toInt()
            }
            //Log.i("teste", "Qtd Total de Alunos: ${qtd_total_alunos} - Curso com mais Alunos: ${curso_mais_alunos}")
        }

        voltar.setOnClickListener {
            finish()
        }

        curso_mais_alunos_relatorio.setText("Curso com mais Alunos: ${curso_mais_alunos}")
        qtd_alunos_relatorio.setText("Quantidade total de Alunos: ${qtd_total_alunos}")

        bt_salvar_arquivo.setOnClickListener{
            val file = et_salvar_arquivo.text.toString()
            var data = ""
            data += "Cursos:\n\n"
            for(curso in listaCursos){
                var dadosSeparados = curso.split(" - ").toTypedArray()
                data += "Nome: ${dadosSeparados[1]}\nCódigo: ${dadosSeparados[0]}" +
                        "\nQuantidade de Alunos: ${dadosSeparados[2]}\nNota MEC: ${dadosSeparados[3]}" +
                        "\nÁrea: ${dadosSeparados[4]}\n\n"
            }
            data += "=============================================\n"
            data += "Dados Gerais:\n\n"
            data += "Quantidade total de alunos: ${qtd_total_alunos}\nCurso com mais alunos: ${curso_mais_alunos}"
            val fileOutputStream: FileOutputStream

            try {
                fileOutputStream = openFileOutput(file, Context.MODE_PRIVATE)
                fileOutputStream.write(data.toByteArray())
            }catch (e: FileNotFoundException){
                e.printStackTrace()
            }catch (e: Exception){
                e.printStackTrace()
            }
            showToast("Arquivo salvo")
        }
    }

    fun Context.showToast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT){
        Toast.makeText(this, text, duration).show()
    }

}