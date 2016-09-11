package com.Example.tests;

import java.io.File;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class T1 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
/*	  ProfilesIni prof = new ProfilesIni();				
	  FirefoxProfile ffProfile= prof.getProfile ("myprofile1");
	  ffProfile.setAcceptUntrustedCertificates(true); 
	  ffProfile.setAssumeUntrustedCertificateIssuer(true);*/
    driver = new FirefoxDriver();
/*    baseUrl = "http://www.google.com";
    File file = new File("C:/DIL_Automation/Sel_IDE/IEDriverServer.exe");
    System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
    driver = new InternetExplorerDriver();*/
/*    File file1 = new File("C:/DIL_Automation/Sel_IDE/chromedriver.exe");
    System.setProperty("webdriver.chrome.driver", file1.getAbsolutePath());
    driver = new ChromeDriver();*/
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testT1() throws Exception {
    driver.get("http://www.google.co.in");
    //driver.findElement(By.cssSelector("form")).click();
    // Find the text input element by its name
    WebElement element = driver.findElement(By.name("q"));
    
    scrollToNhighlight(driver,By.name("q"));
    // Enter something to search for
    element.sendKeys("Cheese!");

    // Now submit the form. WebDriver will find the form for us from the element
    element.submit();

    // Check the title of the page
    System.out.println("Page title is: " + driver.getTitle());
    
    // Google's search is rendered dynamically with JavaScript.
    // Wait for the page to load, timeout after 10 seconds
    (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
        public Boolean apply(WebDriver d) {
            return d.getTitle().toLowerCase().startsWith("cheese!");
        }
    });

    // Should see: "cheese! - Google Search"
    System.out.println("Page title is: " + driver.getTitle());


  }
  public static void scrollToNhighlight(WebDriver driver, By by) throws InterruptedException {
	WebElement element = driver.findElement(by);	
	JavascriptExecutor js = (JavascriptExecutor) driver;	
    js.executeScript("arguments[0].scrollIntoView();", element);
    String originalStyle = element.getAttribute("style");
	js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
	Thread.sleep(500);
	js.executeScript("arguments[0].setAttribute('style', '" + originalStyle + "');", element);
	Thread.sleep(500);
	js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);  
	Thread.sleep(500);
	js.executeScript("arguments[0].setAttribute('style', '" + originalStyle + "');", element);
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
