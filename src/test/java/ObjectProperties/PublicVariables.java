package ObjectProperties;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;

public class PublicVariables {
	public static WebDriver dr;
	public static String gTestName;
	public static HashMap<String, String> hmTestData =null;
	public static HashMap<String, String> hmOutputData =new HashMap<String, String>();
	public static HashMap<String, String[]> hmInvData =new HashMap<String, String[]>();
	public static String country = null;
	public static int pRow;

}
