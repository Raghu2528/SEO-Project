package Reusables;

import java.io.IOException;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import ObjectProperties.PublicVariables;

public class Genericmethods {
	public static WebDriver dr;
	public static WebDriverWait wait;
	// kill Excel

	public static void killExcel() throws InterruptedException, IOException {
		Runtime.getRuntime().exec("taskkill /f /t /IM EXCEL.EXE");
		Thread.sleep(1000);
	}

	public static void browserSetup(String strBrowser) {
		switch (strBrowser) {
		case "CHROME":
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\Drivers\\chromedriver1.exe");

			PublicVariables.dr = new ChromeDriver();
			PublicVariables.dr.manage().window().maximize();
			break;
		}
	}

	// Navigate to

	public static void navigateTo(String url) {
		try {
			PublicVariables.dr.get(url);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Entering the text in texfield

	public static void enterText(String xpath, String textToEnter) {
		try {
			wait = new WebDriverWait(PublicVariables.dr, 50);
			PublicVariables.dr.findElement(By.xpath(xpath)).clear();
			PublicVariables.dr.findElement(By.xpath(xpath)).sendKeys(textToEnter);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// selecting the value from dropdown using visiblity by text

		public static void selectDropdownValue(String xpath, String textToSelect) {
			try {
				wait = new WebDriverWait(PublicVariables.dr, 50);
				Select sc = new Select(PublicVariables.dr.findElement(By.xpath(xpath)));
				sc.selectByVisibleText(textToSelect);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		// clicking on the webelement

				public static void click(String xpath) {
					try {
						wait = new WebDriverWait(PublicVariables.dr, 50);
						PublicVariables.dr.findElement(By.xpath(xpath)).click();
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
}
