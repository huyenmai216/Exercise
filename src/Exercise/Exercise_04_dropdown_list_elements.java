package Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Exercise_04_dropdown_list_elements {
    WebDriver driver;

    String firstName="automation", lastName="check", email= randomEmail();
    String companyName="HM Company",password="123456";

    String dd="15", mm="February", yyyy="1984";


    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_Default_Dropdown(){
        //truy cập vào trang
        driver.get("https://demo.nopcommerce.com/register");
        //Click register link trên
        driver.findElement(By.xpath("//a[text()='Register']")).click();
        //Input các thông tin hợp lệ vào form
        //với date of birth - kiểm tra giá trị trong các field
        driver.findElement(By.cssSelector("input#gender-female")).click();

        driver.findElement(By.cssSelector("input#FirstName")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#LastName")).sendKeys(lastName);

        Select day = new Select(driver.findElement(By.name("DateOfBirthDay")));
        day.selectByVisibleText(dd);

        //verify dropdown này là single choice
        Assert.assertFalse(day.isMultiple());

        // Verify số lưongj item là 32

        List<WebElement> dayOptions=day.getOptions();
        Assert.assertEquals(dayOptions.size(),32);

        Select month = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        month.selectByVisibleText(mm);
        Select year = new Select(driver.findElement(By.name("DateOfBirthYear")));
        year.selectByVisibleText(yyyy);

        //verify dropdown này là single choice
        Assert.assertFalse(year.isMultiple());

        // Verify số lưongj item là 32

        List<WebElement> yearOptions =year.getOptions();
        Assert.assertEquals(yearOptions.size(),112);

        driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
        driver.findElement(By.cssSelector("input#Company")).sendKeys(companyName);
        driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys(password);

        //Click register button
        driver.findElement(By.cssSelector("button#register-button")).click();
        sleepInSecond(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(),"Your registration completed");

    }

    @Test
    public void TC_02_Check_Account_Info() {
        //truy cập vào trang
        driver.get("https://demo.nopcommerce.com");
        //Click my account trên
        driver.findElement(By.cssSelector("a.ico-account")).click();

        sleepInSecond(3);
        //Input các thông tin hợp lệ vào form
        //với date of birth - kiểm tra giá trị trong các field
        Assert.assertTrue(driver.findElement(By.cssSelector("input#gender-female")).isSelected());

        Assert.assertTrue(driver.findElement(By.cssSelector("input#gender-female")).isSelected());

        Assert.assertEquals(driver.findElement(By.cssSelector("input#FirstName")).getAttribute("value"),firstName);

        Assert.assertEquals(driver.findElement(By.cssSelector("input#LastName")).getAttribute("value"), lastName);

        Assert.assertEquals(driver.findElement(By.name("DateOfBirthDay")).getAttribute("value"),dd);

        Assert.assertEquals(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']/option[@selected='']")).getText(),mm);

        Assert.assertEquals(driver.findElement(By.name("DateOfBirthYear")).getAttribute("value"),yyyy);

        Assert.assertEquals(driver.findElement(By.cssSelector("input#Email")).getAttribute("value"),email);

        Assert.assertEquals(driver.findElement(By.cssSelector("input#Company")).getAttribute("value"),companyName);


        driver.quit();
    }

    public void sleepInSecond(long timeInSecond){
        try{
            Thread.sleep(3000);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }

    public String randomEmail(){
        Random ran = new Random();
        return firstName+lastName+ran.nextInt(99999)+"@gmail.net";
    }



}
