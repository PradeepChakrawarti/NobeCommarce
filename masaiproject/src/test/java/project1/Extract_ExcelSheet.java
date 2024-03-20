package project1;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mongodb.MapReduceCommand.OutputType;

public class Extract_ExcelSheet {
    WebDriver driver;
 
//    This is the data provider for the coded
    
    @DataProvider(name = "getdata")
    public Object[][] getdata() throws EncryptedDocumentException, IOException {

        Data_Provider get = new Data_Provider();
        Object[][] data = get.getdatafromexcel();
        return data;
    }
     
//    This is the setup for System path 
    
    @BeforeMethod(alwaysRun = true)
    public void first() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
//        driver = new ChromeDriver();
        
//        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
        
//        driver.manage().window().maximize();
        driver
        
        
    }

//    This the test case to check register details 
    
    @Test(dataProvider = "getdata")
    public void test(String firstName, String lastName, String email, String companyName, String password, String confirmPassword) throws InterruptedException, IOException {
        
    	WebElement FirstName = driver.findElement(By.id("FirstName"));
      
        WebElement LastName = driver.findElement(By.id("LastName"));
 
        WebElement email1 = driver.findElement(By.id("Email"));
 
        WebElement company = driver.findElement(By.id("Company"));

        WebElement password1 = driver.findElement(By.id("Password"));

        WebElement confirmPassword1 = driver.findElement(By.id("ConfirmPassword"));
        
        
        FirstName.sendKeys(firstName);
        LastName.sendKeys(lastName);
        email1.sendKeys(email);
        company.sendKeys(companyName);
        password1.sendKeys(password);
        confirmPassword1.sendKeys(confirmPassword);
        driver.findElement(By.id("register-button")).click();
        Thread.sleep(3000);
        
        TakesScreenshot Provider = (TakesScreenshot)driver;
        
        File file1 = Provider.getScreenshotAs(OutputType.FILE);
        
        File file2 = new File("D:\\SDET 101\\Selenuim Notes.Provider.png");
        
        FileUtils.copyDirectory(file1, file2);
        
        
    }

    @AfterMethod
    public void last() {
        driver.close();
    }
}
