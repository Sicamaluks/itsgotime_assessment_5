package co.za.fat.itsgotime_assessment_5.core.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Contact(
    val firstName: String,
    val lastName: String,
    val company: String,
    val mobile: String,
    val email: String,
    val profilePic: String
): Parcelable

data class ErrorResponse(val message: String?)
