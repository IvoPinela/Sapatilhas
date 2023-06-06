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
import pt.ipg.sapatilhas.databinding.FragmentSneakerListBinding


private const val ID_LOADER_SAPATILHAS = 0

/**
 * A simple [Fragment] subclass.
 * Use the [SneakerListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SneakerListFragment : Fragment(),LoaderManager.LoaderCallbacks<Cursor>{
    private var _binding: FragmentSneakerListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var sapatilhaSelecionado: Sapatilha?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSneakerListBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private var adapterSapatilhas: AdapterSapatilhas?=null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterSapatilhas= AdapterSapatilhas(this)
        binding.reciclerViewSapatilha.adapter=adapterSapatilhas
        binding.reciclerViewSapatilha.layoutManager=LinearLayoutManager(requireContext())

        val loader=LoaderManager.getInstance(this)
        loader.initLoader(ID_LOADER_SAPATILHAS,null,this)

        val activity=(activity as MainActivity)
        activity.fragment=this
        activity.idMenuAtual=R.menu.menu_lista_sapatilhas

    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {

        return CursorLoader(
            requireContext(),
            SapatilhaContentProvider.ENDERECO_SAPATILHA,
            TabelaSapatilha.CAMPOS,
            null,null,
            TabelaSapatilha.CAMPO_MODELO

            )
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        adapterSapatilhas!!.cursor=null
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        adapterSapatilhas!!.cursor =data
    }

}