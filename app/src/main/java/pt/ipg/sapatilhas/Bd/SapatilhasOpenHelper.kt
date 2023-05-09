package pt.ipg.sapatilhas.Bd

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import pt.ipg.sapatilhas.TabelaMarca
import pt.ipg.sapatilhas.TabelaSapatilha


private const val VERSAO_BASE_DE_DADOS = 1

class SapatilhasOpenHelper(
    context: Context?
) : SQLiteOpenHelper(context, NOME_BASE_DE_DADOS, null, VERSAO_BASE_DE_DADOS) {
    override fun onCreate(db: SQLiteDatabase?) {
        requireNotNull(db)
        TabelaMarca(db).cria()
        TabelaSapatilha(db).cria()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    companion object {
        val NOME_BASE_DE_DADOS = "sapatilhas.db"
    }
}