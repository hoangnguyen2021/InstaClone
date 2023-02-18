package myapp.hoang.instaclone.models

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import myapp.hoang.settings.models.Email
import myapp.hoang.settings.models.MobileNumber

@Serializable
data class InstaCloneUser(
    val _id: String,
    val mobileNumber: MobileNumber? = null,
    val email: Email? = null,
    val fullName: String,
    val username: String,
    val birthday: LocalDate,
    val agreedToPolicy: Boolean = false,
    val profilePicPath: String? = null
)