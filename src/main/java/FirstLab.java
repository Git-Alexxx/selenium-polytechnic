import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class FirstLab {
    private WebDriver chromeDriver;
    private static final String baseUrl = "https://www.nmu.org.ua/ua/";

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        //Run driver
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        //set fullscreen
        chromeOptions.addArguments("--start-fullscreen");
        //setup wait for loading elements
        chromeOptions.setImplicitWaitTimeout(Duration.ofSeconds(15));
        this.chromeDriver = new ChromeDriver(chromeOptions);
    }

    @BeforeMethod
    public void preconditions() {
        //open main page
        chromeDriver.get(baseUrl);
    }

    @Test
//    public void testHeaderExists() {
//        WebElement header = chromeDriver.findElement(By.id("header"));
//        Assert.assertNotNull(header);
//    }

//      public void testClickOnStudent() {
//        //find element by xpath
//        WebElement forStudentButton = chromeDriver.findElement(By.xpath("/html/body/center/div[4]/div/div[1]/ul/li[4]/a"));
//        //verification
//        Assert.assertNotNull(forStudentButton);
//        forStudentButton.click();
//        //verification page changed
//        Assert.assertNotNull(chromeDriver.getCurrentUrl(), baseUrl);
//    }

      public void testSlider() {
        //find element by class name
        WebElement nextButton = chromeDriver.findElement(By.className("next"));
        //find element by css selector
        WebElement nextButtonByCss = chromeDriver.findElement(By.cssSelector("a.next"));
        //verification equality
        Assert.assertEquals(nextButton, nextButtonByCss);

        WebElement previousButton = chromeDriver.findElement(By.className("prev"));

        for(int i = 0; i < 2; i++){
            if(nextButton.getAttribute("class").contains("disabled")) {
                previousButton.click();
                Assert.assertTrue(previousButton.getAttribute("class").contains("disabled"));
                Assert.assertFalse(nextButton.getAttribute("class").contains("disabled"));
            }
            else{
                nextButton.click();
                Assert.assertTrue(nextButton.getAttribute("class").contains("disabled"));
                Assert.assertFalse(previousButton.getAttribute("class").contains("disabled"));
            }
        }
    }

//    @AfterClass(alwaysRun = true)
//    public void tearDown() { chromeDriver.quit(); }
}
