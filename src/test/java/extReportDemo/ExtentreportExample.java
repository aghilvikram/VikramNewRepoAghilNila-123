package extReportDemo;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentreportExample {
	public static void main(String[] args) throws Exception {
		ExtentReports extent=new ExtentReports();
		File file=new File(System.getProperty("user.dir")+"\\ExtendtReports\\eReport.html");
		ExtentSparkReporter sparkReporter=new ExtentSparkReporter(file);
		extent.attachReporter(sparkReporter);
		
		String xmlContet="<note>\r\n" + 
				"<to>Tove</to>\r\n" + 
				"<from>Jani</from>\r\n" + 
				"<heading>Reminder</heading>\r\n" + 
				"<body>Don't forget me this weekend!</body>\r\n" + 
				"</note>";
		String jsonContent="{\r\n" + 
				"    \"glossary\": {\r\n" + 
				"        \"title\": \"example glossary\",\r\n" + 
				"		\"GlossDiv\": {\r\n" + 
				"            \"title\": \"S\",\r\n" + 
				"			\"GlossList\": {\r\n" + 
				"                \"GlossEntry\": {\r\n" + 
				"                    \"ID\": \"SGML\",\r\n" + 
				"					\"SortAs\": \"SGML\",\r\n" + 
				"					\"GlossTerm\": \"Standard Generalized Markup Language\",\r\n" + 
				"					\"Acronym\": \"SGML\",\r\n" + 
				"					\"Abbrev\": \"ISO 8879:1986\",\r\n" + 
				"					\"GlossDef\": {\r\n" + 
				"                        \"para\": \"A meta-markup language, used to create markup languages such as DocBook.\",\r\n" + 
				"						\"GlossSeeAlso\": [\"GML\", \"XML\"]\r\n" + 
				"                    },\r\n" + 
				"					\"GlossSee\": \"markup\"\r\n" + 
				"                }\r\n" + 
				"            }\r\n" + 
				"        }\r\n" + 
				"    }\r\n" + 
				"}";
		//eTest1.pass("TestOne passed");
		ExtentTest eTest1 = extent.createTest("TestOne");
		eTest1.log(Status.INFO,MarkupHelper.createCodeBlock(xmlContet,CodeLanguage.XML) );
		eTest1.log(Status.INFO, MarkupHelper.createCodeBlock(jsonContent,CodeLanguage.JSON));
		//eTest1.log(Status.PASS, "TestOne passed");
		
		//----------------------------------------------------------------------------
		ExtentTest eTest2 = extent.createTest("TestTwo");
		//eTest2.fail("TestTwo failed");
		eTest2.log(Status.FAIL, "<i>TestTwo failed</i>");
		ExtentTest eTest3 = extent.createTest("TestThree");
		//eTest3.skip("TestThree skipped");
		//----------------------------------------------------------------------------
		eTest3.log(Status.WARNING, "<u>Test will get skipped</u>");
		//eTest3.log(Status.SKIP,"TestThree skipped");
		//eTest3.log(Status.FAIL, "Test3 Failed");
		eTest3.log(Status.PASS, "Test3 passed");
		//----------------------------------------------------------------------------
		ArrayList<String> alist1=new ArrayList<String>();
		alist1.add("vikram");
		alist1.add("Devi");
		alist1.add("Aghil Nila");
		ExtentTest alistTest1 = extent.createTest("ListTest1");
		alistTest1.log(Status.INFO, MarkupHelper.createOrderedList(alist1));
		//----------------------------------------------------------------------------
		ArrayList<String> alist2=new ArrayList<String>();
		alist2.add("vikram");
		alist2.add("Devi");
		alist2.add("Aghil Nila");
		ExtentTest alistTest2 = extent.createTest("ListTest2");
		alistTest2.log(Status.INFO, MarkupHelper.createUnorderedList(alist2));
		
		HashMap<String,Object> hmap=new HashMap<String,Object>();
		hmap.put("FirstName", "Vikram");
		hmap.put("Last Name", "Balasubramanian");
		hmap.put("age", 32);
		ExtentTest hmapTest = extent.createTest("HasMapTest1");
		hmapTest.log(Status.INFO,MarkupHelper.createOrderedList(hmap));
		
		//------------------------------------------------------------------------------
		ExtentTest highlightTest = extent.createTest("VIKRAM");
		highlightTest.log(Status.INFO, MarkupHelper.createLabel("Vikram Balasubramanian", ExtentColor.GREEN));
		
		//------------------------------------------------------------------------------
		ExtentTest exceptionTest = extent.createTest("ExceptionDetails");
		try {
			int a=9/0;
			
		}catch(Exception e) {
			e.printStackTrace();
			exceptionTest.fail(e);
		}
		//------------------------------------------------------------------------------
		
		ExtentTest descriptionTest = extent.createTest("DescriptionOfTest","This test is describing enabling description in extent report");
		descriptionTest.log(Status.INFO, "Description success");
		
		extent.flush();
		Desktop.getDesktop().browse(file.toURI());
	}

}
