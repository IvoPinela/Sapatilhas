package pt.ipg.sapatilhas

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder


class AdapterSapatilhas(val fragment: SneakerListFragment) : RecyclerView.Adapter<AdapterSapatilhas.ViewHolderSapatilhas>() {
    var cursor:Cursor?=null
    set(value){
        field=value
        notifyDataSetChanged()
    }
    inner class ViewHolderSapatilhas(itemView: View) : ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderSapatilhas {

        return ViewHolderSapatilhas(fragment.layoutInflater.inflate(R.layout.item_sapatiha,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return cursor?.count ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolderSapatilhas, position: Int) {
        TODO("Not yet implemented")
    }
}