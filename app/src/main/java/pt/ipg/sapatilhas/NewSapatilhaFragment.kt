package pt.ipg.sapatilhas

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleCursorAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import pt.ipg.sapatilhas.databinding.FragmentNewSapatilhaBinding


private const val ID_LOADER_MARCA = 0

class NewSapatilhaFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {
    private var sapatilha: Sapatilha?= null
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

        val sapatilha = NewSapatilhaFragmentArgs.fromBundle(requireArguments()).sapatilha

        if (sapatilha != null) {
            activity.atualizaTitulo(R.string.editar_sapatilha_label)
            binding.textViewAlterarNovo.setText(R.string.editar_sapatilha_label)

            binding.EditTextModel.setText(sapatilha.Modelo)
            binding.EditTextColor.setText(sapatilha.Cor)
            binding.EditTextSize.setText(sapatilha.Tamanho.toString())
            binding.EditTextSerialNumber.setText(sapatilha.SerialNumber)

        }else {
            activity.atualizaTitulo(R.string.New_Sneaker_Label)

            binding.textViewAlterarNovo.setText(R.string.New_Sneaker_Label)
        }

        this.sapatilha = sapatilha
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun processaOpcaoMenu(item: MenuItem) : Boolean{
        return  when(item.itemId) {
            R.id.action_Delite-> {

                guardar()
                true
            }
            R.id.action_cancele->{

                voltaParaSneakerListFragment()
                true
            }

            else-> false
        }
    }

    private fun guardar() {
        val modelo=binding.EditTextModel.text.toString()
        if(modelo.isBlank()){
            binding.EditTextModel.error=getString(R.string.RequestModel)
            binding.EditTextModel.requestFocus()
            return
        }
        val marcaid=binding.spinnerMarca.selectedItemId

        val cor=binding.EditTextColor.text.toString()
        if(cor.isBlank()){
            binding.EditTextColor.error=getString(R.string.RequestColor)
            binding.EditTextColor.requestFocus()
            return
        }
        val tamanho=binding.EditTextSize.text.toString()
        if(tamanho.isBlank()){
            binding.EditTextSize.error=getString(R.string.RequestSize)
            binding.EditTextSize.requestFocus()
            return
        }

        val SerialNumber=binding.EditTextSerialNumber.text.toString()
        if(SerialNumber.isBlank()){
            binding.EditTextSerialNumber.error=getString(R.string.RequestSerialNumber)
            binding.EditTextSerialNumber.requestFocus()
            return
        }
        if (sapatilha == null) {
            val sapatilha = Sapatilha(
                modelo,
                cor,
                tamanho.toInt(),
                SerialNumber,
                Marca("?", "?",marcaid),
            )

            insereSapatilha(sapatilha)
        } else {
            val sapatilha = sapatilha!!
            sapatilha.Modelo = modelo
            sapatilha.Cor=cor
            sapatilha.Tamanho=tamanho.toInt()
            sapatilha.SerialNumber=SerialNumber
            sapatilha.marca = Marca("?","", marcaid)
            alteraSapatilha(sapatilha)
        }
    }

    private fun alteraSapatilha(sapatilha: Sapatilha) {
        val enderecoSapatilha= Uri.withAppendedPath(SapatilhaContentProvider.ENDERECO_SAPATILHA, sapatilha.id.toString())
        val SapatilhaAlterados = requireActivity().contentResolver.update(enderecoSapatilha, sapatilha.toContentValues(), null, null)

        if (SapatilhaAlterados == 1) {
            Toast.makeText(requireContext(), R.string.Sapatilha_alteradas_com_sucesso, Toast.LENGTH_LONG).show()
            voltaParaSneakerListFragment()
        } else {
            binding.EditTextModel.error = getString(R.string.erro_alteradas_Sapatilha)
        }
    }

    private fun insereSapatilha(
        sapatilha: Sapatilha
    ) {
       val  id= requireActivity().contentResolver.insert(
            SapatilhaContentProvider.ENDERECO_SAPATILHA,
           sapatilha.toContentValues()
        )
        if(id==null){
            binding.EditTextModel.error=getString(R.string.ErrorSapatilhaNew)
            return
        }
        Toast.makeText(context,R.string.AcertSapatilhaNew , Toast.LENGTH_SHORT).show()
        voltaParaSneakerListFragment()
    }

    private fun voltaParaSneakerListFragment() {
        findNavController().navigate(R.id.action_newSapatilhaFragment_to_SneakerListFragment)
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        return CursorLoader(
            requireContext(),
            SapatilhaContentProvider.ENDERECO_MARCA,
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

    private fun mostraCategoriaSelecionadaSpinner() {
        if (sapatilha == null) return

        val idMarca = sapatilha!!.marca.id

        val ultimaMarca = binding.spinnerMarca.count - 1
        for (i in 0..ultimaMarca) {
            if (idMarca == binding.spinnerMarca.getItemIdAtPosition(i)) {
                binding.spinnerMarca.setSelection(i)
                return
            }
        }
    }


}