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
import pt.ipg.sapatilhas.databinding.FragmentMenuPrincipalBinding
import pt.ipg.sapatilhas.databinding.FragmentSneakerListBinding
import pt.ipg.sapatilhas.databinding.FragmentSobreBinding


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

    private val adapterSapatilhas= AdapterSapatilhas()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.reciclerViewSapatilha.adapter=adapterSapatilhas
        binding.reciclerViewSapatilha.layoutManager=LinearLayoutManager(requireContext())

        val loader=LoaderManager.getInstance(this)
        loader.initLoader(ID_LOADER_SAPATILHAS,null,this)

    }
    companion object {

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
        adapterSapatilhas.cursor=null
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        adapterSapatilhas.cursor=data
    }

}