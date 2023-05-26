package pt.ipg.sapatilhas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import pt.ipg.sapatilhas.databinding.FragmentMenuPrincipalBinding
import pt.ipg.sapatilhas.databinding.FragmentSneakerListBinding



/**
 * A simple [Fragment] subclass.
 * Use the [SneakerListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SneakerListFragment : Fragment() {
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


        return inflater.inflate(R.layout.fragment_sneaker_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapterSapatilhas=AdapterSapatilhas()
        binding.reciclerViewSapatilha.adapter=adapterSapatilhas
        binding.reciclerViewSapatilha.layoutManager=LinearLayoutManager(requireContext())

    }
    companion object {

    }

}