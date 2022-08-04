package pages;

import base.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.IOException;

public class LoginTest extends ConfigReader {
    WebDriver driver;

    private WebDriver getDriver() {
        return new ChromeDriver();
    }
    @BeforeMethod
    public  void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = getDriver();
        driver.manage().window().maximize();

        //Navigate to login page from ConfigReader
        driver.get(geturl());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }


    @Test(priority = 1)
    public void login () throws IOException, InterruptedException {
        //Send correct email to email box
        driver.findElement(By.id("email")).sendKeys(getEmail());
        //Send correct password to password box
        driver.findElement(By.id("password")).sendKeys(getPassword());

        //Click login button
        driver.findElement(By.id("logIn")).click();
        //Wait for page to load
        Thread.sleep(1000);

        //validate sign in by checking for name on home page
        System.out.println(driver.findElement(By.xpath("//a[contains(text(), 'Elias Arlington Park - 22')]")).getText());
    }

    @Test(priority = 2)
    public void loginInvalidEmail() throws IOException, InterruptedException {
        //Send incorrect email to email box
        driver.findElement(By.id("email")).sendKeys(getInvalidEmail());
        //Send correct password to password box
        driver.findElement(By.id("password")).sendKeys(getPassword());

        //Click login
        driver.findElement(By.id("logIn")).click();
        //wait for error message
        Thread.sleep(1000);

        //validate invalid credentials popup
        System.out.println(driver.findElement(By.xpath("//p[@data-qa-id='error-display']")).getText());
    }

    @Test(priority = 3)
    public void loginInvalidPassword() throws IOException, InterruptedException {
        //Send correct email to email box
        driver.findElement(By.id("email")).sendKeys(getEmail());
        //Send incorrect password to password box
        driver.findElement(By.id("password")).sendKeys(getInvalidPassword());

        //Click login
        driver.findElement(By.id("logIn")).click();
        //wait for error message
        Thread.sleep(1000);

        //validate invalid credentials popup
        System.out.println(driver.findElement(By.xpath("//p[@data-qa-id='error-display']")).getText());
    }

    @Test(priority = 4)
    public void helpSignIn() throws InterruptedException, IOException {
        //Click on "Need help?"
        driver.findElement(By.xpath("//a[@data-qa-id='need-help-link']")).click();
        //Click on email and fill with correct email
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[@data-qa-id='password-reset-input']")).sendKeys(getEmail());
        //Click on "Send Password Reset"
        driver.findElement(By.xpath("//button[@data-qa-id='password-reset-submit-btn']")).click();
        Thread.sleep(1000);

        //Get text and print to screen for now
        System.out.println(driver.findElement(By.xpath("//h3[contains(text(), 'Check Your Email')]")).getText());
    }

    @Test(priority = 5)
    public void helpSignInInvalidEmail() throws InterruptedException, IOException {
        //click on "Need help?"
        driver.findElement(By.xpath("//a[@data-qa-id='need-help-link']")).click();
        //wait for page to load
        Thread.sleep(1000);

        //Click on email and fill with incorrect email
        driver.findElement(By.xpath("//input[@data-qa-id='password-reset-input']")).sendKeys(getInvalidEmail());
        //click on "Send Password Reset"
        driver.findElement(By.xpath("//button[@data-qa-id='password-reset-submit-btn']")).click();
        //wait for page to load
        Thread.sleep(1000);

        //get invalid text email
        System.out.println(driver.findElement(By.xpath("//p[@data-qa-id='password-reset-error-display']")).getText());
    }

    @Test(priority = 6)
    public void loginWithOrg() {
        //click on "Login with an Organization"
        driver.findElement(By.xpath("//button[@data-qa-id='log-in-with-organization-btn']")).click();
        //get text to verify landing on new page for now
        System.out.println(driver.findElement(By.xpath("//h2[contains(text(), 'Log into Hudl with your Organization')]")).getText());

        //test back button
        driver.findElement(By.xpath("//button[@data-qa-id='go-back']")).click();
    }

    @Test(priority = 7)
    public void rememberMeBox() {
        //click on "Remember me" box
        driver.findElement(By.xpath("//label[@data-qa-id='remember-me-checkbox-label']")).click();
    }
}
