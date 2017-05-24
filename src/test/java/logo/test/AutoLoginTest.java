package logo.test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import jxl.read.biff.BiffException;
import logo.file.AutoTestCaseID;
import logo.file.AutoTestExcelFile;
import logo.module.InitSetup;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sun.security.krb5.KrbException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by mengfeifei on 2017/5/24.
 */
public class AutoLoginTest {
    AppiumDriver driver;
    AutoTestExcelFile fesm = new AutoTestExcelFile();
    InitSetup is= new InitSetup();
    AutoTestCaseID tcid = new AutoTestCaseID();

    @BeforeClass
    public void beforeClass() throws BiffException, IOException{
        fesm.SetContentInit((short) 10);
    }

    @BeforeMethod
    public void beforeMethod() throws MalformedURLException, InterruptedException{
       driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),is.InitSetUpCFG(new DesiredCapabilities()));
    }
    @Test
    public void My_Login_001() throws InterruptedException, BiffException, IOException, KrbException {
        tcid.TestCaseId(driver,"My_Login_001");
    }
    @AfterMethod
    public  void  afterMethod(){
        driver.quit();
    }
}
