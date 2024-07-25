package Exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Exercise_01 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_Empty_Data() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.xpath("//button[text()='ĐĂNG KÝ' and @type='submit']")).click();
        String errorfirstname = driver.findElement(By.xpath("//label[contains(@class,'error') and @for='txtFirstname']")).getText();
        Assert.assertEquals(errorfirstname, "Vui lòng nhập họ tên");
        String erroremail = driver.findElement(By.xpath("//label[contains(@class,'error') and @for='txtEmail']")).getText();
        Assert.assertEquals(erroremail, "Vui lòng nhập email");
        String errorCemail = driver.findElement(By.xpath("//label[contains(@class,'error') and @for='txtCEmail']")).getText();
        Assert.assertEquals(errorCemail, "Vui lòng nhập lại địa chỉ email");
        String errorpassword = driver.findElement(By.xpath("//label[contains(@class,'error') and @for='txtPassword']")).getText();
        Assert.assertEquals(errorpassword, "Vui lòng nhập mật khẩu");
        String errorCpassword = driver.findElement(By.xpath("//label[contains(@class,'error') and @for='txtCPassword']")).getText();
        Assert.assertEquals(errorCpassword, "Vui lòng nhập lại mật khẩu");
        String errorphone = driver.findElement(By.xpath("//label[contains(@class,'error') and @for='txtPhone']")).getText();
        Assert.assertEquals(errorphone, "Vui lòng nhập số điện thoại.");
    }

    @Test
    public void TC_02_Invalid_Email() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.xpath("//input[@type='text' and @id='txtFirstname']")).sendKeys("huyen");
        driver.findElement(By.xpath("//input[@type='email' and @id='txtEmail']")).sendKeys("hello.com");
        driver.findElement(By.xpath("//input[@type='email' and @id='txtCEmail']")).sendKeys("123.com");
        driver.findElement(By.xpath("//input[@type='password' and @id='txtPassword']")).sendKeys("mth1905");
        driver.findElement(By.xpath("//input[@type='password' and @id='txtCPassword']")).sendKeys("mth1905");
        driver.findElement(By.xpath("//input[@type='number' and @id='txtPhone']")).sendKeys("0378577977");
        driver.findElement(By.xpath("//button[text()='ĐĂNG KÝ' and @type='submit']")).click();
        String erroremail = driver.findElement(By.xpath("//label[contains(@class,'error') and @for='txtEmail']")).getText();
        Assert.assertEquals(erroremail, "Vui lòng nhập email hợp lệ");
        String errorCemail = driver.findElement(By.xpath("//label[contains(@class,'error') and @for='txtCEmail']")).getText();
        Assert.assertEquals(errorCemail, "Email nhập lại không đúng");
    }
    @Test
    public void TC_03_Incorrect_CEmail() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.xpath("//input[@type='text' and @id='txtFirstname']")).sendKeys("huyen");
        driver.findElement(By.xpath("//input[@type='email' and @id='txtEmail']")).sendKeys("mth2105@gmail.com");
        driver.findElement(By.xpath("//input[@type='email' and @id='txtCEmail']")).sendKeys("mth2106@gmail.com");
        driver.findElement(By.xpath("//input[@type='password' and @id='txtPassword']")).sendKeys("mth1905");
        driver.findElement(By.xpath("//input[@type='password' and @id='txtCPassword']")).sendKeys("mth1905");
        driver.findElement(By.xpath("//input[@type='number' and @id='txtPhone']")).sendKeys("0378577977");
        driver.findElement(By.xpath("//button[text()='ĐĂNG KÝ' and @type='submit']")).click();
        String errorCemail = driver.findElement(By.xpath("//label[contains(@class,'error') and @for='txtCEmail']")).getText();
        Assert.assertEquals(errorCemail, "Email nhập lại không đúng");

    }
    @Test
    public void TC_04_Invalid_Password() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.xpath("//input[@type='text' and @id='txtFirstname']")).sendKeys("huyen");
        driver.findElement(By.xpath("//input[@type='email' and @id='txtEmail']")).sendKeys("mth2105@gmail.com");
        driver.findElement(By.xpath("//input[@type='email' and @id='txtCEmail']")).sendKeys("mth2105@gmail.com");
        driver.findElement(By.xpath("//input[@type='password' and @id='txtPassword']")).sendKeys("mth");
        driver.findElement(By.xpath("//input[@type='password' and @id='txtCPassword']")).sendKeys("mth");
        driver.findElement(By.xpath("//input[@type='number' and @id='txtPhone']")).sendKeys("0378577977");
        driver.findElement(By.xpath("//button[text()='ĐĂNG KÝ' and @type='submit']")).click();
        String errorpassword = driver.findElement(By.xpath("//label[contains(@class,'error') and @for='txtPassword']")).getText();
        Assert.assertEquals(errorpassword, "Mật khẩu phải có ít nhất 6 ký tự");
        String errorCpassword = driver.findElement(By.xpath("//label[contains(@class,'error') and @for='txtCPassword']")).getText();
        Assert.assertEquals(errorCpassword, "Mật khẩu phải có ít nhất 6 ký tự");

    }
    @Test
    public void TC_05_Invalid_Cpassword() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.xpath("//input[@type='text' and @id='txtFirstname']")).sendKeys("huyen");
        driver.findElement(By.xpath("//input[@type='email' and @id='txtEmail']")).sendKeys("mth2105@gmail.com");
        driver.findElement(By.xpath("//input[@type='email' and @id='txtCEmail']")).sendKeys("mth2105@gmail.com");
        driver.findElement(By.xpath("//input[@type='password' and @id='txtPassword']")).sendKeys("mth123");
        driver.findElement(By.xpath("//input[@type='password' and @id='txtCPassword']")).sendKeys("mth1234");
        driver.findElement(By.xpath("//input[@type='number' and @id='txtPhone']")).sendKeys("0378577977");
        driver.findElement(By.xpath("//button[text()='ĐĂNG KÝ' and @type='submit']")).click();
        String errorCpassword = driver.findElement(By.xpath("//label[contains(@class,'error') and @for='txtCPassword']")).getText();
        Assert.assertEquals(errorCpassword, "Mật khẩu bạn nhập không khớp");

    }
    @Test
    public void TC_06_Invalid_Phone() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.xpath("//input[@type='text' and @id='txtFirstname']")).sendKeys("huyen");
        driver.findElement(By.xpath("//input[@type='email' and @id='txtEmail']")).sendKeys("mth2105@gmail.com");
        driver.findElement(By.xpath("//input[@type='email' and @id='txtCEmail']")).sendKeys("mth2105@gmail.com");
        driver.findElement(By.xpath("//input[@type='password' and @id='txtPassword']")).sendKeys("mth123");
        driver.findElement(By.xpath("//input[@type='password' and @id='txtCPassword']")).sendKeys("mth123");
        driver.findElement(By.xpath("//input[@type='number' and @id='txtPhone']")).sendKeys("0378");
        driver.findElement(By.xpath("//button[text()='ĐĂNG KÝ' and @type='submit']")).click();
        String errorphone1 = driver.findElement(By.xpath("//label[contains(@class,'error') and @for='txtPhone']")).getText();
        Assert.assertEquals(errorphone1, "Số điện thoại phải từ 10-11 số.");

        driver.findElement(By.xpath("//input[@type='number' and @id='txtPhone']")).clear();
        driver.findElement(By.xpath("//input[@type='number' and @id='txtPhone']")).sendKeys("037899999999");
        driver.findElement(By.xpath("//button[text()='ĐĂNG KÝ' and @type='submit']")).click();
        String errorphone2 = driver.findElement(By.xpath("//label[contains(@class,'error') and @for='txtPhone']")).getText();
        Assert.assertEquals(errorphone2, "Số điện thoại phải từ 10-11 số.");

        driver.findElement(By.xpath("//input[@type='number' and @id='txtPhone']")).clear();
        driver.findElement(By.xpath("//input[@type='number' and @id='txtPhone']")).sendKeys("1234");
        driver.findElement(By.xpath("//button[text()='ĐĂNG KÝ' and @type='submit']")).click();
        String errorphone3 = driver.findElement(By.xpath("//label[contains(@class,'error') and @for='txtPhone']")).getText();
        Assert.assertEquals(errorphone3, "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
    }



}
