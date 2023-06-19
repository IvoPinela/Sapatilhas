package pt.ipg.sapatilhas

import android.content.DialogInterface
import android.net.Uri
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import pt.ipg.sapatilhas.NewSapatilhaFragmentArgs.Companion.fromBundle
import pt.ipg.sapatilhas.databinding.FragmentEliminarMarcaBinding


class EliminarMarcaFragment : Fragment() {
    private lateinit var marca: Marca
    private var _binding: FragmentEliminarMarcaBinding? = null
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
        _binding = FragmentEliminarMarcaBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_eliminar

        marca = EliminarMarcaFragmentArgs.fromBundle(requireArguments()).marca

        binding.textViewNomemarca.text = marca.nome
        binding.textViewHeadOffice.text = marca.sede
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun processaOpcaoMenu(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_Delete -> {
                val dialogoAlerta= AlertDialog.Builder(requireContext())
                dialogoAlerta.setMessage(R.string.Aviso_Eliminar_Marca)
                dialogoAlerta.setTitle(R.string.Eliminar_Marca_label)
                dialogoAlerta.setPositiveButton(android.R.string.ok, DialogInterface.OnClickListener { dialogInterface, witch -> eliminar() })
                dialogoAlerta.setNegativeButton(android.R.string.cancel, DialogInterface.OnClickListener { dialogInterface, witch ->voltarListaMarca() })
                dialogoAlerta.show()

                true
            }
            R.id.action_cancele -> {
                voltarListaMarca()
                true
            }
            else -> false
        }
    }

    companion object {

    }
    private  fun voltarListaMarca(){
        findNavController().navigate(R.id.action_eliminarMarcaFragment_to_brandListFragment)
    }
    private fun eliminar() {
        val enderecoMarca = Uri.withAppendedPath(SapatilhaContentProvider.ENDERECO_MARCA, marca.id.toString())
        val numMarcasEliminados = requireActivity().contentResolver.delete(enderecoMarca, null, null)

        if (numMarcasEliminados == 1) {
            Toast.makeText(requireContext(), getString(R.string.marca_eliminadacomsucesso), Toast.LENGTH_LONG).show()
            voltarListaMarca()
        } else {
            Snackbar.make(binding.textViewNomemarca, getString(R.string.erro_eliminar_marca), Snackbar.LENGTH_INDEFINITE)
        }
    }
}