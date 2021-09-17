package co.za.fat.itsgotime_assessment_5.core.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    //Base url of server, we will use this with all the network calls that we will make.
    private val BASE_URL = "https://8000-cs-603297329329-default.cs-asia-southeast1-ajrg.cloudshell.dev/"
    var gson = GsonBuilder()
        .setLenient()
        .create()
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()


    val apiService: APIService = retrofit.create(APIService::class.java)
}