import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Scanner;

public class Excercise3 {
    public static void main(String[] args) throws IOException, JSONException {
        System.out.println("Welcome to currency converter");
        System.out.println("Enter amount of dollars to convert to shekels");
        Scanner scanner = new Scanner(System.in);
        int amount = scanner.nextInt();

        // use OKHttp client to create the connection and retrieve data
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://api.exchangeratesapi.io/v1/latest?access_key=55dffc95e3b67cabdc983b6dd695ed5a&symbols=USD,ILS")
                .build();
        Response response = client.newCall(request).execute();
        String jsonData = response.body().string();
        // parse JSON
        JSONObject mainJsonObject = new JSONObject(jsonData);
        // get Json object
        JSONObject resultsJson = mainJsonObject.getJSONObject("rates");
        // get value
        double currency = resultsJson.getDouble("ILS");
        double result = amount*currency;
        System.out.println(result+ "("+ amount+"*"+currency+")");
        System.out.println("Thanks for using our currency convertor");
    }
}

