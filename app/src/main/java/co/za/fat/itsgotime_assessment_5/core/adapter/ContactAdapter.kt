package co.za.fat.itsgotime_assessment_5.core.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import co.za.fat.itsgotime_assessment_5.R
import co.za.fat.itsgotime_assessment_5.core.model.Contact

class ContactAdapter(val dataset: List<Contact>) : RecyclerView.Adapter<ContactAdapter.ListViewHolder>() {
    var onItemClick: ((Contact) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ListViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = dataset[position]
    }

    override fun getItemCount() = dataset.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(dataset[adapterPosition])
            }
        }

        fun bindItems(contact: Contact) {
            val ivProfilePic: ImageView = itemView.findViewById(R.id.iv_profilepic)
            val tvFirstname: TextView = itemView.findViewById(R.id.tv_firstname)
            val tvLastname: TextView = itemView.findViewById(R.id.tv_lastname)
            val tvCompany: TextView = itemView.findViewById(R.id.tv_company)
            val tvMobile: TextView = itemView.findViewById(R.id.tv_mobile)
            val tvEmail: TextView = itemView.findViewById(R.id.tv_email)

            ivProfilePic.setImageURI(contact.profilePic.toUri())
            tvFirstname.text = contact.firstName
            tvLastname.text = contact.lastName
            tvCompany.text = contact.company
            tvMobile.text = contact.mobile
            tvEmail.text = contact.email
        }
    }

}