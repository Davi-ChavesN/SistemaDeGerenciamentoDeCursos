package com.example.prova1

class Curso(codigo: Int, nome: String, qtd_alunos: Int, nota_mec: Float, area: String)
{
    var codigo: Int
    var nome: String
    var qtd_alunos: Int
    var nota_mec: Float
    var area: String
    init {
        this.codigo = codigo
        this.nome = nome
        this.qtd_alunos = qtd_alunos
        this.nota_mec = nota_mec
        this.area = area
    }
}