package pojoRequest;

import base_url.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;

import static io.restassured.RestAssured.given;

public class PostRequestPojo02 extends HerOkuAppBaseUrl {
    /*
 https://restful-booker.herokuapp.com/booking
 request body
 { "firstname": "Ali",
            "lastname": "Can",
            "totalprice": 500,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2022-03-01",
                "checkout": "2022-03-11"
             }
 }}
Status code is 200
 response body
 {
    "bookingid": 11,
       "booking": {
         "firstname": "Ali",
         "lastname": "Can",
         "totalprice": 500,
         "depositpaid": true,
         "bookingdates": {
            "checkin": "2022-03-01",
            "checkout": "2022-03-11"
                             }
                         }
                     }
  */

    @Test
    public void test(){
        //1) url olustur
        spec05.pathParam("bir","booking");

        //2) expected data
        BookingDatesPojo bookingDates=new BookingDatesPojo("2022-03-01","2022-03-11");
        System.out.println(bookingDates);

        BookingPojo bookingPojo=new BookingPojo("Ali","Can",500,true,bookingDates);
        System.out.println(bookingPojo);

        //request ve responce
        Response response=given().contentType(ContentType.JSON).spec(spec05).auth().basic("admin","password123")
                          .body(bookingPojo).when().post("/{bir}");

        response.prettyPeek();
     //4)dogrulama

    }



}
