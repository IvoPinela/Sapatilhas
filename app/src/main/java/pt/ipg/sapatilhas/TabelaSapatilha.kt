package pt.ipg.sapatilhas
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.provider.BaseColumns



class TabelaSapatilha(db: SQLiteDatabase):TabelaBD(db,NOME_TABELA) {
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME_TABELA ($CHAVE_TABELA,$CAMPO_MODELO TEXT NOT NULL,$CAMPO_COR TEXT NOT NULL,$CAMPO_TAMANHO Integer NOT NULL,$CAMPO_SERIALNUMBER TEXT NOT NULL,$CAMPO_IDMARCA INTEGER NOT NULL,FOREIGN KEY($CAMPO_IDMARCA) REFERENCES ${TabelaMarca.NOME_TABELA}(${BaseColumns._ID}) ON DELETE RESTRICT)")
    }

    override  fun  consulta(
        colunas: Array<String>,
        selecao: String?,
        argsSelecao: Array<String>?,
        groupby: String?,
        having: String?,
        orderby: String?
    ): Cursor {
        val sql=SQLiteQueryBuilder()
        sql.tables="${TabelaSapatilha.NOME_TABELA} INNER JOIN ${TabelaMarca.NOME_TABELA} ON $CAMPO_IDMARCA=${TabelaMarca.CAMPO_ID2}"
        return sql.query(db, colunas, selecao, argsSelecao, groupby, having, orderby)
    }
    companion object{
        const val NOME_TABELA = "sapatilha"

        const val CAMPO_ID = "$NOME_TABELA.${BaseColumns._ID}"
        const val CAMPO_MODELO = "Modelo"
        const val CAMPO_COR = "Cor"
        const val CAMPO_TAMANHO = "Tamanho"
        const val CAMPO_SERIALNUMBER = "SerialNumber"

        const val CAMPO_IDMARCA = "id_marca"
        const val CAMPO_SEDE= TabelaMarca.Campo_Sede
        const val CAMPO_NOME= TabelaMarca.Campo_Nome

        //select   sapatila._ID      scricao from inner join categoria
        val CAMPOS= arrayOf(CAMPO_ID,CAMPO_MODELO,CAMPO_COR,CAMPO_TAMANHO,CAMPO_SERIALNUMBER,CAMPO_NOME,CAMPO_SEDE,CAMPO_IDMARCA)

    }


}