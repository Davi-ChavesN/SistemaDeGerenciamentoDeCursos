package com.example.prova1

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader

class TelaLogRelatorios : AppCompatActivity() {

    lateinit var nome_arquivo: EditText
    lateinit var conteudo_arquivo: TextView
    lateinit var procurar: Button
    lateinit var voltar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tela_log_relatorios)

        nome_arquivo = findViewById(R.id.et_nome_arquivo)
        conteudo_arquivo = findViewById(R.id.tv_conteudo_arquivo)
        procurar = findViewById(R.id.bt_procurar)
        voltar = findViewById(R.id.bt_voltar_log)

        voltar.setOnClickListener{
            finish()
        }

        procurar.setOnClickListener{
            val file = nome_arquivo.text.toString()

            if(file.toString()!=null && file.trim()!=""){
                var fileInputStream: FileInputStream? = null
                fileInputStream = openFileInput(file)

                var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
                val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)

                val stringBuilder: StringBuilder = StringBuilder()
                var text: String? = null

                while ({text = bufferedReader.readLine(); text}() != null){
                    stringBuilder.append(text)
                    stringBuilder.append("\n")
                }

                conteudo_arquivo.setText(stringBuilder)
            }else{
                showToast("Nome do arquivo não pode estar vazio")
            }
        }

    }

    fun Context.showToast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT){
        Toast.makeText(this, text, duration).show()
    }

}