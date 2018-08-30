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
package org.entando.selenium.utils;

import static java.lang.Thread.sleep;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.entando.selenium.utils.pageParts.ExpandableTable;
import org.openqa.selenium.WebElement;

/**
 *  This class contains some utils methos for the pages contains a browsable table
 * 
 *  @version 1.01
 */
public class ExpandableTableTestTypology extends FunctionalTestBase{
    
    /**
     * This method click on every right button to open all rows of the table
     * 
     * @param page The Page Tree page in which the method operates
     * @throws java.lang.InterruptedException
     */
    public void expandTable(ExpandableTablePageInterface page) throws InterruptedException{
        List<WebElement> buttons = page.getTable().getAllExpandableButtons();
        while(!buttons.isEmpty()){
            /** Debug code **/ Logger.getGlobal().info(String.valueOf(buttons.size()));
            clickOnEveryButton(buttons);
            buttons = page.getTable().getAllExpandableButtons();
        }
    }
    
    
    
    /**
     * This method click on right button to open the specific rows on the table
     * 
     * @param page The Page Tree page in which the method operates
     * @param rowName The name of the row to find
     * @param columnName The name of the column in witch to find the item
     * @throws java.lang.InterruptedException
     */
    public void expandRows(ExpandableTablePageInterface page, String rowName, String columnName) throws InterruptedException{
        List<WebElement> buttons = page.getTable().getExpandableButtons(rowName, columnName);
        /** Debug code **/ Logger.getGlobal().log(Level.INFO, "Numeber of expandable buttons founded: {0}", String.valueOf(buttons.size()));
        clickOnEveryButton(buttons);
        
        //Wait until the spinner disappears
        sleep(800);
        //Utils.waitUntilIsVisible(driver, ExpandableTable.rowSpinner);
        //Utils.waitUntilIsPresent(driver, ExpandableTable.rowSpinner);
        //Utils.waitUntilIsDisappears(driver, ExpandableTable.rowSpinner);
        /*int i = 0;
        int lapse = 50;
        int wait = 2000;
        while((driver.findElements(ExpandableTable.rowSpinner).isEmpty())&&(i<=wait)){
            sleep(lapse);
            i = i + lapse;
        }
        while((!driver.findElements(ExpandableTable.rowSpinner).isEmpty())&&(i<=wait)){
            sleep(lapse);
            i = i + lapse;
        }
        */
    }
    
    
    
    /**
     * This method click on every button present in the list
     * 
     * @param buttons 
     */
    private void clickOnEveryButton(List<WebElement> buttons) throws InterruptedException{
        for(WebElement button : buttons){
                //Utils.waitUntilIsVisible(driver, button);
                //sleep(100);
                button.click();
                Utils.waitUntilIsPresent(driver, ExpandableTable.rowSpinner);
                Utils.waitUntilIsDisappears(driver, ExpandableTable.rowSpinner);
            }
    }
    
}//end class
