package logo.module;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mengfeifei on 2017/5/24.
 */
public class ScreenShot {
    public static void takeScreenShot(WebDriver driver){
        File screenShotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //截屏图片保存到路径以及取名
        try{
            FileUtils.copyFile(screenShotFile, new File("screenshot/"+getDatePath()+"/"+getCurrentDateTime()+".jpg"));
            //以图片当前时间为名称保存到当前日期的目录下
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static String getCurrentDateTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyyyMMdd_HHmmss");
        return df.format(new Date());
    }
    public static File getDatePath(){
       Date date = new Date();
       String path = new SimpleDateFormat("yyyyMMdd").format(date);
       File f = new File(path);
       return f;
    }
}
