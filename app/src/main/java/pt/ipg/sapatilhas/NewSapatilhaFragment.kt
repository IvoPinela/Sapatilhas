package pt.ipg.sapatilhas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import pt.ipg.sapatilhas.databinding.FragmentNewSapatilhaBinding



class NewSapatilhaFragment : Fragment() {
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
}