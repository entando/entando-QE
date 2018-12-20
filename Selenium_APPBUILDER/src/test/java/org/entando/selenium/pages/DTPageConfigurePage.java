/*
Copyright 2015-Present Entando Inc. (http://www.entando.com) All rights reserved.
This library is free software; you can redistribute it and/or modify it under
the terms of the GNU Lesser General Public License as published by the Free
Software Foundation; either version 2.1 of the License, or (at your option)
any later version.
This library is distributed in the hope that it will be useful, but WITHOUT
ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
details.
 */
package org.entando.selenium.pages;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.entando.selenium.tests.DTPageConfigureTest;
import org.entando.selenium.utils.PageObject;
import org.entando.selenium.utils.Utils;
import org.entando.selenium.utils.pageParts.Kebab;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import static org.openqa.selenium.interactions.PointerInput.Origin.fromElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This class represent the Page Configure page
 * 
 * @version 1.01
 */
public class DTPageConfigurePage extends PageObject{
    
    @FindBy(css = "h1.PageConfigPage__title")
    private WebElement pageTitle;
    
    @FindBy(xpath = "//button[contains(@class, 'PageConfigPage__info-btn')]")
    private WebElement infoButton;
    
    @FindBy(xpath = "//button[contains(@class, 'btn-warning')]")
    private WebElement restoreButton;
    
    @FindBy(xpath = "//button[contains(@class, 'PageConfigPage__unpublish-btn')]")
    private WebElement unpublishButton;
    
    @FindBy(xpath = "//button[contains(@class, 'PageConfigPage__publish-btn')]")
    private WebElement publishButton;
    
    @FindBy(xpath = "//button[contains(@class, 'dropdown-on-the-fly')]")
    private WebElement onTheFlyButton;
    
    @FindBy(xpath = "//a[contains(@title, 'Preview')]")
    private WebElement previewButton;
    
    @FindBy(xpath = "//button[contains(@class, 'PageConfigPage__apply-default-btn')]")
    private WebElement defaultWidgetButton;
    
    @FindBy(xpath = "//i[contains(@*, 'PageConfigPage__default-applied-icon')]")
    private WebElement defaultWidgetIcon;
    
    @FindBy(xpath = "//div[contains(@class, 'ContentWidgetList--list')]")
    private WebElement widgetList;
    
    @FindBy(xpath = "//div[contains(@class, 'PageConfigGrid')]")
    private WebElement pageConfigGrid;
    
    

    public DTPageConfigurePage(WebDriver driver) {
        super(driver);
    }

    public WebElement getPageTitle() {
        return pageTitle;
    }

    public WebElement getInfoButton() {
        return infoButton;
    }

    public WebElement getRestoreButton() {
        return restoreButton;
    }

    public WebElement getUnpublishButton() {
        return unpublishButton;
    }

    public WebElement getPublishButton() {
        return publishButton;
    }

    public WebElement getOnTheFlyButton() {
        return onTheFlyButton;
    }

    public WebElement getPreviewButton() {
        return previewButton;
    }

    public WebElement getDefaultWidgetButton() {
        return defaultWidgetButton;
    }

    public WebElement getDefaultWidgetIcon() {
        return defaultWidgetIcon;
    }

    public WebElement getWidgetList() {
        return widgetList;
    }

    public WebElement getPageConfigGrid() {
        return pageConfigGrid;
    }
    
    public WebElement getWidget(String widgetName){
        By widgetTag = By.xpath(String.format("//*[text()= '%s']/../../../../..", widgetName));
        WebElement widget = this.widgetList.findElement(widgetTag);
        return widget;
    }
    
    public List<WebElement> getCells(String cellName){
        By cellTag = By.xpath(String.format("//*[text()= '%s']/..", cellName));
        List<WebElement> cellsElement = this.pageConfigGrid.findElements(cellTag);
        return cellsElement;
    }
    
    public List<WebElement> getWidgetsOnGrid(String widgetName, String cellName){
        By widgetOnGridTag = By.xpath(String.format("//*[text()= '%s']/../..//*[text()= '%s']/..", cellName, widgetName));
        List<WebElement> widgetsElement = this.pageConfigGrid.findElements(widgetOnGridTag);
        return widgetsElement;
    }
    
    public List<Kebab> getKebabsWidgetOnGrid(String widgetName, String cellName){
        List<WebElement> widgetsElement = this.getWidgetsOnGrid(widgetName, cellName);
        List<Kebab> kebabs = new ArrayList<>();
        widgetsElement.forEach((widget) -> {
            kebabs.add(new Kebab(widget, widget));
        });
        return kebabs;
    }
    
    public boolean moveWidgetToFrame(String widgetName, String frameName){
        
        
        
        WebElement from = driver.findElement(By.xpath(String.format("//a[text()='%s']/..", widgetName)));
        Assert.assertNotNull(from);
        
        WebElement to = driver.findElement(By.xpath( String.format("//div[contains(@class, 'EmptyFrame')]//*[text()= '%s']/../..", frameName)));
        Assert.assertNotNull(to);
        
     
        //Utils.waitUntilIsVisible(driver, from);
        
        org.openqa.selenium.Dimension d = to.getSize();
        int FrameWidth = d.getWidth();
        int FrameHeight = d.getHeight();
        Point coordinates = to.getLocation();
        
        System.out.println(FrameWidth);
        System.out.println(FrameHeight);
        
        Utils.waitUntilIsVisible(driver, to);
        //Point coordinates = driver.findElement(By.xpath( String.format("//div[contains(@class, 'EmptyFrame')]//*[text()= '%s']/../..", frameName))).getLocation();
        
        to.click();
        
        
        Assert.assertTrue(from.isDisplayed());
        Assert.assertTrue(to.isDisplayed());
        
        
 
        
        Actions testBuilder = new Actions(driver);
        Action seriesOfAction = testBuilder
                
                .dragAndDrop(from, to)
                .pause(2000)
                .build();
        seriesOfAction.perform();
        
           Robot robot;
        try {
            robot = new Robot();
            robot.mouseMove(coordinates.getX()+FrameWidth/2 ,coordinates.getY()+FrameHeight*3);
            robot.mousePress( InputEvent.BUTTON1_MASK );
            robot.delay(2000);
            robot.mouseRelease( InputEvent.BUTTON1_MASK );
        } catch (AWTException ex) {
            Logger.getLogger(DTPageConfigureTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        return true;
    }
    
   
    public void JavascriptDragAndDrop(String widgetName, String frameName){
            
            
            WebElement from = driver.findElement(By.xpath(String.format("//a[text()='%s']/..", widgetName)));
        
        
        WebElement to = driver.findElement(By.xpath( String.format("//div[contains(@class, 'EmptyFrame')]//*[text()= '%s']/../..", frameName)));
        

    String script = "function createEvent(typeOfEvent) {\n" +
"    var event = document.createEvent(\"CustomEvent\");\n" +
"    event.initCustomEvent(typeOfEvent, true, true, null);\n" +
"    event.dataTransfer = {\n" +
"        data: {},\n" +
"        setData: function (key, value) {\n" +
"            this.data[key] = value;\n" +
"        },\n" +
"        getData: function (key) {\n" +
"            return this.data[key];\n" +
"        }\n" +
"    };\n" +
"    return event;\n" +
"}\n" +
"function dispatchEvent(element, event, transferData) {\n" +
"    if (transferData !== undefined) {\n" +
"        event.dataTransfer = transferData;\n" +
"    }\n" +
"    if (element.dispatchEvent) {\n" +
"        element.dispatchEvent(event);\n" +
"    } else if (element.fireEvent) {\n" +
"        element.fireEvent(\"on\" + event.type, event);\n" +
"    }\n" +
"}\n" +
"function simulateHTML5DragAndDrop(element, target) {\n" +
"    var dragStartEvent = createEvent('dragstart');\n" +
"    dispatchEvent(element, dragStartEvent);\n" +
"    var dropEvent = createEvent('drop');\n" +
"    dispatchEvent(target, dropEvent, dragStartEvent.dataTransfer);\n" +
   "target.style.border = '2px solid red';\n"+         
"    var dragEndEvent = createEvent('dragend');\n" +
"    dispatchEvent(element, dragEndEvent, dropEvent.dataTransfer);\n" +
"}";
    script += "simulateHTML5DragAndDrop(arguments[0], arguments[1])";
    JavascriptExecutor js = (JavascriptExecutor)driver;
    js.executeScript(script, from, to);
}
    
    
    
    
    
    
    
    
    public static Boolean isVisibleInViewport(WebElement element) {
  WebDriver driver = ((RemoteWebElement)element).getWrappedDriver();

  return (Boolean)((JavascriptExecutor)driver).executeScript(
      "var elem = arguments[0],                 " +
      "  box = elem.getBoundingClientRect(),    " +
      "  cx = box.left + box.width / 2,         " +
      "  cy = box.top + box.height / 2,         " +
      "  e = document.elementFromPoint(cx, cy); " +
      "for (; e; e = e.parentElement) {         " +
      "  if (e === elem)                        " +
      "    return true;                         " +
      "}                                        " +
      "return false;                            "
      , element);
}
    
}
