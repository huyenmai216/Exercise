package Exercise;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Exercise03_ElementCommand {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Login_withemptyvalue(){
        //1. truy cập vào trang
        driver.get("http://live.techpanda.org/");
        sleepInSecond(3);

        //Click vào my account để tới trang đăng nhập
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSecond(3);

        //Để trống username/pw
//        driver.findElement(By.cssSelector("input#email")).clear();
//        driver.findElement(By.cssSelector("input#pass")).clear();clear

        //Click Login button
        driver.findElement(By.cssSelector("button#send2")).click();
        sleepInSecond(3);

//        String a=driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText();
//        System.out.println(a);
        //Verify error message tại 2 fields
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(),"This is a required field.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).getText(),"This is a required field.");
    }
    @Test
    public void TC_02_Login_with_invalidEmail(){
        //1. truy cập vào trang
        driver.get("http://live.techpanda.org/");
        sleepInSecond(3);

        //Click vào my account để tới trang đăng nhập
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSecond(3);

        //nhập invalid email 123456@123412.123
        driver.findElement(By.cssSelector("input#email")).sendKeys("123456@123412.123");
//        driver.findElement(By.cssSelector("input#pass")).clear();

        //Click Login button
        driver.findElement(By.cssSelector("button#send2")).click();
        sleepInSecond(3);

//        String a=driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText();
//        System.out.println(a);
        //Verify error message tại 2 fields
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-email-email")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");
    }
    @Test
    public void TC_03_Login_invalidPw(){
        //1. truy cập vào trang
        driver.get("http://live.techpanda.org/");
        sleepInSecond(3);

        //Click vào my account để tới trang đăng nhập
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSecond(3);

        //Nhập valid email và invalid pw
        driver.findElement(By.cssSelector("input#email")).sendKeys("mth1107@gmail.com");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123");

        //Click Login button
        driver.findElement(By.cssSelector("button#send2")).click();
        sleepInSecond(3);

//        String a=driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText();
//        System.out.println(a);
        //Verify error message tại field pw
        //Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(),"This is a required field.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-password-pass")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");
    }
    @Test
    public void TC_04_Login_incorrectEmail_pw(){
        //1. truy cập vào trang
        driver.get("http://live.techpanda.org/");
        sleepInSecond(3);

        //Click vào my account để tới trang đăng nhập
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSecond(3);

        //nhập correct email và incorrect pw
        driver.findElement(By.cssSelector("input#email")).sendKeys("huyenmai@gmail.com");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("12345678");

        //Click Login button
        driver.findElement(By.cssSelector("button#send2")).click();
        sleepInSecond(3);

//        String a=driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText();
//        System.out.println(a);
        //Verify error message
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(),"Invalid login or password.");

    }

    @Test
    public void TC_05_Login_Success(){
        //1 đăng kí trước (manual) 1 tk rồi dùng để login
        // - rủi ro khi hệ thông reset dữ liệu, db bị xoá
        // - qua môi trường mới thì cũng phải đăng kí 1 account mới
        // 2 dùng tính năng register trước - email cố đinhj không thay đổi
        // - chức năng register cũng auto, email được fix cứng nên chỉ chạy được 1 lần
        // - lần sau thực hiện sẽ bị lỗi, cần phải đổi email

        // 3 - sẽ dùng tính năng register trước - email random
        //- chạy luôn đúng trên các case

        //1. truy cập vào trang
        driver.get("http://live.techpanda.org/");
        sleepInSecond(3);

        //Click vào my account để tới trang đăng nhập
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInSecond(3);

        String firstname="Automation", lastname = "FC", email = getEmailadress(), password="1234567";
        //Đăng kí tài khoản trước
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        sleepInSecond(3);
        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstname);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastname);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(email);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);
        sleepInSecond(3);


        //Click Login button
        driver.findElement(By.cssSelector("button[title='Register']")).click();
        sleepInSecond(3);

        //Verify thong tin

//        String a=driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText();
//        System.out.println(a);
        //Verify  message
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"Thank you for registering with Main Website Store.");

        String fullname = firstname +" "+lastname;
        //Verify  first and lastname
        Assert.assertEquals(driver.findElement(By.xpath("//p[@class='hello']/strong")).getText(),"Hello, " +fullname + "!");

        //Verify  email
        String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInformation.contains(fullname));
        Assert.assertTrue(contactInformation.contains(email));
        sleepInSecond(3);

        //Check Login thành công

        //Log out
        driver.findElement(By.cssSelector("a[class*='skip-account']")).click();
        sleepInSecond(3);
        driver.findElement(By.xpath("//a[@title='Log Out']")).click();
        sleepInSecond(3);

        //Click vào my account để tới trang đăng nhập
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();


        //nhập correct email và correct pw
        driver.findElement(By.cssSelector("input#email")).sendKeys(email);
        driver.findElement(By.cssSelector("input#pass")).sendKeys(password);

        //Click Login button
        driver.findElement(By.cssSelector("button#send2")).click();
        sleepInSecond(3);

//        String a=driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText();
//        System.out.println(a);
        //Verify error message
        //Verify  first and lastname
        Assert.assertEquals(driver.findElement(By.xpath("//p[@class='hello']/strong")).getText(),"Hello, " +fullname + "!");

        //Verify  email
        Assert.assertTrue(contactInformation.contains(fullname));
        Assert.assertTrue(contactInformation.contains(email));
        sleepInSecond(3);

        //Verify account information
        driver.findElement(By.xpath("//a[text()='Account Information']")).click();
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#firstname")).getAttribute("value"),firstname);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#lastname")).getAttribute("value"),lastname);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#email")).getAttribute("value"),email);

        driver.quit();
    }
    public void sleepInSecond(long timeInSecond){
        try{
            Thread.sleep(3000);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }

    public String getEmailadress(){
        Random ran = new Random();
        return "automation" + ran.nextInt(9999)+ "@gmail.net";

    }
}
