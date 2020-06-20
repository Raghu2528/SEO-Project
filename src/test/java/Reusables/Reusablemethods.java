package Reusables;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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
				String email = lname+"_"+fname+"_"+value;
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

public static void main(String args[]) {
	Random rand = new Random(); 
	int value = rand.nextInt(1000); 
	int random =ThreadLocalRandom.current().nextInt();
	System.out.println(random);
	System.out.println(value);
}

}
