package org.entando.selenium.smoketests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenPrintSaver {
    private static int count;
    public static void save(WebDriver webDriver){
        try {
            File file = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File("screenshot"+count++ + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
