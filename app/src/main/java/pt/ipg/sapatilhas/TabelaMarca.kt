package pt.ipg.sapatilhas
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns


class TabelaMarca(db:SQLiteDatabase):TabelaBD(db,"marca"){
    override fun cria() {
       db.execSQL("CREATE TABLE $NOME_TABELA ($CHAVE_TABELA,Nome TEXT NOT NULL,Sede TEXT NOT NULL)")
    }
    companion object{
        const val NOME_TABELA = "marca"
    }
}