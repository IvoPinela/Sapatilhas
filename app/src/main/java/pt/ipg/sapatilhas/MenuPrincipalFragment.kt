package pt.ipg.sapatilhas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import pt.ipg.sapatilhas.databinding.FragmentMenuPrincipalBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MenuPrincipalFragment : Fragment() {

    private var _binding: FragmentMenuPrincipalBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMenuPrincipalBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSobre.setOnClickListener {
            findNavController().navigate(R.id.action_MenuPrincipalFragment_to_SobreFragment)
        }

        binding.buttonSapatilhas.setOnClickListener {
            findNavController().navigate(R.id.action_MenuPrincipalFragment_to_SneakerListFragment)
        }
        binding.buttonMarcas.setOnClickListener {
            findNavController().navigate(R.id.action_MenuPrincipalFragment_to_brandListFragment)
        }
        val activity=(activity as MainActivity)
        activity.fragment=this
        activity.idMenuAtual=R.menu.menu_main
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}