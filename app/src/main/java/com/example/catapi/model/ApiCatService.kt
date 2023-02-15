package com.example.catapi.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiCatService {

    @GET
    suspend fun getCats( @Url URL: String ): Response< List<CatResponse> > // esperar hasta recibir la respuesta -  Obtenemos lo que estamos mapeando, como solo es un gato ya no ocupamos list.

}