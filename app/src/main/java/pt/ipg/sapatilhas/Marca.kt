package pt.ipg.sapatilhas
import android.content.ContentValues


data class  Marca (
    var nome: String,
    var sede :String,
    var id: Long=-1) {

        fun toContentValues(): ContentValues {
            val valores= ContentValues()

            valores.put(TabelaMarca.Campo_Nome, nome)
            valores.put(TabelaMarca.Campo_Sede, sede)
            return  valores
        }
}