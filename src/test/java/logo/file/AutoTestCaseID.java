package logo.file;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import jxl.read.biff.BiffException;
import logo.module.ElementExist;
import logo.module.SlidePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import sun.security.krb5.KrbException;

import java.io.IOException;

/**
 * Created by LiuChuanan on 2017/5/24.
 */
public class AutoTestCaseID {
    ElementExist el = new ElementExist();
    static AutoTestExcelFile ft = new AutoTestExcelFile();
    //static AutoTestExcelFile ft = new AutoTestExcelFile();
   SlidePage sp = new SlidePage();
    public void TestCaseId(AppiumDriver driver, String id) throws InterruptedException, BiffException, IOException, KrbException {
        int i,j,k,l,m,n,o,p,q;
        for(i=0; i<ft.ReadContent().size(); i++){
            if(ft.ReadContent().get(i).contains(id)){
                for(j=0; j<ft.ReadTitle().size(); j++){
                    if(ft.ReadTitle().get(j).contains("定位方式")){
                        break;
                    }
                }
                String caseidLocation = ft.ReadTitleContent(i+1,j);
                for(k=0; k<ft.ReadTitle().size(); k++){
                    if(ft.ReadTitle().get(k).contains("控件元素")){
                        break;
                    }
                }
                String caseidElement = ft.ReadTitleContent(i+1,k);
                for(l=0; l<ft.ReadTitle().size(); l++){
                    if(ft.ReadTitle().get(l).contains("操作方法")){
                        break;
                    }
                }
                String caseidOperationMethod = ft.ReadTitleContent(i+1,l);
                for(m=0; m<ft.ReadTitle().size(); m++){
                    if(ft.ReadTitle().get(m).contains("测试数据")){
                        break;
                    }
                }
                String caseidTestData = ft.ReadTitleContent(i+1,m);
                for(n=0; n<ft.ReadTitle().size(); n++){
                    if(ft.ReadTitle().get(n).contains("验证数据")){
                        break;
                    }
                }
                String caseidVerifyData = ft.ReadTitleContent(i+1,n);
                for(o=0; o<ft.ReadTitle().size(); o++){
                    if(ft.ReadTitle().get(o).contains("延迟时间")){
                        break;
                    }
                }
                String sleepTime = ft.ReadTitleContent(i+1,o);
                if(caseidLocation.equals("By.xpath")) {
                    if (caseidOperationMethod.equals("sendkeys")) {
                        el.waitForElementByXpath(caseidElement, driver);
                        driver.findElement(By.xpath(caseidElement)).sendKeys(caseidTestData);
                        if (sleepTime != null && sleepTime.length() != 0) {
                            String stepTime = sleepTime.substring(0, sleepTime.indexOf("."));
                            Thread.sleep(Integer.parseInt(stepTime));
                        }
                    } else if (caseidOperationMethod.equals("click")) {
                        el.waitForElementByXpath(caseidElement, driver);
                        driver.findElement(By.xpath(caseidElement)).click();
                        if (sleepTime != null && sleepTime.length() != 0) {
                            String stepTime = sleepTime.substring(0, sleepTime.indexOf("."));
                            Thread.sleep(Integer.parseInt(stepTime));
                        }
                    } else if (caseidOperationMethod.equals("swipedown")) {
                        el.waitForElementByXpath(caseidElement, driver);
                        sp.Down_Page(caseidElement, driver);
                        if (sleepTime != null && sleepTime.length() != 0) {
                            String stepTime = sleepTime.substring(0, sleepTime.indexOf("."));
                            Thread.sleep(Integer.parseInt(stepTime));
                        }
                    }
                }else  if(caseidLocation.equals("By.id")) {
                    if (caseidOperationMethod.equals("sendkeys")) {
                        el.waitForElementById(caseidElement, driver);
                        driver.findElement(By.id(caseidElement)).sendKeys(caseidTestData);
                        if (sleepTime != null && sleepTime.length() != 0) {
                            String stepTime = sleepTime.substring(0, sleepTime.indexOf("."));
                            Thread.sleep(Integer.parseInt(stepTime));
                        }
                    } else if (caseidOperationMethod.equals("click")) {
                        el.waitForElementById(caseidElement, driver);
                        driver.findElement(By.id(caseidElement)).click();
                        if (sleepTime != null && sleepTime.length() != 0) {
                            String stepTime = sleepTime.substring(0, sleepTime.indexOf("."));
                            Thread.sleep(Integer.parseInt(stepTime));
                        }
                    } else if (caseidOperationMethod.equals("swipedown")) {
                        el.waitForElementById(caseidElement, driver);
                        sp.Down_Page(caseidElement, driver);
                        if (sleepTime != null && sleepTime.length() != 0) {
                            String stepTime = sleepTime.substring(0, sleepTime.indexOf("."));
                            Thread.sleep(Integer.parseInt(stepTime));
                        }
                    }else if (caseidOperationMethod.equals("DownPage")) {
                        el.waitForElementById(caseidElement, driver);
                        sp.DownPage(caseidElement, driver);
                        if (sleepTime != null && sleepTime.length() != 0) {
                            String stepTime = sleepTime.substring(0, sleepTime.indexOf("."));
                            Thread.sleep(Integer.parseInt(stepTime));
                        }
                    }
                }else if(caseidLocation.equals("By.name")) {
                    if (caseidOperationMethod.equals("sendkeys")) {
                        el.waitForElementByName(caseidElement, driver);
                        driver.findElement(By.name(caseidElement)).sendKeys(caseidTestData);
                        if (sleepTime != null && sleepTime.length() != 0) {
                            String stepTime = sleepTime.substring(0, sleepTime.indexOf("."));
                            Thread.sleep(Integer.parseInt(stepTime));
                        }
                    } else if (caseidOperationMethod.equals("click")) {
                        el.waitForElementByName(caseidElement, driver);
                        driver.findElement(By.name(caseidElement)).click();
                        if (sleepTime != null && sleepTime.length() != 0) {
                            String stepTime = sleepTime.substring(0, sleepTime.indexOf("."));
                            Thread.sleep(Integer.parseInt(stepTime));
                        }
                    } else if (caseidOperationMethod.equals("press")) {
                        el.waitForElementByName(caseidElement, driver);
                        WebElement e = driver.findElement((By.name(caseidElement)));
                        TouchAction action = new TouchAction(driver);
                        action.press(e).waitAction(5000);
                        if (sleepTime != null && sleepTime.length() != 0) {
                            String stepTime = sleepTime.substring(0, sleepTime.indexOf("."));
                            Thread.sleep(Integer.parseInt(stepTime));
                        }
                    }
                }else if(caseidLocation.equals("By.driver")) {
                    if (caseidOperationMethod.equals("swipetoup")) {
                        //el.waitForElementByName(caseidElement, driver);
                        sp.swipeToUp(Integer.parseInt(caseidElement),driver);
                        if (sleepTime != null && sleepTime.length() != 0) {
                            String stepTime = sleepTime.substring(0, sleepTime.indexOf("."));
                            Thread.sleep(Integer.parseInt(stepTime));
                        }
                    }
                }else if(caseidLocation.equals("By.name")){
                if("pass".equals(el.waitForElementByNameSkip(caseidElement,driver))){
                    if(caseidOperationMethod.equals("click")){
                        driver.findElement(By.name(caseidElement)).click();
                        if (sleepTime != null && sleepTime.length() != 0) {
                            String stepTime = sleepTime.substring(0, sleepTime.indexOf("."));
                            Thread.sleep(Integer.parseInt(stepTime));
                        }
                    }else if("failed".equals(el.waitForElementByNameSkip(caseidElement,driver))){
                            continue;
                    }
                }
            }
            if(caseidVerifyData !=null && caseidVerifyData.length()!=0){
                    el.waitForElementByName(caseidVerifyData,driver);
            }
            for(p=0; p <ft.ReadTitle().size(); p++){
                if(ft.ReadTitle().get(p).contains("测试结果")){
                    break;
                }
            }
            ft.WriteTitleContent(i+1,(short)p);
            }else {

            }
        }
        Thread.sleep(3000);
    }
}
