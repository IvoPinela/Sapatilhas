package pt.ipg.sapatilhas

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleCursorAdapter
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import pt.ipg.sapatilhas.databinding.FragmentNewSapatilhaBinding


private const val ID_LOADER_MARCA = 0

class NewSapatilhaFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {
    private var _binding: FragmentNewSapatilhaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNewSapatilhaBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        val loader= LoaderManager.getInstance(this)
        loader.initLoader(0,null,this)

        val activity=(activity as MainActivity)
        activity.fragment=this
        activity.idMenuAtual=R.menu.menu_guardar_cancelar
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun processaOpcaoMenu(item: MenuItem) : Boolean{
        return  when(item.itemId) {
            R.id.action_save-> {

                guardar()
                true
            }
            R.id.action_cancele->{

                cancelar()
                true
            }

            else-> false
        }
    }

    private fun guardar() {
        TODO("Not yet implemented")
    }

    private fun cancelar() {
        findNavController().navigate(R.id.action_newSapatilhaFragment_to_SneakerListFragment)
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        return CursorLoader(
            requireContext(),
            TabelaSapatilha.CAMPO_IDMARCA,
            TabelaMarca.CAMPOS,
            null,
            null,
            TabelaMarca.Campo_Nome
        )
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        if (data==null){
            binding.spinnerMarca.adapter=null
            return
        }
        binding.spinnerMarca.adapter=SimpleCursorAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            data,
            arrayOf(TabelaMarca.Campo_Nome),
            intArrayOf(android.R.id.text1),
            0

        )
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        binding.spinnerMarca.adapter=null
    }


}