/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entando.selenium.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author leobel
 */
public class ChromeDriverManager implements DriverManager{

    private ChromeDriver driver;

    @Override
    public WebDriver getDriver() {
        if(driver == null){
            driver = new ChromeDriver();
        }
        return driver;
    }
    
}
