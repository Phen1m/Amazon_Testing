import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AmazonLoginTest {
    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Users/subhamroy/Downloads/chromedriver-mac-arm64/chromedriver"); 
        WebDriver driver = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();
        driver.get("https://www.amazon.in/");
	Thread.sleep(100);

        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-link-accountList")));
        signInButton.click();

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_email")));
        emailField.sendKeys("technicalsubham2001@gmail.com"); 

        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("continue")));
        continueButton.click();

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_password")));
        passwordField.sendKeys("84207sr35110"); 
        
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("signInSubmit")));
        loginButton.click();

        Thread.sleep(5000);

        if (driver.getCurrentUrl().contains("amazon")) {
            System.out.println("Login Successful!");
        } else {
            System.out.println("Login Failed!");
        }

        driver.get("https://www.amazon.in/gp/cart/view.html?ref_=nav_cart");
        Thread.sleep(3000);

        WebElement proceedToBuyButton = driver.findElement(By.name("proceedToRetailCheckout")); 
        proceedToBuyButton.click();
        Thread.sleep(5000);

        try {
            driver.switchTo().frame(0); 
            System.out.println("Switched to payment iframe.");
        } catch (Exception e) {
            System.out.println("No iframe detected.");
        }

        try {
            WebElement upiOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@value, 'UPI')]")));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", upiOption);
            Thread.sleep(1000);
            upiOption.click();
            System.out.println("Amazon UPI Selected!");

            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Amazon UPI option not found or not clickable.");
        }
        
    }
}