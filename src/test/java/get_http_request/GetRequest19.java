package get_http_request;

import base_url.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest19 extends DummyBaseUrl {

    /*
http://dummy.restapiexample.com/api/v1/employees
1) Status kodunun 200,
2) 10’dan büyük tüm id'leri ekrana yazdırın ve 10’dan büyük 14 id olduğunu,
3) 30’dan küçük tüm yaşları ekrana yazdırın ve bu yaşların içerisinde en büyük yaşın 23 olduğunu
4) Maası 350000 den büyük olan tüm employee name'leri ekrana yazdırın
     ve bunların içerisinde "Charde Marshall" olduğunu test edin
     */

    @Test
    public void test19(){
        //url olustur
        spec02.pathParams("1", "api", "2", "v1","3","employees");

        //response yapalim
        Response response=given().spec(spec02).when().get("/{1}/{2}/{3}");
        //"/{1}/{2}/{3}" => /api/v1/employees yazdik aslinda
        //response.prettyPrint(); bununla bi bakip kapattik cok uzun cunku

        //1- status kodunun 200 oldugu
        Assert.assertEquals(200,response.getStatusCode()); //1.yol
        response.then().assertThat().statusCode(200); //2.yol

        //2) 10’dan büyük tüm id'leri ekrana yazdırın ve 10’dan büyük 14 id olduğunu,
        JsonPath json=response.jsonPath();

        List<Integer>idList = json.getList("data.findAll{it.id>10}.id");
        //List<Integer>idList = json.getList("data.id.findAll{it>10}"); seklinde de yazilabilir
        System.out.println("ID List: " + idList);
        //GRoovy java platformu uzerinde calisan bir bilgisayar dilidir.yukarida findAll{it.id>10} kismi groovydir
        //groovy ile loop kullanmadan response dan gelen degerleri bir sarta gore yazdirabiliriz


        //3) 30’dan küçük tüm yaşları ekrana yazdırın ve bu yaşların içerisinde en büyük yaşın 23 olduğunu

        List<Integer>ageList =json.getList("data.id.findAll{it.employee_age<30}.employee_age");
        System.out.println("Age List: " + ageList);
        Collections.sort(ageList);
        Assert.assertEquals(23,(int) ageList.get(ageList.size()-1));//int yazarak casting yaptik
        //Assert.assertEquals((Integer) 23,ageList.get(ageList.size()-1)); basa integer yazarak casting yaptik,ikisinden biri secilir

       // Assert.assertTrue(ageList.get(ageList.size()-1)==23);  bu ise 2.cozum yolu

        //4) Maası 350000 den büyük olan tüm employee name'leri ekrana yazdırın
        //     ve bunların içerisinde "Charde Marshall" olduğunu test edin
        List<Integer>salaryList=json.getList("data.findAll{it.employee_salary>350000}.employee_name");
        System.out.println(salaryList);

        Assert.assertTrue(salaryList.contains("Charde Marshall"));

    }

}
