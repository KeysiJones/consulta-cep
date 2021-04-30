package com.kdev.consultacep.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kdev.consultacep.model.Endereco
import com.kdev.consultacep.service.EnderecoApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DadosViewModel : ViewModel() {
    // The internal MutableLiveData String that stores the status of the most recent request
    private val _status = MutableLiveData<String>()

//    // The external immutable LiveData for the request status String
//    val response: LiveData<String>
//        get() = _status

    private val _property = MutableLiveData<Endereco>()

    val property: LiveData<Endereco>
        get() = _property

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */

    fun fetchEnderecoFromApi(cep: String) {

        var endereco: Endereco;

        coroutineScope.launch {
            var getPropertiesDeferred = EnderecoApi.retrofitService.getEnderecoByCepAsync(cep)
            try {
                endereco = getPropertiesDeferred.await()
                if (endereco.localidade.isNotEmpty()) {
                    println("************************ Resultado vindo da API ********************")
                    println(endereco);
                    _property.value = endereco!!
                }

            } catch (t: Throwable) {
                println("********** MENSAGEM DE ERRO !!!: ${t.message} ***********")
            }
        }
    }
}