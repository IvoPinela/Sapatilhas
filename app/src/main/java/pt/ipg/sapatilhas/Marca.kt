package pt.ipg.sapatilhas
import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns


data class  Marca (
    var nome: String,
    var sede:String,
    var id: Long=-1):java.io.Serializable {

        fun toContentValues(): ContentValues {
            val valores= ContentValues()

            valores.put(TabelaMarca.Campo_Nome, nome)
            valores.put(TabelaMarca.Campo_Sede, sede)
            return  valores
        }

companion object {
    fun fromCursor(cursor:Cursor): Marca{

        val posId=cursor.getColumnIndex(BaseColumns._ID)
        val posNome=cursor.getColumnIndex(TabelaMarca.Campo_Nome)
        val posSede=cursor.getColumnIndex(TabelaMarca.Campo_Sede)

        val id= cursor.getLong(posId)
        val nome=cursor.getString(posNome)
        val sede=cursor.getString(posSede)

        return Marca(nome,sede,id)
        }
    }
}