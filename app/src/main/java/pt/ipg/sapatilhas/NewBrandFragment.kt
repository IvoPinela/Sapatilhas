package pt.ipg.sapatilhas

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import pt.ipg.sapatilhas.databinding.FragmentNewBrandBinding


class NewBrandFragment : Fragment() {
    private var marca: Marca?= null
    private var _binding: FragmentNewBrandBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewBrandBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_guardar_cancelar

        val marca = NewBrandFragmentArgs.fromBundle(requireArguments()).marca

        if (marca != null) {
            activity.atualizaTitulo(R.string.editar_marca_label)
            binding.textViewaddorUpdate.setText(R.string.editar_marca_label)
            binding.TextInputEditTextNameBrand.setText(marca.nome)
            binding.TextInputEditTextHeadOffice.setText(marca.sede)


        }else {
            activity.atualizaTitulo(R.string.New_Sneaker_Label)

            binding.textViewaddorUpdate.setText(R.string.AddBrand)
        }
        this.marca = marca
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_Delite -> {
                guardar()
                true
            }
            R.id.action_cancele -> {
                cancelar()
                true
            }
            else -> false
        }
    }
    private fun cancelar() {
        voltarListaMarca()
    }

    private  fun voltarListaMarca(){
        findNavController().navigate(R.id.action_newBrandFragment_to_brandListFragment)
    }
    private fun guardar() {
        val nomeMarca = binding.TextInputEditTextNameBrand.text.toString()
        if (nomeMarca.isBlank()) {
            binding.TextInputEditTextNameBrand.error = getString(R.string.RequestNameBrand)
            binding.TextInputEditTextNameBrand.requestFocus()
            return
        }
        if (nomeMarca.length > 30) {
            binding.TextInputEditTextNameBrand.error = getString(R.string.Aviso_NomeMarca)
            binding.TextInputEditTextNameBrand.requestFocus()
            return
        }
        val sede = binding.TextInputEditTextHeadOffice.text.toString()
        if (sede.isBlank()) {
            binding.TextInputEditTextHeadOffice.error = getString(R.string.RequestHeadOffice)
            binding.TextInputEditTextHeadOffice.requestFocus()
            return
        }
        if (sede.length > 30) {
            binding.TextInputEditTextHeadOffice.error = getString(R.string.Aviso_Sede)
            binding.TextInputEditTextHeadOffice.requestFocus()
            return
        }
        if (marca == null) {
            val marca = Marca(
                nomeMarca,
                sede
            )

            insereMarca(marca)
        } else {
            val marca = marca!!
            marca.nome = nomeMarca
            marca.sede=sede

            alteraMarca(marca)
        }
    }

        private fun alteraMarca(marca: Marca) {
            val enderecoMarca= Uri.withAppendedPath(SapatilhaContentProvider.ENDERECO_MARCA, marca.id.toString())
            val marcaAlterados = requireActivity().contentResolver.update(enderecoMarca, marca.toContentValues(), null, null)

            if (marcaAlterados == 1) {
                Toast.makeText(requireContext(), R.string.Marca_alteradas_com_sucesso, Toast.LENGTH_LONG).show()
                voltarListaMarca()
            } else {
                binding.TextInputEditTextNameBrand.error = getString(R.string.ErrorBrandNew)
            }
        }
        private fun insereMarca(
            marca: Marca
        ){
        val id = requireActivity().contentResolver.insert(
            SapatilhaContentProvider.ENDERECO_MARCA,
            marca.toContentValues()
        )

        if (id == null) {
            binding.TextInputEditTextNameBrand.error = getString(R.string.ErrorBrandNew)
            return
        }
            Toast.makeText(requireContext(), getString(R.string.AcertBrandNew), Toast.LENGTH_SHORT).show()
            voltarListaMarca()

        }



    companion object {

    }
}