package util;

import org.openqa.selenium.WebElement;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebUtil {
    private static String userDirectory = System.getProperty("user.dir");

    public static void typeIntoTextField(WebElement element, String text) {
        element.sendKeys(text);
    }

    public static int randomNumGenerator() {
        Random r = new Random(System.currentTimeMillis());
        return 10000 + r.nextInt(20000);
    }

    public static String getProperty(String key) throws IOException {
        String path = "/src/test/resources/configs/Configuration.properties" ;

        String propertyFilePath = userDirectory + path;

        BufferedReader reader = new BufferedReader(new FileReader(propertyFilePath));
        Properties properties = new Properties();
        properties.load(reader);

        return properties.getProperty(key);

    }

    public static String getText(WebElement element){

        return element.getText();
    }

    // Helper method to extract the number from the text using regular expression
    public static int extractNumber(String text) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group());
        }
        return 0; // Return 0 if no number is found
    }


}
