package pt.ipg.sapatilhas.Bd

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

private const val NOME_BASE_DE_DADOS = "sapatilhas.db"
private const val VERSAO_BASE_DE_DADOS = 1

class SapatilhasOpenHelper(
    context: Context?
) : SQLiteOpenHelper(context, NOME_BASE_DE_DADOS, null, VERSAO_BASE_DE_DADOS) {
    override fun onCreate(p0: SQLiteDatabase?) {
        TODO("Not yet implemented")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}