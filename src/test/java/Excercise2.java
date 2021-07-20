import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.JsonSerializable;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.JsonSerializer;

class Excercise2 {
    public static String name;
    public static void main(String[] args) {
        System.out.println("Please enter a country name");
        enterCountryName();

    }
    public static void enterCountryName(){

        Scanner scanner= new Scanner(System.in);
        name = scanner.nextLine();
        if (name.equalsIgnoreCase("exit")){
            System.out.println("Program is closed");
        } else {
            try {
                call_me();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void call_me() throws Exception {

        String url = "https://restcountries.eu/rest/v2/name/"+name;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = con.getResponseCode();
        if(responseCode<299) {
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            //print in String
            System.out.println(response.toString());
            //Read JSON response and print
            JSONObject myResponse = new JSONObject(response.toString());

            JSONObject name = myResponse.getJSONObject('{'+"name");

            String namee = name.getString("name");
            System.out.println("region- " + namee);
//            System.out.println("result after Reading JSON Response");
//            System.out.println("statusCode- " + myResponse.getString("statusCode"));
//            System.out.println("statusMessage- " + myResponse.getString("statusMessage"));
//            System.out.println("callingCodes- " + myResponse.getString("callingCodes"));
//            System.out.println("region- " + myResponse.getString("region"));
//            System.out.println("currencies- " + array.getJSONArray(3));
//            System.out.println("borders- " + array.getJSONArray(1));
//
        } else  {
            System.out.println("Please enter re-enter a country name");
            enterCountryName();
        }

    }
}
