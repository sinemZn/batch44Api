package test_data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DummyTestData {
    /*
http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
Status kodun 200 olduğunu,
14. Çalışan isminin "Haley Kennedy" olduğunu,
Çalışan sayısının 24 olduğunu,
Sondan 3. çalışanın maaşının 675000 olduğunu
40,21 ve 19 yaslarında çalışanlar olup olmadığını
10. Çalışan bilgilerinin bilgilerinin aşağıdaki gibi

{
        "id": 10,
        "employee_name": "Sonya Frost",
        "employee_salary": 103600,
        "employee_age": 23,
        "profile_image": ""
 }

  olduğunu test edin.
*/

    public HashMap<String, Object> setUpTestData(){
        //40,21 ve 19 yaslarında çalışanlar olup olmadığını
        //list oldugu icin add ile eklendi
        List<Integer> yaslar =new ArrayList<>();
        yaslar.add(40);
        yaslar.add(21);
        yaslar.add(19);

        //10. Çalışan bilgilerinin bilgilerinin aşağıdaki gibi
        //map oldugundan put ile ekliyoruz
        HashMap<String, Object> onuncu= new HashMap<>();
        onuncu.put("id", 10);
        onuncu.put("employee_name","Sonya Frost");
        onuncu.put("employee_salary",103600);
        onuncu.put("employee_age",23);
        onuncu.put("profile_image","");

         /*
    Status kodun 200 olduğunu,
   14. Çalışan isminin "Haley Kennedy" olduğunu,
   Çalışan sayısının 24 olduğunu,
   Sondan 3. çalışanın maaşının 675000 olduğunu
   40,21 ve 19 yaslarında çalışanlar olup olmadığını
   10. Çalışan bilgilerinin bilgilerinin aşağıdaki gibi
         */
         //tum datalari expecteddata mapte birlestirmis olduk
        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("statusCode", 200);
        expectedData.put("ondurduncucalisan", "Haley Kennedy");
        expectedData.put("calisansayisi", 24);
        expectedData.put("sondanucuncucalisaninmaasi", 675000);
        expectedData.put("arananyaslar", yaslar);
        expectedData.put("onuncucalisan", onuncu);
        return expectedData;





    }
}
