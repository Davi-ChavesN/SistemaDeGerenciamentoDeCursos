package com.example.prova1

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Banco(context: Context): SQLiteOpenHelper(context, "DataBase", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val nomeTabela = "cursos"
        val codigo = "codigo"
        val nome = "nome"
        val num_alunos = "num_alunos"
        val nota_mec = "nota_mec"
        val area = "area"
        val SQL_criacao =
            "CREATE TABLE ${nomeTabela} ("+
                    "${codigo} INTEGER PRIMARY KEY,"+
                    "${nome} TEXT,"+
                    "${num_alunos} INTEGER,"+
                    "${nota_mec} FLOAT,"+
                    "${area} TEXT)"
        db.execSQL(SQL_criacao)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}