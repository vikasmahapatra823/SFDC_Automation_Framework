package salesforce_rest_executor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RESTExecutor {
    public static void runApex(String instanceUrl, String accessToken) throws IOException, InterruptedException {

        String jsonBody = """
                {
                  "Name": "ABC Group",
                  "Phone": "1234567890",
                  "Website": "https://www.mtxb2b.com",
                  "Industry": "Technology"
                }""";

        System.out.println(jsonBody);
        String toolingEndpoint = instanceUrl + "/services/data/v62.0/sobjects/Account";
        System.out.println("The tooling end-point is: "+toolingEndpoint);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(toolingEndpoint))
                .header("Authorization", "Bearer " + accessToken)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Response: " + response.body());

    }
}
