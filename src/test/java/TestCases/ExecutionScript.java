package TestCases;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import org.openqa.selenium.WebDriver;
import Reusables.Reusablemethods;
import Utilities.ExcelConnections;

import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ObjectProperties.PublicVariables;
import Reusables.Genericmethods;

public class ExecutionScript {

	public WebDriver dr;
	public PublicVariables pv;
	
	@SuppressWarnings("deprecation")
	
	@AfterTest
	public void AfterTest() throws InterruptedException, IOException {
		Genericmethods.killExcel();
	}

	@Test(dataProvider ="TestIterations")
	public void Verify_executionTests(int initItraration, int intRow) throws IOException, InterruptedException {
		Reusablemethods.initiation(intRow);
		Reusablemethods.gmailAccountCreation();
		System.out.println(ExcelConnections.getFieldValue("firstName"));
		ExcelConnections.writeToExcel("Status", "Pass");
	
	}
	
	@DataProvider(name ="TestIterations")
	public Object[][] TestIterations(Method result) throws IOException{
		
		PublicVariables.gTestName = result.getName();
		List<Integer> testData = null;
		ExcelConnections excel = new ExcelConnections();
		testData = excel.findRows(PublicVariables.gTestName);
		Object[][] DatatobeReturned = new Object[testData.size()][2];
		int i=0;
		for(int j=0;j<testData.size();j++) {
			DatatobeReturned[j][0] =i+1;
			DatatobeReturned[j][1]=testData.get(j);
			i=i+1;
		}
		return DatatobeReturned;
	}
}
