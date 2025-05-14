package salesforce_rest_executor;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws  IOException, InterruptedException {
        String username = "********@agentforce.com";
        String securityToken = "******";
        String password = "********";
        String clientId = "********";
        String clientSecret = "*********";
        String loginUrl = "https://login.salesforce.com";

        String accessToken = SalesforceAuth.getAccessToken(username, password+securityToken, clientId, clientSecret, loginUrl);
        System.out.println("The access Token is: "+accessToken);
        String instanceUrl = "https://orgfarm-a7dc5bf45b-dev-ed.develop.my.salesforce.com";
        RESTExecutor.runApex(instanceUrl, accessToken);

    }
}
