package pt.ipg.sapatilhas
import android.content.ContentValues


data class  Marca (
    var nome: String,
    var sede :String,
    var id: Long=-1) {

        fun toContentValues(): ContentValues {
            val valores= ContentValues()

            valores.put(TabelaSapatilha.Campo_N, Modelo)
            valores.put(TabelaSapatilha.CAMPO_COR, Cor)
            return  valores
        }
}