package Reusables;

import java.io.IOException;

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

	// Intiation exection
		public static void gmailAccountCreation() {
			try {
				String url ="https://accounts.google.com/signup/v2/webcreateaccount?service=mail&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F%3Fpc%3Dtopnav-about-n-en&flowName=GlifWebSignIn&flowEntry=SignUp";
				Thread.sleep(1000);
				Genericmethods.browserSetup("CHROME");
				Genericmethods.navigateTo(url);
				Thread.sleep(3000);
				Genericmethods.enterText(ObjectProperties.firstName, "firstname");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

		}
	
}
