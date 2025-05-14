package salesforce_rest_executor;

import org.json.JSONObject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SalesforceAuth {
    public static String getAccessToken(String username, String password, String clientId, String clientSecret, String loginUrl) throws IOException, InterruptedException {

        String params = "grant_type=password"
                + "&client_id="+clientId
                + "&client_secret="+clientSecret
                + "&username="+username
                + "&password="+password;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(loginUrl + "/services/oauth2/token"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(params))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        
        String getAccessToken = null;
        
        try {
                JSONObject jsonObject = new JSONObject(response.body());
                if(jsonObject.has("access_token")){
                    getAccessToken = jsonObject.getString("access_token");
                }
                else {
                    System.out.println("Key 'access_token' not found in the JSON.");
                }
        }
        catch (Exception e){
            System.err.println("Error parsing JSON: " + e.getMessage());
        }

        return getAccessToken;
    }
}
