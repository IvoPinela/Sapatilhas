package pt.ipg.sapatilhas
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns


class TabelaMarca(db:SQLiteDatabase):TabelaBD(db,"marca"){
    override fun cria() {
       db.execSQL("CREATE TABLE $NOME_TABELA ($CHAVE_TABELA,$Campo_Nome TEXT NOT NULL,$Campo_Sede TEXT NOT NULL)")
    }
    companion object{
        const val NOME_TABELA = "marca"
        const val Campo_Nome = "nome"
        const val Campo_Sede = "sede"
        const val CAMPO_ID2 = "${NOME_TABELA}.${BaseColumns._ID}"

        val CAMPOS= arrayOf(BaseColumns._ID, Campo_Nome, Campo_Sede)


    }
}