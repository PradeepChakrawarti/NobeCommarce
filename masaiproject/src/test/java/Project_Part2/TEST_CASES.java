package Project_Part2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

@Test
public class TEST_CASES {

    WebDriver driver;
    ExtentReports report;
    ExtentTest test;

    @BeforeClass
    public void reportSetUp() {
        report = new ExtentReports(System.getProperty("user.dir") + "\\ProjectReport.html"); // this is the name of my // report file

        test = report.startTest("Setup is done for test");
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        System.setProperty("webdriver.chrome.driver","E:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/");


        driver.manage().window().maximize();
        
        test.log(LogStatus.PASS, "Browser opened successfully");
        SoftAssert asert = new SoftAssert();
        
        String ExpectedResult = "Open details";
        String ActualResult = driver.getTitle();
        
        asert.assertAll();
        
        asert.assertEquals(ExpectedResult, ActualResult);
        test.log(LogStatus.PASS, "Url is valid");
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
         
    }	
    @Test
    public void ShowProductDetails() throws InterruptedException, IOException {
      
        Actions Hover = new Actions(driver);
        WebElement computer = driver.findElement(By.xpath("//a[@href='/computers']"));
        Hover.moveToElement(computer).build().perform();
        test.log(LogStatus.PASS, "Mouse Hover");
 
        WebElement DesktopSubMenu = driver.findElement(By.xpath("//a[@href='/desktops']"));
        DesktopSubMenu.click();
//      Thread.sleep(2000);
        test.log(LogStatus.PASS, "Desktop click");
           
        WebElement ImageDetails = driver.findElement(By.xpath("//img[@alt='Picture of Build your own computer'][1]"));
        ImageDetails.click();
        test.log(LogStatus.PASS, "show detail");
    
//        _____________________________________________
     
    	  
        WebElement selDropDown = driver.findElement(By.id("product_attribute_1"));      
        Select selectProcessor =new Select(selDropDown);  
        selectProcessor.selectByVisibleText("2.2 GHz Intel Pentium Dual-Core E2200");
        test.log(LogStatus.PASS, "Selected");
        
      
        WebElement DropDown2 = driver.findElement(By.id("product_attribute_2"))  ;
        Select selectRam = new Select(DropDown2);      
        selectRam.selectByVisibleText("4GB [+$20.00]");   
        test.log(LogStatus.PASS, "Selected ram");
//      Thread.sleep(2000);
        
//       Ram selection
        WebElement RadioBtn = driver.findElement(By.id("product_attribute_3_7"));
        RadioBtn.click();
//        Thread.sleep(2000);
        test.log(LogStatus.PASS, "Radio button click");
        
//        Os selection  
        WebElement Os = driver.findElement(By.id("product_attribute_3_7"));
        Os.click();
        Thread.sleep(2000);
        test.log(LogStatus.PASS, "Os selected");
   //   Software check boxes
        
        JavascriptExecutor js = (JavascriptExecutor) driver;
   		js.executeScript("window.scrollBy(0,350)", "");
   		
        List<WebElement> software = driver.findElements(By.xpath("//input[@type='checkbox']"));
        for (WebElement clickSoftware : software) {
      	  clickSoftware.click();
      	  Thread.sleep(2000);
      	  
      	  test.log(LogStatus.PASS, "click check box");
   	 }

        
        
       //  Add to cart button
        WebElement cart = driver.findElement(By.id("add-to-cart-button-1"));
        cart.click();
        test.log(LogStatus.PASS, "Clicked cart page");
        Thread.sleep(2000);
        

        driver.get("https://demo.nopcommerce.com/cart");
        TakesScreenshot CartPage = (TakesScreenshot)driver;

        File file = CartPage.getScreenshotAs(OutputType.FILE);

        File file2 = new File("D:\\SDET 101.CartPage.png");

        FileUtils.copyFile(file, file2);

        Thread.sleep(2000);

        test.log(LogStatus.PASS, "Take Screen shot");
       		
//        Executor for scroll
        
       JavascriptExecutor scroll = (JavascriptExecutor)driver;
       scroll.executeScript("window.scroll(0,400)", "");
    //  Checkout gift wrapping
      WebElement GiftWrapping = driver.findElement(By.id("checkout_attribute_1"))  ;
      Select selectGift = new Select(GiftWrapping);
      selectGift.selectByVisibleText("Yes [+$10.00]");
      test.log(LogStatus.PASS, "Gift wraping");
      Thread.sleep(2000);
      
    //  Discount code
      WebElement Discount = driver.findElement(By.id("discountcouponcode"));
      Discount.sendKeys("masai@30");
      
    //  click on discount
      WebElement ClickOnDiscountBtn = driver.findElement(By.id("applydiscountcouponcode"));
      ClickOnDiscountBtn.click();
      test.log(LogStatus.PASS, "Enterd discount code successfully");
    //  Enter on gift cart
      
      WebElement GifCartCouponCode = driver.findElement(By.id("giftcardcouponcode"));
      GifCartCouponCode.sendKeys("console.log");
      driver.findElement(By.id("applygiftcardcouponcode")).click();
      test.log(LogStatus.PASS, "Coupon applied Successfully");
      
    //  Continue shopping
      WebElement ContinueShopping = driver.findElement(By.className("continue-shopping-button"));
      ContinueShopping.click();
      test.log(LogStatus.PASS, "Continue shopping");
      Thread.sleep(2000);
      
    //  Scrolling down
      
      JavascriptExecutor js2 = (JavascriptExecutor) driver;
    		js2.executeScript("window.scrollBy(0,500)", "");
      Thread.sleep(3000);
 }
    

    @AfterMethod(alwaysRun = true)
    public void TearDown() throws InterruptedException {

          driver.close();   
    }
    
    @AfterClass
    public void ends() {
    	
    	 report.endTest(test);

         report.flush();
    }
}
