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

public class Exercise_02_BrowserCommand {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_PageURL(){
        //1. truy cập vào trang
        driver.get("http://live.techpanda.org/");

        //Click vào my account ở footer
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        sleepInSecond(3);
        //Verify url của page
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");

        sleepInSecond(3);
        //Click Create an account button
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        sleepInSecond(3);
        //Verify url của page
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");

        driver.quit();
    }

    @Test
    public void TC_O2_VerifyTitle(){
        //1. truy cập vào trang
        driver.get("http://live.techpanda.org/");
        //Click vào my account ở footer
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        sleepInSecond(3);
        //Verify title của page
        Assert.assertEquals(driver.getTitle(),"Customer Login");

        sleepInSecond(3);
        //Click Create an account button
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        sleepInSecond(3);
        //Verify title của page
        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");

        driver.quit();
    }

    @Test
    public void TC_03_Navigationfunction(){
        //1. truy cập vào trang
        driver.get("http://live.techpanda.org/");
        //Click vào my account ở footer
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        sleepInSecond(3);
        //Click Create an account button
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        sleepInSecond(3);
        //Verify url của page
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");

        //Back lại trang login page
        driver.navigate().back();
        //Verify url của login page
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");

        //Forward tới trang register Page
        driver.navigate().forward();

        //Verify title của register page
        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");

        driver.quit();
    }

    @Test
    public void TC_04_GetPageSourceCode(){
        //1. truy cập vào trang
        driver.get("http://live.techpanda.org/");
        //Click vào my account ở footer
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        //Verify login page chứa text => dùng assertTrue để mong đợi nó có chứa từ khoá mà mình mong đợi
        Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));

        sleepInSecond(3);
        //Click Register Page
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        Assert.assertTrue(driver.getPageSource().contains("Create an Account"));

        driver.quit();
    }

    //Element Commands
    @Test
    public void TC_01_Element(){
        //1. truy cập vào trang
        driver.get("https://automationfc.github.io/basic-form/index.html");

        //Kiểm tra các phần tử sau hiển thị trên trang - nếu hiển thị thì nhập giá trị và in ra màn hình Element is displayed

        if (driver.findElement(By.xpath("//input[@id='mail']")).isDisplayed()){
            driver.findElement(By.xpath("//input[@id='mail']")).sendKeys("mth1806@gmail.com");
            System.out.println("Email textbox is displayed");
        } else{
            System.out.println("Email textbox is not displayed");
        }

        if (driver.findElement(By.cssSelector("input#under_18")).isDisplayed()){
            driver.findElement(By.cssSelector("input#under_18")).click();
            System.out.println("Under 18 radio is displayed");
        } else{
            System.out.println("Under 18 radio is not displayed");
        }

        if (driver.findElement(By.cssSelector("textarea#edu")).isDisplayed()){
            driver.findElement(By.cssSelector("textarea#edu")).sendKeys("Math");
            System.out.println("Education textarea is displayed");
        } else{
            System.out.println("Education textarea is not displayed");
        }

        if (driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed()){
            System.out.println("Name User5 text is displayed");
        } else{
            System.out.println("Name User5 text is not displayed");
        }



        driver.quit();

    }

    @Test
    public void TC_02_Element(){
        //1. truy cập vào trang
        driver.get("https://automationfc.github.io/basic-form/index.html");

        //Kiểm tra các phần tử enable trên trang
        if (driver.findElement(By.xpath("//input[@id='mail']")).isEnabled()){
            System.out.println("Email textbox is enabled");
        } else{
            System.out.println("Email textbox is disabled");
        }

        if (driver.findElement(By.cssSelector("input#under_18")).isEnabled()){
            System.out.println("Under 18 radio is enabled");
        } else{
            System.out.println("Under 18 radio is disabled");
        }

        if (driver.findElement(By.cssSelector("textarea#edu")).isEnabled()){
            System.out.println("Education textarea is enabled");
        } else{
            System.out.println("Education textarea is disabled");
        }

        if (driver.findElement(By.cssSelector("select#job1")).isEnabled()){
            System.out.println("Job Role 1 is enabled");
        } else{
            System.out.println("Job Role 1 is disabled");
        }


        driver.quit();
    }

    @Test
    public void TC_03_Element(){
        //1. truy cập vào trang
        driver.get("https://automationfc.github.io/basic-form/index.html");

        //Click chọn Age(Under18)và Laguages: Java
        driver.findElement(By.cssSelector("input#under_18")).click();
        driver.findElement(By.cssSelector("input#java")).click();

        //Kiểm tra các phần tử đã được chọn chưa
        if (driver.findElement(By.cssSelector("input#under_18")).isSelected()){
            System.out.println("Under 18 radio is selected");
        } else{
            System.out.println("Under 18 radio is de-selected");
        }

        if (driver.findElement(By.cssSelector("input#java")).isSelected()){
            System.out.println("Languages:Java checkbox is selected");
        } else{
            System.out.println("Languages:Java checkbox is de-selected");
        }

        //Click để bỏ chọn languages: Java
        driver.findElement(By.cssSelector("input#java")).click();
        // kiểm tra lại phần tử đã chọn
        if (driver.findElement(By.cssSelector("input#java")).isSelected()){
            System.out.println("Languages:Java checkbox is selected");
        } else{
            System.out.println("Languages:Java checkbox is de-selected");
        }

        driver.quit();
    }

    @Test
    public void TC_04_Element(){
        //1. truy cập vào trang
        driver.get("https://login.mailchimp.com/signup/");

        //Nhập dữ liệu vào trường email
        driver.findElement(By.cssSelector("input#email")).sendKeys("mth2506@gmail.com");

        //Check giá trị password
        //Case 1: Chỉ nhập number
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("123");
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

        //Case 2: Chỉ có uppercase
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("ABC");
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

        //Case 3: Chỉ có lowercase
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("abc");
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

        //Case4: Special chars
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("*");
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

        //Case5: Max length
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("qwertyui");
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());

        //Case6: valid
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("Mth2506123#");
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());

        //Case7: empty
        driver.findElement(By.cssSelector("input#new_password")).clear();
        WebElement signUpbutton = driver.findElement(By.xpath("//section/button[@id='create-account-enabled']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", signUpbutton);
        //signUpbutton.click();
        signUpbutton.click();
        //sleepInSecond(2);

        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

        driver.quit();
    }


    public void sleepInSecond(long timeInSecond){
        try{
            Thread.sleep(3000);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }



}
