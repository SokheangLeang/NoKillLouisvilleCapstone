/**
 * Appointment Data Class
 */
data class Appointment(
    val id: Int = 0,
    val firstName: String = "",
    val lastName: String = "",
    val phone: String = "",
    val email: String = "",
    val date: String = "",
    val time: String = "",
    val endTime: String = "",
    val dateCreated: String = "",
    val datetimeCreated: String = "",
    val datetime: String = "",
    val price: String = "",
    val priceSold: String = "",
    val paid: String = "",
    val amountPaid: String = "",
    val type: String = "",
    val appointmentTypeID: Int = 0,
    val classID: Any? = null,
    val addonIDs: ArrayList<Any>? = null,
    val category: String = "",
    val duration: String = "",
    val calendar: String = "",
    val calendarID: Int = 0,
    val certificate: Any? = null,
    val confirmationPage: String = "",
    val location: String = "",
    val notes: String = "",
    val timezone: String = "",
    val calendarTimezone: String = "",
    val canceled: Boolean = false,
    val canClientCancel: Boolean = false,
    val canClientReschedule: Boolean = false,
    val labels: Any? = null
)