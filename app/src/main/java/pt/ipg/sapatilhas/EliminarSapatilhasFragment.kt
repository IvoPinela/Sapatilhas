package pt.ipg.sapatilhas

import android.net.Uri
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import pt.ipg.sapatilhas.databinding.FragmentEliminarSapatilhasBinding


class EliminarSapatilhasFragment : Fragment() {
    private lateinit var sapatilha: Sapatilha
    private var _binding: FragmentEliminarSapatilhasBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEliminarSapatilhasBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_eliminar

        sapatilha = EliminarSapatilhasFragmentArgs.fromBundle(requireArguments()).sapatilha

        binding.textViewModeloDelete.text = sapatilha.Modelo
        binding.textViewColorDelete .text = sapatilha.Cor
        binding.textViewSizeDelete.text = sapatilha.Tamanho.toString()
        binding.textViewSerialNumberDelete.text = sapatilha.SerialNumber
        binding.textViewBrandDelete.text=sapatilha.marca.nome


    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_Delete -> {
                eliminar()
                true
            }
            R.id.action_cancele -> {
                voltaListaSapatilha()
                true
            }
            else -> false
        }
    }
    private fun voltaListaSapatilha() {
        findNavController().navigate(R.id.action_iliminarSapatilhasFragment_to_SneakerListFragment)
    }

    private fun eliminar() {
        val enderecoSapatilha = Uri.withAppendedPath(SapatilhaContentProvider.ENDERECO_SAPATILHA, sapatilha.id.toString())
        val numSapatilhEliminados = requireActivity().contentResolver.delete(enderecoSapatilha, null, null)

        if (numSapatilhEliminados == 1) {
            Toast.makeText(requireContext(), getString(R.string.Sapatilha_eliminado_com_sucesso), Toast.LENGTH_LONG).show()
            voltaListaSapatilha()
        } else {
            Snackbar.make(binding.textViewModeloDelete, getString(R.string.erro_eliminar_Sapatilha), Snackbar.LENGTH_INDEFINITE)
        }
    }
}