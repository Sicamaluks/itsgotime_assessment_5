package co.za.fat.itsgotime_assessment_5.core.api


import co.za.fat.itsgotime_assessment_5.core.model.Contact
import retrofit2.Call
import retrofit2.http.GET

interface APIService {

    //Interface to fetch data from the service
    @GET("/contacts/?format=json")
    fun getAllContacts() : Call<List<Contact>>
}



