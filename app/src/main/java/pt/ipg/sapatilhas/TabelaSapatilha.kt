package pt.ipg.sapatilhas
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns



class TabelaSapatilha(db: SQLiteDatabase):TabelaBD(db,NOME_TABELA) {
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME_TABELA ($CHAVE_TABELA,$CAMPO_MODELO TEXT NOT NULL,$CAMPO_COR TEXT NOT NULL,$CAMPO_TAMANHO Integer NOT NULL,$CAMPO_SERIALNUMBER TEXT NOT NULL,$CAMPO_IDMARCA INTEGER NOT NULL,FOREIGN KEY($CAMPO_IDMARCA) REFERENCES ${TabelaMarca.NOME_TABELA}(${BaseColumns._ID}) ON DELETE RESTRICT)")
    }
    companion object{
        const val NOME_TABELA = "sapatilha"
        const val CAMPO_MODELO = "Modelo"
        const val CAMPO_COR = "Cor"
        const val CAMPO_TAMANHO = "Tamanho"
        const val CAMPO_SERIALNUMBER = "SerialNumber"
        const val CAMPO_IDMARCA = "id_marca"
        val CAMPOS= arrayOf(BaseColumns._ID,CAMPO_MODELO,CAMPO_COR,CAMPO_TAMANHO,CAMPO_SERIALNUMBER,CAMPO_IDMARCA)

    }
}
