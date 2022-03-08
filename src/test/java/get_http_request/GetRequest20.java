package get_http_request;


     /*
https://jsonplaceholder.typicode.com/todos/2
1) Status kodunun 200,
2) respose body'de,
         "completed": değerinin false
         "title": değerinin "quis ut nam facilis et officia qui"
         "userId" sinin 1 ve
    header değerlerinden
         "via" değerinin "1.1 vegur" ve
         "Server" değerinin "cloudflare" olduğunu test edin…
*/
    import base_url.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

    public class GetRequest20 extends JsonPlaceHolderBaseUrl {

         /*
          https://jsonplaceholder.typicode.com/todos/2
          1) Status kodunun 200,
          2) respose body'de,
         "completed": değerinin false
         "title": değerinin "quis ut nam facilis et officia qui"
         "userId" sinin 1 ve
           header değerlerinden
         "via" değerinin "1.1 vegur" ve
         "Server" değerinin "cloudflare" olduğunu test edin…
           */

        @Test
        public void test20(){

            // 1)URL OLUSTUR
            spec04.pathParams("parametre1","todos","parametre2",2);

            //EXPECTED DATA OLUSTUR

            HashMap<String,Object> expectedData=new HashMap<>();
            expectedData.put("statusCode",200);
            expectedData.put("completed",false);
            expectedData.put( "title","quis ut nam facilis et officia qui");
            expectedData.put("userId", 1 );
            expectedData.put("via", "1.1 vegur");
            expectedData.put( "Server", "cloudflare");

            System.out.println("EXPECTED DATA : " +expectedData);


            //REQUEST VE RESPONSE DATA OLUSTUR

            Response response=given().spec(spec04).when().get("/{parametre1}/{parametre2}");

            // DOGRULAMA
            //  MATCHERS CLASS
            response.then().assertThat().statusCode((Integer) expectedData.get("statusCode"))
                    .headers("via",equalTo(expectedData.get("via")),"Server",equalTo(expectedData.get("Server")))
                    .body("userId",equalTo(expectedData.get("userId")),"title",equalTo(expectedData.get("title"))
                            ,"completed",equalTo(expectedData.get("completed")));


            //  JSON PATH

            JsonPath json=response.jsonPath();

            Assert.assertEquals(expectedData.get("statusCode"), response.statusCode());
            Assert.assertEquals(expectedData.get("via"),response.getHeader("via"));
            Assert.assertEquals(expectedData.get("Server"),response.getHeader("Server"));// burdakı expectedi sil bak calısyormı

            Assert.assertEquals(expectedData.get("userId"),json.getInt("userId"));
            Assert.assertEquals(expectedData.get("title"), json.getString("title"));
            Assert.assertEquals(expectedData.get("completed"), json.getBoolean("completed"));

            //3) YOL DE-SERİALİZATİON

            Map<String,Object> actualData=response.as(HashMap.class);
            System.out.println("ACTUAL DATA :" + actualData);


            Assert.assertEquals(expectedData.get("completed"),actualData.get("completed"));
            Assert.assertEquals(expectedData.get("title"),actualData.get("title"));
            Assert.assertEquals(expectedData.get("userId"),actualData.get("userId"));




        }

    }

