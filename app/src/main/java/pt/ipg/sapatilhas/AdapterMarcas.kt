package pt.ipg.sapatilhas
import android.widget.Toast
import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
class AdapterMarcas(val fragment: BrandListFragment): RecyclerView.Adapter<AdapterMarcas.ViewHolderMarca>() {
    var cursor: Cursor? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    inner class ViewHolderMarca(contentor: View) : RecyclerView.ViewHolder(contentor) {
        private val textViewNomeMarca = contentor.findViewById<TextView>(R.id.textViewNomeMarca)
        private val textViewSede = contentor.findViewById<TextView>(R.id.textViewSede)


        init {
            contentor.setOnClickListener {
                viewHolderSeleccionado?.desSeleciona()
                seleciona()
            }
        }

        internal  var marca:Marca?=null
            set(value) {
                field = value
                textViewNomeMarca.text = marca?.nome ?: ""
                textViewSede.text = marca?.sede ?: ""
            }
        fun seleciona() {
            viewHolderSeleccionado = this
            fragment.marcaSelecionada =marca
            itemView.setBackgroundResource(R.color.ItemSelecionado)
        }

        fun desSeleciona() {

            itemView.setBackgroundResource(R.color.white)

        }





    }
    private var viewHolderSeleccionado : ViewHolderMarca? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMarca {
        return ViewHolderMarca(
            fragment.layoutInflater.inflate(R.layout.item_marca, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return cursor?.count ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolderMarca, position: Int) {
        cursor!!.moveToPosition(position)
        holder.marca = Marca.fromCursor(cursor!!)
    }


}
