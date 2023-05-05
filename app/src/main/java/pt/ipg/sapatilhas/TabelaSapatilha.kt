package pt.ipg.sapatilhas
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns



class TabelaSapatilha(db: SQLiteDatabase):TabelaBD(db,NOME_TABELA) {
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME_TABELA ($CHAVE_TABELA,Modelo TEXT NOT NULL,Cor TEXT NOT NULL,Tamanho Integer NOT NULL,SerialNumber TEXT NOT NULL,id_marca INTEGER NOT NULL),FOREIGN KEY(id_marca) REFERENCES ${TabelaMarca.NOME_TABELA}(${BaseColumns._ID})")
    }
    companion object{
        const val NOME_TABELA = "sapatilha"
    }
}
