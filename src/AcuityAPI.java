package org.example;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.asynchttpclient.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Arrays.stream;

public class AcuityAPI {
    public static void main(String[] args) {


        Appointment[] app = GetAppointmentList(LocalDate.of(2023, 1, 16));

        for(int i = 0; i < app.length; i++)
        {
            System.out.println(app[i].firstName + " " + app[i].lastName);
        }

        Appointment x = GetAppointment(LocalDate.of(2023, 1, 10), "1235550101");
        System.out.println(x.id);

        Appointment x2 = GetAppointment(LocalDate.of(2023, 1, 10), "123 555 0101");
        System.out.println(x2.id);

        Appointment x3 = GetAppointment(LocalDate.of(2023, 1, 10), "123-555-0101");
        System.out.println(x3.id);

        Appointment x4 = GetAppointment(LocalDate.of(2023, 1, 16), "test@test.com");
        System.out.println(x4.id);



    }


    /**
     * Converts JSON from API request into a Java object in an Array
     * @param date
     * @return the array with the appointments
     */
    public static Appointment[] GetAppointmentList(LocalDate date)
    {
        Response res;
        ObjectMapper mapper = new ObjectMapper();
        String json;

        try {
            AsyncHttpClient client = new DefaultAsyncHttpClient();
            res = client.prepareGet("https://acuityscheduling.com/api/v1/appointments?max=100&minDate=" + date.getMonth().getValue() +"%2F" + date.getDayOfMonth()  + "%2F" + date.getYear() + "&maxDate="+ date.getMonth().getValue() +" %2F"+ date.getDayOfMonth() +"%2F"+ date.getYear() +"&canceled=false&excludeForms=true&direction=DESC")
                    .setHeader("accept", "application/json")
                    .setHeader("authorization", "Basic MjgxMzM5MzU6NGUwY2Q5ZmY3MWM5NjU0YTM2MDZiMjViZTg5YmMxMGI=")
                    .execute().get();
            client.close();

            json = res.getResponseBody();
            Appointment[] app = mapper.readValue(json, Appointment[].class);
            return app;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Appointment GetAppointment(LocalDate date, String user)
    {
        Response res;
        ObjectMapper mapper = new ObjectMapper();
        String json;

        String req = "";

        if(checkEmail(user))
        {
            req = "https://acuityscheduling.com/api/v1/appointments?max=1&minDate=" + date.getMonth().getValue() +"%2F" + date.getDayOfMonth()  + "%2F" + date.getYear() + "&maxDate="+ date.getMonth().getValue() +" %2F"+ date.getDayOfMonth() +"%2F"+ date.getYear() +"&canceled=false&email="+ user + "&excludeForms=true&direction=DESC";
        }
        else if(checkPhoneNum(user))
        {
            req = "https://acuityscheduling.com/api/v1/appointments?max=1&minDate=" + date.getMonth().getValue() +"%2F" + date.getDayOfMonth()  + "%2F" + date.getYear() + "&maxDate="+ date.getMonth().getValue() +" %2F"+ date.getDayOfMonth() +"%2F"+ date.getYear() +"&canceled=false&phone="+ user + "&excludeForms=true&direction=DESC";
        }

        try {
            AsyncHttpClient client = new DefaultAsyncHttpClient();
            res = client.prepareGet(req)
                    .setHeader("accept", "application/json")
                    .setHeader("authorization", "Basic MjgxMzM5MzU6NGUwY2Q5ZmY3MWM5NjU0YTM2MDZiMjViZTg5YmMxMGI=")
                    .execute().get();
            client.close();
            json = res.getResponseBody();
            Appointment[] app = mapper.readValue(json, Appointment[].class);
            if(app.length == 1)
            {
                return app[0];
            }
            else {
                return new Appointment();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }





    public static boolean checkEmail(String email) {
        Pattern pattern = Pattern.compile("^(.+)@(\\S+)$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean checkPhoneNum(String phoneNum) {
        Pattern pattern = Pattern.compile("^(\\d{3}[- .]?){2}\\d{4}$");
        Matcher matcher = pattern.matcher(phoneNum);
        return matcher.matches();
    }



    //Classes used for converting JSON into list

    public static class Person {

        String name;
        Integer age;

        //getters and setters
    }

    public static class Appointment{
        public int id;
        public String firstName;
        public String lastName;
        public String phone;
        public String email;
        public String date;
        public String time;
        public String endTime;
        public String dateCreated;
        public String datetimeCreated;
        public String datetime;
        public String price;
        public String priceSold;
        public String paid;
        public String amountPaid;
        public String type;
        public int appointmentTypeID;
        public Object classID;
        public ArrayList<Object> addonIDs;
        public String category;
        public String duration;
        public String calendar;
        public int calendarID;
        public Object certificate;
        public String confirmationPage;
        public String location;
        public String notes;
        public String timezone;
        public String calendarTimezone;
        public boolean canceled;
        public boolean canClientCancel;
        public boolean canClientReschedule;
        public Object labels;
    }

    public static class Value{
        public Object id;
        public int fieldID;
        public String value;
        public String name;
    }
}