package pt.ipg.sapatilhas

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AdapterMarcas(val fragment: BrandListFragment): RecyclerView.Adapter<AdapterMarcas.ViewHolderMarca>() {
    var cursor: Cursor? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    inner class ViewHolderMarca(itemView: View) : RecyclerView.ViewHolder(itemView) {
            internal  var marca:Marca?=null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMarca {
        return ViewHolderMarca(
            fragment.layoutInflater.inflate(R.layout.item_marca, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return cursor?.count ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolderMarca, position: Int) {
        cursor!!.move(position)
        holder.marca = Marca.fromCursor(cursor!!)
    }
}