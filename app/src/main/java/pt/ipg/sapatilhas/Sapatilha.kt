package pt.ipg.sapatilhas
import android.content.ContentValues

data class Sapatilha(

    var Modelo:String,
    var Cor:String,
    var Tamanho:Int,
    var SerialNumber:String,
    var idSapatilha: Long,
    var id: Long=-1) {

    fun toContentValues(): ContentValues {
        val valores=ContentValues()

        valores.put(TabelaSapatilha.CAMPO_MODELO, Modelo)
        valores.put(TabelaSapatilha.CAMPO_COR, Cor)
        valores.put(TabelaSapatilha.CAMPO_TAMANHO, Tamanho)
        valores.put(TabelaSapatilha.CAMPO_SERIALNUMBER, SerialNumber)
        valores.put(TabelaSapatilha.CAMPO_IDMARCA, idSapatilha)
        return  valores
    }


}