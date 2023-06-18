package pt.ipg.sapatilhas

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.LinearLayoutManager
import pt.ipg.sapatilhas.databinding.FragmentBrandListBinding

private const val ID_LOADER_MARCA = 0
private val adapterMarcas2: AdapterMarcas
    get() {
        val adapterMarcas = AdapterMarcas()
        return adapterMarcas
    }


class BrandListFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {

    private var _binding: FragmentBrandListBinding?=null
    private  val binding get()=_binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBrandListBinding.inflate(inflater, container, false)
        return binding.root
    }
    private val adapterMarcas = AdapterMarcas()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapterMarcas=AdapterMarcas()
        binding.recyclerViewMarcas.adapter=adapterMarcas
        binding.recyclerViewMarcas.layoutManager= LinearLayoutManager(requireContext())
        val loader = LoaderManager.getInstance(this)
        loader.initLoader(ID_LOADER_MARCA, null, this)
    }
    companion object {

    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        return CursorLoader(
            requireContext(),
            SapatilhaContentProvider.ENDERECO_MARCA,
            TabelaMarca.CAMPOS,
            null, null,
            TabelaMarca.Campo_Nome
        )
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        adapterMarcas.cursor = null
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        adapterMarcas.cursor = data
    }
}