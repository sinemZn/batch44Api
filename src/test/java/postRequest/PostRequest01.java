package postRequest;

import base_url.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import static io.restassured.RestAssured.given;

public class PostRequest01 extends HerOkuAppBaseUrl {
    /*
   https://restful-booker.herokuapp.com/booking
   { "firstname": "Ali",
              "lastname": "Can",
              "totalprice": 500,
              "depositpaid": true,
              "bookingdates": {
                  "checkin": "2022-03-01",
                  "checkout": "2022-03-11"
               }
}
gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
}
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
olduğunu test edin
    */

   @Test
    public void test01(){
       //1) URL olustur
       spec05.pathParam("parametre1", "booking");

       //2) expected data
       HerOkuAppTestData testData = new HerOkuAppTestData();
       JSONObject expectedRequestData = testData.setUpTestAndRequestData();
       System.out.println(expectedRequestData);

       //3)Request ve Responce
       Response response= given()
                         .contentType(ContentType.JSON)
                         .auth()
                         .basic("admin", "password123")
                         .spec(spec05)
                         .body(expectedRequestData.toString())//jsonObject de tostring eklenmeli
                         .when()
                         .post("{/parametre1}");

       response.prettyPrint();

      // 4) DOGRULAMA
      // 1. WAY JSON PATH
      JsonPath json = response.jsonPath();
      response.then().assertThat().statusCode(200);
      Assert.assertEquals(expectedRequestData.getString("firstname") , json.getString("booking.firstname"));
      Assert.assertEquals(expectedRequestData.getString("lastname") , json.getString("booking.lastname"));
      Assert.assertEquals(expectedRequestData.getInt("totalprice") , json.getInt("booking.totalprice"));
      Assert.assertEquals(expectedRequestData.getBoolean("depositpaid") , json.getBoolean("booking.depositpaid"));
      // 1.Yol Casting
      Assert.assertEquals(((JSONObject)expectedRequestData.get("bookingdates")).getString("checkin") ,
              json.getString("booking.bookingdates.checkin"));
      Assert.assertEquals(((JSONObject)expectedRequestData.get("bookingdates")).getString("checkout") ,
              json.getString("booking.bookingdates.checkout"));
      // 2. Yol
      Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkin") ,
              json.getString("booking.bookingdates.checkin"));
      Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkout") ,
              json.getString("booking.bookingdates.checkout"));
   }
}
