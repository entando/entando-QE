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

package org.entando.selenium.utils.pageParts;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * This class prepresent the Expandable Table
 * 
 * @version 1.01
 */

public class ExpandableTable extends SimpleTable{
    
    By expandedIconTag = By.className("TreeNodeExpandedIcon");
    By dragButtonTag = By.className("PageTree__drag-handle");
    String cellWithoutIconTag = "column-td--empty";
    String angleRightIconTag = "fa-angle-right";
    public static final By rowSpinner = 
            By.xpath("//div[contains(@class, 'RowSpinner__spinner')]");
    
    
    
    public ExpandableTable(WebElement table){
        super(table);
    }
    
    
    
    /**
     * All Expandable Buttons present in the current table
     * @return a List of right buttons, if all buttons are expanded return a empty list
     */
    public List<WebElement> getAllExpandableButtons(){
        List<WebElement> rows = super.getRowsList();
        return extractExpandableButtons(rows);
    }
    
    
    
    /**
     * 
     * @param rowName the item name to find
     * @param columnName the name of the column in witch to find the item
     * @return a List of right buttons, if the item was not found return a empty list
     */
    public List<WebElement> getExpandableButtons(String rowName, String columnName){
        List<WebElement> rows = super.findRowList(rowName, columnName);
        return extractExpandableButtons(rows);
    }
    
    
    
    /**
     * Extract the buttons from a rows list
     * @param rows
     * @return a List of expandable buttons, if all buttons are expanded return a empty list
     */
    private List<WebElement> extractExpandableButtons(List<WebElement> rows){
        List<WebElement> allExpandableButtons = new ArrayList<>();
        /** Debug code **/ Logger.getGlobal().info("Extracting buttons");
        for(WebElement row: rows){
            //Identify the first cell of the row
            WebElement cell = row.findElement(super.getCellTag());
            //Identify a expanded icon
            WebElement icon = cell.findElement(expandedIconTag);
            //If the cell is a expandable folder and the icon is an angle right icon
            //the icon is added in the buttons List
            if((!(cell.getAttribute("class").contains(cellWithoutIconTag)))&&
                 (icon.getAttribute("class").contains(angleRightIconTag)))
            {
                /** Debug code **/ Logger.getGlobal().info(cell.getText());
                allExpandableButtons.add(icon);
            }
        }
        return allExpandableButtons;
    }
    
    
    
    /**
     * The drag button of a specific row
     * @param rowName the item to search in the first column
     * @return the corresponding drag button
     */
    public WebElement getDragButton(String rowName){
        String itemColumn = super.getHeaderTitlesList().get(0);
        WebElement cell = super.getCell(rowName, itemColumn, itemColumn);
        WebElement dragButton = cell.findElement(dragButtonTag);
        return dragButton;
    }
    
    
    
}//end class
