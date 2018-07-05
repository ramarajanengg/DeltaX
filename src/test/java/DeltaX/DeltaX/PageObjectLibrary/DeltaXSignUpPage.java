package DeltaX.DeltaX.PageObjectLibrary;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DeltaXSignUpPage {

	WebDriver driver;
	
	public DeltaXSignUpPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.NAME, using = "first_name")
	private WebElement txtFirstName;
	
	@FindBy(how = How.NAME, using = "last_name")
	private WebElement txtLastName;
	
	@FindBy(how = How.NAME, using = "department")
	private WebElement dropDownDept;
	
	@FindBy(how = How.NAME, using = "user_name")
	private WebElement txtUserName;
	
	@FindBy(how = How.NAME, using = "user_password")
	private WebElement txtPassword;
	
	@FindBy(how = How.NAME, using = "confirm_password")
	private WebElement txtConfirmPassword;
	
	@FindBy(how = How.NAME, using = "email")
	private WebElement txtEmail;
	
	@FindBy(how = How.NAME, using = "contact_no")
	private WebElement txtContactNo;
	
	@FindBy(how = How.CSS, using = ".btn.btn-warning")
	private WebElement btnSubmit;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='contact_form']/fieldset/legend/center/h2/b")
	private WebElement txtThanks;

	public void setTxtFirstName(String firstName) {
		txtFirstName.sendKeys(firstName);
	}

	public void setTxtLastName(String lastName) {
		txtLastName.sendKeys(lastName);;
	}

	public void setDropDownDept(int deptIndex) {
		
		Select selectDept = new Select(dropDownDept);
		selectDept.selectByIndex(deptIndex);
	}
	
	public void setDropDownDept(String deptName) {
		
		Select selectDept = new Select(dropDownDept);
		selectDept.selectByValue(deptName);
	}

	public void setTxtUserName(String userName) {
		txtUserName.sendKeys(userName);
	}

	public void setTxtPassword(String password) {
		txtPassword.sendKeys(password);;
	}

	public void setTxtConfirmPassword(String confirmPassword) {
		txtConfirmPassword.sendKeys(confirmPassword);;
	}

	public void setTxtEmail(String email) {
		txtEmail.sendKeys(email);
	}

	public void setTxtContactNo(String contactNo) {
		txtContactNo.sendKeys(contactNo);
	}
	
	public void clickSubmit()
	{
		try
		{
			btnSubmit.click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		catch(Exception e)
		{
			System.out.println("Submit Button is disabled.");
		}
	}
	
	public boolean verifyThanks()
	{
		String thanks = txtThanks.getText();
		if(thanks.equals("Thanks"))
		{
			System.out.println("Sign Up is successful!");
			return true;
		}
		return false;
	}
	
	public boolean verifyError(int index)
	{
		String errXpath = ".//*[@id='contact_form']/fieldset/div["+index+"]/div/small[1]";
		System.out.println("XPATH of Error is: "+errXpath);
		
		if(index == 7)
		{
			errXpath = ".//*[@id='contact_form']/fieldset/div["+index+"]/div/small[2]";
		}
		
		if(driver.findElement(By.xpath(errXpath)).getText().equals("This value is not valid"))
		{
			//Pass
			System.out.println(errXpath);
			System.out.println("PASS: Error message is displayed."+ driver.findElement(By.xpath(errXpath)).getText());
			return true;
		}
		else
		{
			//Fail
			System.out.println("FAIL: Expected Error message is NOT displayed.");
			return false;
		}
	}
}
