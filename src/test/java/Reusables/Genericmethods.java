package Reusables;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

import ObjectProperties.PublicVariables;

public class Genericmethods {
	public static WebDriver dr;
	public static WebDriverWait wait;
	// kill Excel

	public static void killExcel() throws InterruptedException, IOException {
		Runtime.getRuntime().exec("taskkill /f /t /IM EXCEL.EXE");
		Thread.sleep(1000);
	}

	@SuppressWarnings("deprecation")
	public static void browserSetup(String strBrowser) {
		switch (strBrowser) {
		case "CHROME":
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\Drivers\\chromedriver1.exe");

			PublicVariables.dr = new ChromeDriver(capabilities);
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
			fluentwait(xpath);
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
				fluentwait(xpath);
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
						fluentwait(xpath);
						wait = new WebDriverWait(PublicVariables.dr, 50);
						PublicVariables.dr.findElement(By.xpath(xpath)).click();
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				
		//verifying text
				public static void verifyValue(String actualValue, String expectedValue) {
					try {
						if(expectedValue.contains(actualValue)) {
							System.out.println(expectedValue+"::: is expected");
						}else {
							System.out.println(expectedValue+":: is not expected");
						}
					} catch (Exception e) {
						System.out.println("Assersion excpetion"+e);
					}
				}
				
				//verifying text
				public static String getText(String xpath) {
					String text =null;
					try {
						fluentwait(xpath);
						text = PublicVariables.dr.findElement(By.xpath(xpath)).getText();
						
					} catch (Exception e) {
						System.out.println("Assersion excpetion"+e);
					}
					return text;
				}
				
	//fluent wait
				public static void fluentwait(final String xpath) {
					Wait<WebDriver> wait = new FluentWait<WebDriver>(PublicVariables.dr)
						       .withTimeout(30, TimeUnit.SECONDS)
						       .pollingEvery(5, TimeUnit.SECONDS)
						       .ignoring(NoSuchElementException.class);

						   WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
						     public WebElement apply(WebDriver driver) {
						       return driver.findElement(By.xpath(xpath));
						     }
						   });
				}
				
		//switch to frame
				public static void switchToFrame(String xpath) {
					try {
						WebElement frameWebelement = PublicVariables.dr.findElement(By.xpath(xpath));
						Thread.sleep(1000);
						PublicVariables.dr.switchTo().frame(frameWebelement);
						PublicVariables.dr.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
						
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
}
