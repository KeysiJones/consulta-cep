
package com.kdev.consultacep.overview

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.kdev.consultacep.R
import com.kdev.consultacep.databinding.FragmentEnderecoBinding

/**
 * This fragment shows the the status of the Mars real-estate web services transaction.
 */
class EnderecoFragment : Fragment() {

    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the OverviewFragment
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentEnderecoBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.enderecoToolbar.title = ""

        activity?.setActionBar(binding.enderecoToolbar)
        activity?.actionBar?.setDisplayShowHomeEnabled(true)
        setHasOptionsMenu(true)

        binding.btnBuscar.setOnClickListener { view: View ->
            if (binding.textViewCep.text.toString().length == 9) {
                binding.layoutCep.isErrorEnabled = false
                binding.layoutCep.error = ""

                var bundle: Bundle = Bundle()
                var cep: String = binding.textViewCep.text.toString()

                System.out.println("************* cep: $cep *****************")
                bundle.putString("cepDesejado", cep)

                view.findNavController().navigate(R.id.action_enderecoFragment_to_dadosFragment, bundle) 
            } else {
                binding.layoutCep.isErrorEnabled = true
                binding.layoutCep.error = "O cep deve possuir 8 caracteres !"
            }
        }

        binding.enderecoToolbar.setNavigationOnClickListener { view ->
            val myDrawerMenu = activity?.findViewById<DrawerLayout>(R.id.drawerLayout)
            myDrawerMenu?.openDrawer(Gravity.LEFT)
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_endereco_fragment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.add_endereco -> {
                Toast.makeText(context, "Adding...", Toast.LENGTH_SHORT).show()
            }
        }

        return true
    }
}
