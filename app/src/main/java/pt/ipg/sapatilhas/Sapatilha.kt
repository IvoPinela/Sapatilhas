package pt.ipg.sapatilhas
import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Sapatilha(

    var Modelo:String,
    var Cor:String,
    var Tamanho:Int,
    var SerialNumber:String,
    var idMarca: Long,
    var id: Long=-1) {

    fun toContentValues(): ContentValues {
        val valores=ContentValues()

        valores.put(TabelaSapatilha.CAMPO_MODELO, Modelo)
        valores.put(TabelaSapatilha.CAMPO_COR, Cor)
        valores.put(TabelaSapatilha.CAMPO_TAMANHO, Tamanho)
        valores.put(TabelaSapatilha.CAMPO_SERIALNUMBER, SerialNumber)
        valores.put(TabelaSapatilha.CAMPO_IDMARCA,idMarca)
        return  valores
    }
    companion object {
        fun fromCursor(cursor: Cursor): Sapatilha{

            val posId=cursor.getColumnIndex(BaseColumns._ID)
            val posModelo=cursor.getColumnIndex(TabelaSapatilha.CAMPO_MODELO)
            val posCor=cursor.getColumnIndex(TabelaSapatilha.CAMPO_COR)
            val posTamanho=cursor.getColumnIndex(TabelaSapatilha.CAMPO_TAMANHO)
            val posSerialNumber=cursor.getColumnIndex(TabelaSapatilha.CAMPO_SERIALNUMBER)
            val posMarcaFK=cursor.getColumnIndex(TabelaSapatilha.CAMPO_IDMARCA)


            val id= cursor.getLong(posId)
            val modelo=cursor.getString(posModelo)
            val cor=cursor.getString(posCor)
            val tamanho=cursor.getInt(posTamanho)
            val serialNumber=cursor.getString(posSerialNumber)
            val idMarca=cursor.getLong(posMarcaFK)

            return Sapatilha(modelo,cor,tamanho,serialNumber,idMarca,id)
        }
    }


}