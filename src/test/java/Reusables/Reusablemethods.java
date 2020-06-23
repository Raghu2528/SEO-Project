package Reusables;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;

import ObjectProperties.ObjectProperties;
import ObjectProperties.PublicVariables;
import Utilities.ExcelConnections;

public class Reusablemethods {
	
	public static void initiation(int intRow) throws InterruptedException, IOException {
		Thread.sleep(1000);
		Genericmethods.killExcel();
		PublicVariables.pRow = intRow;
		PublicVariables.hmTestData =ExcelConnections.GetTestData(intRow);
		System.out.println(ExcelConnections.getFieldValue("firstName"));
	}

	// Open gmail url
		public static void openGmailUrl(String url) {
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
	
		
	//Gmail account creation
		
		public static void gmailAccountCreation(String fname,String lname) {
			try {
				Random rand = new Random(); 
				int value = rand.nextInt(1000); 
				Thread.sleep(3000);
				String pass = fname+"@091234";
				int random =ThreadLocalRandom.current().nextInt();
				String email = lname+fname+value+value;
				Genericmethods.enterText(ObjectProperties.firstName, fname);
				Genericmethods.enterText(ObjectProperties.lastName, lname);
				Genericmethods.enterText(ObjectProperties.emailId, email);
				Genericmethods.enterText(ObjectProperties.password, pass);
				Genericmethods.enterText(ObjectProperties.confirmPassword, pass);
				Genericmethods.click(ObjectProperties.nextBtn);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		//Yandex account creation
		
				public static void yandexAccountCreation(String fname,String lname) {
					try {
					//	openGmailUrl("https://mail.yandex.com/?noretpath=1");
						Random rand = new Random(); 
						int value = rand.nextInt(1000); 
						Thread.sleep(3000);
						String pass = fname+"@091234";
						int random =ThreadLocalRandom.current().nextInt();
						String email = lname+fname+value;
						//Genericmethods.click(ObjectProperties.createAccountbtn);
						Thread.sleep(5000);
						Genericmethods.enterText(ObjectProperties.yfirstName, fname);
						Genericmethods.enterText(ObjectProperties.ylastName, lname);
						Genericmethods.enterText(ObjectProperties.yusername, email);
						Genericmethods.enterText(ObjectProperties.ypassword, "Test@7890");
						Genericmethods.enterText(ObjectProperties.yconfirmPassword, "Test@7890");
						Genericmethods.click(ObjectProperties.ynophone);
						Genericmethods.enterText(ObjectProperties.yhintanswer, "singers");
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
		
				//tumblr account creation
				
				public static void tumblrAccountCreation(String fname,String lname) {
					try {
						openGmailUrl("https://www.tumblr.com/");
						Random rand = new Random(); 
						int value = rand.nextInt(1000); 
						Thread.sleep(3000);
						String pass = fname+"@091234";
						int random =ThreadLocalRandom.current().nextInt();
						String email = lname+fname+value+value;
						Genericmethods.click(ObjectProperties.createAccountbtn);
						Thread.sleep(3000);
						Genericmethods.enterText(ObjectProperties.yfirstName, fname);
						Genericmethods.enterText(ObjectProperties.ylastName, lname);
						Genericmethods.enterText(ObjectProperties.yusername, email);
						Genericmethods.enterText(ObjectProperties.ypassword, "Test@7890");
						Genericmethods.enterText(ObjectProperties.yconfirmPassword, "Test@7890");
						Genericmethods.click(ObjectProperties.ynophone);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				
		//Verify Yandex account
				
				public static void verifyYandexAccount(String email, String pass) {
					try {
						System.out.println("Verifying yandex account");
						openGmailUrl("https://passport.yandex.com/auth?from=mail&origin=hostroot_homer_auth_com&retpath=https%3A%2F%2Fmail.yandex.com%2F&backpath=https%3A%2F%2Fmail.yandex.com%3Fnoretpath%3D1");
						Genericmethods.enterText(ObjectProperties.yandexemail, email);
						Genericmethods.click(ObjectProperties.yloginbtn);
						Thread.sleep(1000);
						Genericmethods.enterText(ObjectProperties.yandexpass, pass);
						Thread.sleep(1000);
						Genericmethods.click(ObjectProperties.yloginbtn);
						Thread.sleep(20000);
						Genericmethods.click(ObjectProperties.ynotnowbtn);
						Thread.sleep(20000);
						if(PublicVariables.dr.findElements(By.xpath(ObjectProperties.ypopupcheck)).size()>=1) {
							Genericmethods.click(ObjectProperties.ypopuclose);
						}else {
							System.out.println("Their is no popup");
						}
						//veriying text after login
						
						Genericmethods.verifyValue(Genericmethods.getText(ObjectProperties.ycomposetext), "Compose");
						System.out.println("YANDEX ACCOUNT VERIFIED SUCCESFULLY  FOR ::::"+email);
						Thread.sleep(1000);
						PublicVariables.dr.close();
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				
				
		
}
