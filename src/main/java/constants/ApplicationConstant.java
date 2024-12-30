package constants;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class ApplicationConstant {


    public static String SF_APPLICATION_URL = "https://mtxgroup49-dev-ed.develop.my.salesforce.com/";

//    public static String SF_USERNAME = "vikaskumar+developer@mtxb2b.com.dev";
//    public static String SF_PASSWORD = "Tester@12345";

    public static Map<Long, WebDriver> driverMap = new HashMap<>();
    public static Map<String, String> globalDataMap = new HashMap<>(); 

}
