package com.example.prova1

import android.content.ContentValues
import android.util.Log

class CursoDAO(banco: Banco) {
    var banco: Banco
    init {
        this.banco = banco
    }

    fun insert(curso: Curso) {
        val db_insert = this.banco.writableDatabase
        var cv_valores = ContentValues().apply {
            put("codigo", curso.codigo)
            put("nome", curso.nome)
            put("num_alunos", curso.qtd_alunos)
            put("nota_mec", curso.nota_mec)
            put("area", curso.area)
        }
        val confirmaInsert = db_insert?.insert("cursos", null, cv_valores)
        Log.i("Teste", "Inserção: ${confirmaInsert}")
    }

    fun select(): ArrayList<String> {
        var listaCursos = ArrayList<String>()
        val db_read = this.banco.readableDatabase
        var cursor = db_read.rawQuery("SELECT * FROM cursos", null)

        with(cursor) {
            while (moveToNext()) {
                val codigo = getLong(getColumnIndexOrThrow("codigo"))
                val nome = getString(getColumnIndexOrThrow("nome"))
                val qtd_alunos = getInt(getColumnIndexOrThrow("num_alunos"))
                val nota_mec = getFloat(getColumnIndexOrThrow("nota_mec"))
                val area = getString(getColumnIndexOrThrow("area"))
                listaCursos.add("${codigo} - ${nome} - ${qtd_alunos} - ${nota_mec} - ${area}")
                //Log.i("Teste", "Codigo: ${codigo} - Nome: ${nome} - Quantidade de Alunos: ${qtd_alunos} - Nota MEC: ${nota_mec} - Area: ${area}")
            }
        }
        cursor.close()
        return (listaCursos)
    }

    fun selectComWhere(codigo: Long): ArrayList<String> {
        var listaCursos = ArrayList<String>()
        val db_read = this.banco.readableDatabase
        var cursor = db_read.rawQuery("SELECT * FROM cursos WHERE codigo = ${codigo}", null)

        with(cursor) {
            while (moveToNext()) {
                val codigo = getLong(getColumnIndexOrThrow("codigo"))
                val nome = getString(getColumnIndexOrThrow("nome"))
                val qtd_alunos = getInt(getColumnIndexOrThrow("num_alunos"))
                val nota_mec = getFloat(getColumnIndexOrThrow("nota_mec"))
                val area = getString(getColumnIndexOrThrow("area"))
                listaCursos.add("${codigo} - ${nome} - ${qtd_alunos} - ${nota_mec} - ${area}")
                //Log.i("Teste", "Codigo: ${codigo} - Nome: ${nome} - Quantidade de Alunos: ${qtd_alunos} - Nota MEC: ${nota_mec} - Area: ${area}")
            }
        }
        cursor.close()
        return (listaCursos)
    }

    fun update(curso: Curso) {
        val db_update = this.banco.writableDatabase
        var cv_valores = ContentValues().apply {
            put("nome", curso.nome)
            put("num_alunos", curso.qtd_alunos)
            put("nota_mec", curso.nota_mec)
            put("area", curso.area)
        }
        val condicao = "codigo = ${curso.codigo}"
        val confirmaUpdate = db_update.update("cursos", cv_valores, condicao, null)
    }

    fun delete(codigo: Int) {
        val db_delete = this.banco.writableDatabase
        val condicao = "codigo = ${codigo}"
        db_delete.delete("cursos", condicao, null)
    }
}