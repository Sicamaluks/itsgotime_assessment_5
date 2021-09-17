package co.za.fat.itsgotime_assessment_5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.za.fat.itsgotime_assessment_5.core.adapter.ContactAdapter
import co.za.fat.itsgotime_assessment_5.core.api.APIService
import co.za.fat.itsgotime_assessment_5.core.model.Contact
import co.za.fat.itsgotime_assessment_5.core.api.RetrofitClient
import co.za.fat.itsgotime_assessment_5.core.extensions.showErrorMessage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    private lateinit var adapter: ContactAdapter
    private lateinit var recyclerView: RecyclerView
    private var contacts: List<Contact> = mutableListOf()

    private val itsGoTimeAPIService: APIService by lazy {
        RetrofitClient.apiService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // link recyclerView
        recyclerView = findViewById(R.id.recyclerView)

        // create a layout manager
        recyclerView.layoutManager = LinearLayoutManager(this)

        //fetch data from the backend server

        fetchContacts()
    }

    private fun fetchContacts() {
        itsGoTimeAPIService.getAllContacts().enqueue(object : Callback<List<Contact>> {

            override fun onResponse(call: Call<List<Contact>>?, response: Response<List<Contact>>) {
                if (response.isSuccessful) {
                    Log.i(TAG, "contacts loaded from API $response")

                    response.body()?.let {
                        contacts = it
                    }

                    if (contacts.isNotEmpty())
                        setupRecyclerView(contacts)
                    else
                        Toast.makeText(this@MainActivity, "No Items Found", Toast.LENGTH_LONG).show()

                } else {
                    Log.i(TAG, "error $response")
                    showErrorMessage(response.errorBody()!!)
                }
            }

            override fun onFailure(call: Call<List<Contact>>?, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message ?: "Error Fetching Results", Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun setupRecyclerView(contacts: List<Contact>) {
        adapter = ContactAdapter(contacts)
        recyclerView.adapter = adapter

        // add on click for elements
        adapter.onItemClick = { contact ->

            val intent = Intent(this, ContactDetailsActivity::class.java)
            intent.putExtra("contact", contact)
            startActivity(intent)
        }
    }
}