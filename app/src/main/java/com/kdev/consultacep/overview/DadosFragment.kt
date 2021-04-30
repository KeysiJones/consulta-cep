/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.kdev.consultacep.overview

import android.graphics.drawable.RotateDrawable
import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kdev.consultacep.R
import com.kdev.consultacep.databinding.FragmentDadosBinding
import com.kdev.consultacep.model.Endereco
import kotlinx.android.synthetic.main.fragment_dados.*
import kotlinx.android.synthetic.main.fragment_endereco.*
import kotlinx.android.synthetic.main.fragment_endereco.textViewCep

/**
 * This fragment shows the the status of the Mars real-estate web services transaction.
 */
class DadosFragment : Fragment() {

    /**
     * Lazily initialize our [EnderecoViewModel].
     */
    private val viewModel: DadosViewModel by lazy {
        ViewModelProvider(this).get(DadosViewModel::class.java)
    }

    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the OverviewFragment
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDadosBinding.inflate(inflater)

        var cep = arguments?.getString("cepDesejado");
        //var cep: String? = bundle?.getString("cepDesejado")
        println("**************** CEP PESQUISADO: $cep ***********************")

        activity?.setActionBar(binding.dadosToolbar)
        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)
        setHasOptionsMenu(true)

        viewModel.fetchEnderecoFromApi(cep.toString())

        viewModel.property.observe(viewLifecycleOwner, Observer<Endereco> { endereco ->
            cepText.text = endereco.cep
            ufText.text = endereco.uf
            logradouroText.text = endereco.logradouro
            localidadeText.text = endereco.localidade
            bairroText.text = endereco.bairro

            progressBar.visibility = ProgressBar.INVISIBLE
        })

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        return binding.root
    }

//    override fun onResume() {
//        super.onResume()
//        var localidade = view?.findViewById<TextView>(R.id.textViewLocalidade)
//        var progressBar = view?.findViewById<ProgressBar>(R.id.progressBar)
//
//        if (localidade != null) {
//            when(localidade.text) {
//                "Carregando..." -> {
//                    Toast.makeText(context, "VISIBLE", Toast.LENGTH_SHORT).show()
//                    progressBar?.visibility = View.VISIBLE
//                }
//
//                else -> {
//                    Toast.makeText(context, "GONE", Toast.LENGTH_SHORT).show()
//                    progressBar?.visibility = View.GONE
//                }
//            }
//        }
//    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_dados_fragment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_adicionar -> {
                Toast.makeText(view?.context, "Adicionado... ${item.itemId}", Toast.LENGTH_SHORT)
                    .show()
            }

            R.id.menu_cancelar -> {
                Toast.makeText(context, "Cancelando...", Toast.LENGTH_SHORT).show()
            }

            else -> {
                activity?.onBackPressed()
            }
        }
        return true
    }
}
