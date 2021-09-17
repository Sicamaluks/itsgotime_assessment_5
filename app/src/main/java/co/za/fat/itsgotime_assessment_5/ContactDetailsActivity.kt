package co.za.fat.itsgotime_assessment_5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import co.za.fat.itsgotime_assessment_5.core.model.Contact
import com.squareup.picasso.Picasso

class ContactDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_details)
        // Link text view to code
        val ivProfilePic = findViewById<ImageView>(R.id.iv_profilepic_detail)
        val tvFirstname = findViewById<TextView>(R.id.tv_firstname_detail)

        val shareProfile = findViewById<Button>(R.id.shareProfile)

        // get data
        val contact = intent.getParcelableExtra<Contact>("Contact")

        // add user name to text view
        tvFirstname.text = contact?.firstName
        // add user profile using picasso
        Picasso.get().load(contact?.profilePic).into(ivProfilePic)


        shareProfile.setOnClickListener {
            if (contact != null) {
                this.shareProfile(contact)
            }
        }
    }

    fun shareProfile (contact: Contact) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, contact.firstName)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

}