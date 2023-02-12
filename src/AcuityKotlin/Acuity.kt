import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.time.LocalDate
import java.util.concurrent.ExecutionException
import java.util.regex.Pattern

/**
 * There are 3 functions in the Acuity Class
 *
 * GetAppointmentList(LocalDate) generates a Array of the appointment on a given day.
 *
 * GetAppointment(LocalDate, String) generates a Appointment object of a given day and email/phone number.
 *
 * PrintInfo(Appointment) will print the necessary information of the appointment such as:
 * ID
 * Name
 * Email
 * Phone
 * Date
 * Time
 */


class Acuity {
    fun GetAppointmentList(date: LocalDate): Array<Appointment> {
        val mapper = ObjectMapper()
        return try {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("https://acuityscheduling.com/api/v1/appointments?max=100&minDate=" + date.getMonth()
                    .getValue() + "%2F" + date.getDayOfMonth() + "%2F" + date.getYear() + "&maxDate=" + date.getMonth()
                    .getValue() + " %2F" + date.getDayOfMonth() + "%2F" + date.getYear() + "&canceled=false&excludeForms=true&direction=DESC")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("authorization", "Basic MjgxMzM5MzU6NGUwY2Q5ZmY3MWM5NjU0YTM2MDZiMjViZTg5YmMxMGI=")
                .build()

            val response = client.newCall(request).execute()
            println('x')
            val json = response.body?.string()
            println('x')
            mapper.readValue(json, Array<Appointment>::class.java)
        } catch (e: IOException) {
            throw RuntimeException(e)
        } catch (e: ExecutionException) {
            throw RuntimeException(e)
        } catch (e: InterruptedException) {
            throw RuntimeException(e)
        }
    }

    fun GetAppointment(date: LocalDate, user: String): Appointment {
        val mapper = ObjectMapper()
        var req = ""
        if (checkEmail(user)) {
            req = "https://acuityscheduling.com/api/v1/appointments?max=1&minDate=" + date.getMonth()
                .getValue() + "%2F" + date.getDayOfMonth() + "%2F" + date.getYear() + "&maxDate=" + date.getMonth()
                .getValue() + " %2F" + date.getDayOfMonth() + "%2F" + date.getYear() + "&canceled=false&email=" + user + "&excludeForms=true&direction=DESC"
        } else if (checkPhoneNum(user)) {
            req = "https://acuityscheduling.com/api/v1/appointments?max=1&minDate=" + date.getMonth()
                .getValue() + "%2F" + date.getDayOfMonth() + "%2F" + date.getYear() + "&maxDate=" + date.getMonth()
                .getValue() + " %2F" + date.getDayOfMonth() + "%2F" + date.getYear() + "&canceled=false&phone=" + user + "&excludeForms=true&direction=DESC"
        }
        return try {
            val client = OkHttpClient()

            val request = Request.Builder()
                .url(req)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("authorization", "Basic MjgxMzM5MzU6NGUwY2Q5ZmY3MWM5NjU0YTM2MDZiMjViZTg5YmMxMGI=")
                .build()

            val response = client.newCall(request).execute()
            val json = response.body?.string()
            val app: Array<Appointment> = mapper.readValue(json, Array<Appointment>::class.java)
            if (app.isNotEmpty()) {
                app[0]
            } else {
                Appointment()
            }
        } catch (e: IOException) {
            throw RuntimeException(e)
        } catch (e: ExecutionException) {
            throw RuntimeException(e)
        } catch (e: InterruptedException) {
            throw RuntimeException(e)
        }
    }

    fun printInfo(appointment: Appointment) {
        println(
            """ID: ${appointment.id}
Name: ${appointment.firstName} ${appointment.lastName}
Email: ${appointment.email}
Phone: ${appointment.phone}
Date: ${appointment.date}
Time: ${appointment.time}"""
        )
    }

    private fun checkEmail(email: String?): Boolean {
        val pattern = Pattern.compile("^(.+)@(\\S+)$")
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

    private fun checkPhoneNum(phoneNum: String?): Boolean {
        val pattern = Pattern.compile("^(\\d{3}[- .]?){2}\\d{4}$")
        val matcher = pattern.matcher(phoneNum)
        return matcher.matches()
    }
}

