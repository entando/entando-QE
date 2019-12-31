package org.entando.selenium.smoketests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class ScreenPrintSaver {
    private static int count;

    public static void save(WebDriver webDriver){
        try {

            File file = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            file.setReadable(true);
            System.out.println("Printing page from " + webDriver.getCurrentUrl());
            FileUtils.copyFile(file, new File("screenshot"+count++ + ".png"));
            System.out.println("printed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
