package Reusables;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;

import ObjectProperties.ObjectProperties;
import ObjectProperties.PublicVariables;
import ObjectProperties.ShawObjectProperties;
import Utilities.ExcelConnections;

public class ShawResuables {

	
	public static void initiation(int intRow) throws InterruptedException, IOException {
		Thread.sleep(1000);
		Genericmethods.killExcel();
		PublicVariables.pRow = intRow;
		PublicVariables.hmTestData =ExcelConnections.GetTestData(intRow);
		System.out.println(ExcelConnections.getFieldValue("firstName"));
	}

	// Open gmail url
		public static void openShawUrl(String url) {
			try {
				//String url ="https://accounts.google.com/signup/v2/webcreateaccount?service=mail&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F%3Fpc%3Dtopnav-about-n-en&flowName=GlifWebSignIn&flowEntry=SignUp";
				Thread.sleep(1000);
				Genericmethods.browserSetup("CHROME");
				Genericmethods.navigateTo(url);
				System.out.println("URL enterd succesfully");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Not able to enter URL enterd succesfully");
			}
			
		}
		
		public static void creteaccount(String fullName,String gender,String goalsValue) throws Exception {
			Thread.sleep(1000);
			String mobl =ExcelConnections.getFieldValue("Mobile");
			//int mobile = Integer.valueOf(mobl);
			Genericmethods.enterText(ShawObjectProperties.fullname,fullName);
			Genericmethods.enterText(ShawObjectProperties.email,ExcelConnections.getFieldValue("Email"));
			Genericmethods.enterText(ShawObjectProperties.mobile,mobl);
			Genericmethods.click(ShawObjectProperties.contbutton);
            Thread.sleep(2000);			
            Genericmethods.enterText(ShawObjectProperties.pwd, "password");
            if(gender.contains("Male")) {
            PublicVariables.dr.findElement(By.xpath("//span[@id='male']")).click();
            }else if(gender.contains("Female")) {
            	  PublicVariables.dr.findElement(By.xpath("//span[@id='female']")).click();
            }
            
            Genericmethods.selectDropdownValue(ShawObjectProperties.goalsDropDown, goalsValue);
            
            
			
			
		}

}
