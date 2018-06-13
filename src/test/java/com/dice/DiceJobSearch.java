package com.dice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		/*
		 Step 1 . launch browser and navigate to http:// dice.com
		 	Expected : dice home page should be displayed
		 */
		WebDriver driver = new ChromeDriver();
		//fullcreen
		// driver.mange().window().fullscreen();
		driver.manage().window().fullscreen();
		// set universal wait time in case web page is slow
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		 /* 
		 * 
		 * */
			String url= "http://dice.com";
			driver.get(url);
			String actual=driver.getTitle();
			String expectedTitle="Job Search for Technology Professionals | Dice.com";
			if(actual.equals(expectedTitle)) {
				System.out.println("Step PASS.Dice homePage successfulluy loaded");
			}else {
				System.out.println("Step FAIL.Dice homePage did not load successfully");
				throw new RuntimeException("Step FAIL.Dice homePage did not load successfully");
			}
					
			String keyWord="java Developer";
			driver.findElement(By.id("search-field-keyword")).clear();;
			driver.findElement(By.id("search-field-keyword")).sendKeys(keyWord);
			String location="22102";
			driver.findElement(By.id("search-field-location")).clear();
			driver.findElement(By.id("search-field-location")).sendKeys(location);
			
			driver.findElement(By.id("findTechJobs")).click();
			String count = driver.findElement(By.id("posiCountMobileId")).getText();
			int countResult= Integer.valueOf(count.replace(",", ""));
			if(countResult > 0) {
				System.out.println( "Step PASS: Keyword : " + keyWord +" search returned " +
				countResult +" results in " + location);
			}else {
				System.out.println( "Step FAIL: Keyword : " + keyWord +" search returned " +
						countResult +" results in " + location);
			}
			
			driver.close();
		

	}

}









