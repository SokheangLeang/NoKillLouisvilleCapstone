package org.example

import Acuity
import java.time.LocalDate

object Test {
    @JvmStatic
    fun main(args: Array<String>) {

        val acuity = Acuity();

        val x = acuity.GetAppointment(LocalDate.of(2023, 1, 10), "1235550101")
        acuity.printInfo(x)
        println()



        val app = acuity.GetAppointmentList(LocalDate.of(2023, 1, 10));
        for (i in app.indices) {
            acuity.printInfo(app[i])
        }

        val x2 =  acuity.GetAppointment(LocalDate.of(2023, 1, 10), "123 555 0101")
        acuity.printInfo(x2)
        println()
        val x3 =  acuity.GetAppointment(LocalDate.of(2023, 1, 10), "123-555-0101")
        acuity.printInfo(x3)
        println()
        val x4 =  acuity.GetAppointment(LocalDate.of(2023, 1, 16), "test@test.com")
        acuity.printInfo(x4)
        println()
        val x5 =  acuity.GetAppointment(LocalDate.of(2023, 1, 16), "test@test.com")
        acuity.printInfo(x5)
        println()
    }

}
