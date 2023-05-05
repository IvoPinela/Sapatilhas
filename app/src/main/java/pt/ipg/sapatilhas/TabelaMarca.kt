package pt.ipg.sapatilhas
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

private const val NOME_TABELA = "marca"

class TabelaMarca(db:SQLiteDatabase):TabelaBD(db,"marca"){
    override fun cria() {
       db.execSQL("CREATE TABLE $NOME_TABELA ($CHAVE_TABELA,Nome TEXT NOT NULL,Sede TEXT NOT NULL)")
    }
}