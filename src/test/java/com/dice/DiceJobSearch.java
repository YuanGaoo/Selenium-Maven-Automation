package com.dice;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import okio.Buffer;

//1)Create arraylist of keywords.
//add 20 different keyworks
//list.add("java");
//
//pass each item to search box and print accordingly.
//modify your arraylist 
//
//java-1234
//
//2) Store all keywords into a text file 
//read the text file and  repeat above steps.
//
//store keyword and results count into an arraylist.
//----
//
//after closing browser.
//print contents of arraylist that was updated each time 
//we looped.
//
//commit > push > share your github link

public class DiceJobSearch {
	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		FileReader fr = new FileReader("Information.txt");
		String url = "https://dice.com";
		String[] keyword;
		String jobtitle;
		String location;
		driver.get(url);
		ArrayList<String> ls = new ArrayList<>();
		ArrayList<String> result = new ArrayList<>();

		BufferedReader bf = new BufferedReader(fr);
		String value;
		while ((value = bf.readLine()) != null) {
			ls.add(value);
			keyword = value.split(",");
			jobtitle = keyword[0];
			location = keyword[1];
			
			driver.findElement(By.id("search-field-keyword")).clear();
			driver.findElement(By.id("search-field-keyword")).sendKeys(jobtitle);

			driver.findElement(By.id("search-field-location")).clear();
			driver.findElement(By.id("search-field-location")).sendKeys(location);

			driver.findElement(By.id("findTechJobs")).click();

			String count = driver.findElement(By.id("posiCountId")).getText();
			int countResult = Integer.parseInt(count.replace(",", ""));
			if (countResult > 0) {
				result.add("Step PASS: Keyword : " + jobtitle+","+location + " search returned " + countResult + " results in "
						+ location);
			} else {
				result.add("Step FAIL: Keyword : " +  jobtitle+","+location + " search returned " + countResult + " results in "
						+ location);
			}
			driver.navigate().back();
			

		}
		driver.close();
		
		for(String allInfo:result) {
			System.out.println(allInfo);
		}
		System.out.println("TEST COMPLETED - : "+ LocalTime.now());
		// System.out.println(ls.get(1));

		// //Set up chrome driver path
		// WebDriverManager.chromedriver().setup();
		// //invoke selenium webdriver
		// WebDriver driver = new ChromeDriver();
		// //fullcreen
		// driver.manage().window().fullscreen();
		// //set universal wait time in case web page is slow
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//
		// /*Step 1. Launch browser and navigate to https://dice.com
		// Expected: dice home page should be displayed
		// */
		// String url = "https://dice.com";
		// driver.get(url);
		//
		// String actualTitle = driver.getTitle();
		// String expectedTitle = "Job Search for Technology Professionals | Dice.com";
		//
		// if(actualTitle.equals(expectedTitle)) {
		// System.out.println("Step PASS. Dice homepage successfully loaded");
		// }else {
		// System.out.println("Step FAIL. Dice homepage did not load successfully");
		// throw new RuntimeException("Step FAIL. Dice homepage did not load
		// successfully");
		// }
		//
		// String keyword ="java developer";
		// driver.findElement(By.id("search-field-keyword")).clear();
		// driver.findElement(By.id("search-field-keyword")).sendKeys(keyword);
		//
		// String location = "GA";
		// driver.findElement(By.id("search-field-location")).clear();
		// driver.findElement(By.id("search-field-location")).sendKeys(location);
		//
		// driver.findElement(By.id("findTechJobs")).click();
		//
		// String count = driver.findElement(By.id("posiCountId")).getText();
		// System.out.println(count);
		// //ensure count is more than 0
		// int countResult = Integer.parseInt(count.replace(",", ""));
		//
		// if(countResult > 0) {
		// System.out.println( "Step PASS: Keyword : " + keyword +" search returned " +
		// countResult +" results in " + location);
		// }else {
		// System.out.println( "Step FAIL: Keyword : " + keyword +" search returned " +
		// countResult +" results in " + location);
		// }
		//
		// //driver.close();
		// System.out.println("TEST COMPLETED - : "+ LocalTime.now());

	}
}
