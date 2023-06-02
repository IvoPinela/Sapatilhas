package pt.ipg.sapatilhas

import android.database.Cursor
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder


class AdapterSapatilhas(val fragment: SneakerListFragment) : RecyclerView.Adapter<AdapterSapatilhas.ViewHolderSapatilhas>() {
    var cursor:Cursor?=null
    set(value){
        field=value
        notifyDataSetChanged()
    }

    inner class ViewHolderSapatilhas(contentor: View) : ViewHolder(contentor) {
        private val textViewModelo=contentor.findViewById<TextView>(R.id.textViewModelo)
        private val textViewMarca=contentor.findViewById<TextView>(R.id.textViewMarca)
        private val textViewCor=contentor.findViewById<TextView>(R.id.textViewCor)
        private val textViewTamanho=  contentor.findViewById<TextView>(R.id.textViewTamanho)

        init {
            contentor.setOnClickListener {
                ViewHolderSelecionado?.desSeleciona()
                seleciona()
            }
        }

        internal var sapatilha: Sapatilha?=null
        set(value){
            field=value
            textViewModelo.text=sapatilha?.Modelo?:""
            textViewMarca.text=sapatilha?.marca?.nome?:""
            textViewCor.text=sapatilha?.Cor?:""
            textViewTamanho.text=sapatilha?.Tamanho.toString()?:""

        }
        fun seleciona(){
            ViewHolderSelecionado=this
            itemView.setBackgroundResource(R.color.ItemSelecionado)
        }
        fun desSeleciona(){
            itemView.setBackgroundResource(android.R.color.white)
        }

    }
    private  var ViewHolderSelecionado:ViewHolderSapatilhas?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderSapatilhas {

        return ViewHolderSapatilhas(
            fragment.layoutInflater.inflate(R.layout.item_sapatiha,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return cursor?.count ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolderSapatilhas, position: Int) {
       cursor!!.moveToPosition(position)
        holder.sapatilha=Sapatilha.fromCursor(cursor!!)

    }

}