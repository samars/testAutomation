package testCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;

public class LoginTest extends TestBase {
	
	
	@Test
	public void loginAsRegsiteredUser() throws InterruptedException {
		log.debug("Login Started");
		WebElement element= driver.findElement(By.xpath(OR.getProperty("store")));
//		WebElement element = driver.findElement(By.xpath("//*[@id='store_selector_main']/div/div/div[3]/div[1]"));
		Actions action = new Actions(driver);
		 
        action.moveToElement(element).build().perform();
 
        driver.findElement(By.xpath(OR.getProperty("storeLink"))).click();
        
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        
		driver.findElement(By.xpath(OR.getProperty("loginLink"))).click();
		
		driver.findElement(By.xpath(OR.getProperty("username"))).sendKeys("topintest@gmail.com");
		
		driver.findElement(By.xpath(OR.getProperty("password"))).sendKeys("test@123");
		
		driver.findElement(By.xpath(OR.getProperty("submit"))).click();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		Thread.sleep(3000);
		
		driver.findElement(By.xpath(OR.getProperty("customericon"))).click();
		
		
		
		Assert.assertEquals(true, driver.findElement(By.xpath(OR.getProperty("myaccount"))).isDisplayed());
		
		log.debug("Login successfully");
		
		
		
		
	}

}
