package com.kdev.consultacep.service

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kdev.consultacep.model.Endereco
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://viacep.com.br/ws/"

private val moshi: Moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

private val retrofit = Retrofit.Builder()
    //Faz o map pro da resposta transformando-a num objeto MarsProperty
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface EnderecoService {
    //endpoint que este método vai chamar, fazendo um get no padrão baseUrl/realestate
    @GET("{cep}/json")
    fun getEnderecoByCepAsync(@Path("cep") cep : String): Deferred<Endereco>
}

object EnderecoApi {
    val retrofitService : EnderecoService by lazy {
        retrofit.create(EnderecoService::class.java)
    }
}