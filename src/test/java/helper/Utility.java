package helper;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Utility {
    private static WebDriver driver;

    public static WebDriver getDriver(){
        return driver;
    }

    // method to run driver web, like chrome
    public static void startDriver() {
        //Initiating your chromedriver
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless"); //  running Chrome via console, and UI Chrome not displayed
        options.addArguments("--no-sandbox"); // chrome production version
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*"); // allowing to cross domain, so chrome can remoted by selenium
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize(); //maximize window
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3)); // wait element with time while 3 second
    }

    // method to closed drive
    public static void quitDriver() {
        driver.quit();
    }

    //navigate driver to url page
    public static void openPage(String url) {
        driver.get(url);
    }

    // generate random data with 8 lenght for username and password
    // yg diperbolehkan adakah huruf dan angka -- still error
    public static String generateRandomData() {
        String allowedChars = "abcdefghijklmnopqrstuvwxyz" + "1234567890" + "_-.";
        String data = RandomStringUtils.randomAlphanumeric(8); // Panjang email 10 karakter
        return data;
    }
}
