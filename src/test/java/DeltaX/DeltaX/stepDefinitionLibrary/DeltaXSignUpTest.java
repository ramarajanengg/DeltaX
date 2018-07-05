package DeltaX.DeltaX.stepDefinitionLibrary;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import DeltaX.DeltaX.PageObjectLibrary.DeltaXSignUpPage;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DeltaXSignUpTest {
	public static WebDriver driver;
	
	DeltaXSignUpPage SignUpPage;
	
	Calendar cal = Calendar.getInstance();
	
	int year = cal.get(Calendar.YEAR);
	int month = cal.get(Calendar.MONTH);
	int day = cal.get(Calendar.DAY_OF_MONTH);
	int minute = cal.get(Calendar.MINUTE);
    int second = cal.get(Calendar.SECOND);
	
	String user = Integer.toString(year)+Integer.toString(month)+Integer.toString(day)+Integer.toString(minute)+Integer.toString(second);
	
	
	@Before
    public void beforeScenario(Scenario scenario) {
    	
    	System.setProperty("webdriver.chrome.driver", "Resources/Driver Files/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
    }
	
	@After
    public void afterScenario(Scenario scenario) {
    	
    	driver.quit();
    }
	
	@Given("^I am in Registration Form$")
	public void i_am_in_Registration_Form() throws Throwable {
	    
	    //Navigate to Registration Form
		String URL = "http://bit.ly/dxqatest-online";
	    driver.get(URL);
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    
	    String FormName = driver.findElement(By.xpath(".//*[@id='contact_form']/fieldset/legend/center/h2/b")).getText();

		System.out.println(FormName);
		if(FormName.equals("Registration Form"))
		{
			System.out.println("Registration Form is displayed.");
		}
		else
		{
			System.out.println("Registration Form did not displayed.");
		}
	}

	@When("^I enter Name, Dept, Username, Password, Email and Contact Number$")
	public void i_enter_Name_Dept_Usernamwe_Password_Email_and_Contact_Number() throws Throwable {
		SignUpPage = new DeltaXSignUpPage(driver);
		String firstName = user;
		String lastName = user;
		String contactno = user;
		
		SignUpPage.setTxtFirstName(firstName);
		SignUpPage.setTxtLastName(lastName);
		SignUpPage.setDropDownDept(1);
		SignUpPage.setTxtUserName(firstName+lastName);
		SignUpPage.setTxtPassword(firstName+lastName);
		SignUpPage.setTxtConfirmPassword(firstName+lastName);
		SignUpPage.setTxtEmail(firstName+"@gmail.com");
		SignUpPage.setTxtContactNo(contactno);
		
	}

	@When("^Click on Submit$")
	public void click_on_Submit() throws Throwable {
		SignUpPage = new DeltaXSignUpPage(driver);
		SignUpPage.clickSubmit();
	}

	@Then("^Sign Up should be successful$")
	public void sign_Up_should_be_successful() throws Throwable {
		SignUpPage = new DeltaXSignUpPage(driver);
		SignUpPage.verifyThanks();
	}
	
	@Given("^the user is already registered$")
	public void the_user_is_already_registered() throws Throwable {
	    
	}

	@When("^I try to register again$")
	public void i_try_to_register_again() throws Throwable {
		i_am_in_Registration_Form();
		i_enter_Name_Dept_Usernamwe_Password_Email_and_Contact_Number();
		click_on_Submit();
	}

	@Then("^Sign Up should not be successful$")
	public void sign_Up_should_not_be_successful() throws Throwable {
	    System.out.println("Sign up should not be successful");
	    SignUpPage = new DeltaXSignUpPage(driver);
		
		assertFalse(SignUpPage.verifyThanks());
	}
	
	@When("^I enter only required fields$")
	public void i_enter_only_required_fields() throws Throwable {
		SignUpPage = new DeltaXSignUpPage(driver);
		String firstName = user;
		String lastName = user;
		
		//Required Fields
		SignUpPage.setTxtFirstName(firstName);
		SignUpPage.setTxtLastName(lastName);
		SignUpPage.setTxtUserName(firstName+lastName);
		SignUpPage.setTxtPassword(firstName+lastName);
		SignUpPage.setTxtConfirmPassword(firstName+lastName);
		SignUpPage.setTxtEmail(firstName+"@gmail.com");
	}
	
	@When("^Invalid or bad data entered on fields$")
	public void invalid_or_bad_data_entered_on_fields() throws Throwable {
		SignUpPage = new DeltaXSignUpPage(driver);
		String firstName = "f";
		String lastName = "l";
		
		SignUpPage.setTxtFirstName(firstName);
		//Verify Error
		assertTrue(SignUpPage.verifyError(1));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		SignUpPage.setTxtLastName(lastName);
		//Verify Error
		assertTrue(SignUpPage.verifyError(2));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		SignUpPage.setTxtUserName(firstName+lastName);
		//Verify Error
		assertTrue(SignUpPage.verifyError(4));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		SignUpPage.setTxtPassword(firstName+lastName);
		//Verify Error
		assertTrue(SignUpPage.verifyError(5));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		SignUpPage.setTxtConfirmPassword(firstName+lastName);
		//Verify Error
		assertTrue(SignUpPage.verifyError(6));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		SignUpPage.setTxtEmail(firstName);
		//Verify Error
		assertTrue(SignUpPage.verifyError(7));
		
			System.out.println("ERROR: Assertion Failed.");
		
	}
	
	@When("^Password and Confirm Password do not match$")
	public void password_and_Confirm_Password_do_not_match() throws Throwable {
		SignUpPage = new DeltaXSignUpPage(driver);
		String firstName = user;
		String lastName = user;
		String contactno = user;
		
		SignUpPage.setTxtFirstName(firstName);
		SignUpPage.setTxtLastName(lastName);
		SignUpPage.setDropDownDept(2);
		SignUpPage.setTxtUserName(firstName+lastName);
		SignUpPage.setTxtPassword(firstName+lastName);
		SignUpPage.setTxtConfirmPassword(firstName+lastName+contactno);
		SignUpPage.setTxtEmail(firstName+"@gmail.com");
		SignUpPage.setTxtContactNo(contactno);
	}
}
