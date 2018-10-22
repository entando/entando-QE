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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * This class represents the simple html table
 * 
 * @version 1.01
 */

public class SimpleTable {
    public WebElement table;
    /*
    Ways to find elements
    */
    private static By rowTag = By.xpath("./tbody/tr");
    private static By headerTag = By.xpath("//th"); //thead/tr[1]/th
    private static By cellTag = By.xpath("./td");
    private static String innerText = "innerText";
    
    
    public SimpleTable(){
    }
    
    public SimpleTable(WebElement table){
        this.table = table;
    }
    
    public By getRowTag() {
        return rowTag;
    }

    public By getHeaderTag() {
        return headerTag;
    }

    public By getCellTag() {
        return cellTag;
    }
    
    /**
     * To set the table
     * @param table The table to set
     */
    public void setTable (WebElement table){
        this.table = table;
    }
    
    
    /**
     * The list of the rows
     * @return a List of row WebElement
     */
    public List<WebElement> getRowsList(){
        return table.findElements(rowTag);
    }
    
    
    /**
     * The size of the table (number of rows)
     * @return the table size
     */
    public int tableSize(){
        return table.findElements(rowTag).size();
    }
    
    
    /**
     * The List of table header cells
     * @return a List of cells WebElement
     */
    public List<WebElement> getHeaderList(){
        return  table.findElements(headerTag);
    }
    
    /**
     * The List of table header titles on List
     * @return a List of string titles
     */
    public List<String> getHeaderTitlesList(){
        List <WebElement> headerCells = this.getHeaderList();
        int columnCount = headerCells.size();
        List<String> headerTitles = new ArrayList<>();
        for (int i = 0; i < columnCount; i++) {
            /** Debug code **/ Logger.getGlobal().info(headerCells.get(i).getText());
            headerTitles.add(headerCells.get(i).getText());
        }
        return headerTitles;
    }
    
    
    /**
     * Find a single row (a list of cells) by item name in the table and item column index
     * @param itemName
     * @param itemColumnIndex
     * @return a list with the row cells, a empty list if item name not has been found
     */
    public List<WebElement> getCellsFromRow(String itemName, int itemColumnIndex){
        List<WebElement> rowCells = new ArrayList<>();
        if(itemColumnIndex >= 0){
            List<WebElement> rows = this.getRowsList();
            /** Debug code **/ Logger.getGlobal().log(Level.INFO, "Rows Size: {0}", rows.size());
            for(int j = 0; j < rows.size(); j++){
                //The list of the cells row
                rowCells = rows.get(j).findElements(cellTag);
                /** Debug code **/ Logger.getGlobal().log(Level.INFO, "Elemento: {0}", rowCells.get(itemColumnIndex).getText());
                //If the content of the cell in the corresponding column equal the column name, return the row
                if(rowCells.get(itemColumnIndex).getText().equalsIgnoreCase(itemName)){
                    /** Debug code **/ Logger.getGlobal().info("Cell founded!!!");
                    return rowCells;
                }
            }
            /** Debug code **/ Logger.getGlobal().info("Can't find the element in the table.");
            return rowCells;
        }else{
            /** Debug code **/ Logger.getGlobal().info("One or all columns has not been found in the table.");
        }
        return rowCells;
    }
    
    /**
     * Find a single row (a list of cells) by item name in the table and item column name
     * @param itemName
     * @param itemColumn
     * @return a list with the row cells, a empty list if item name not has been found
     */
    public List<WebElement> getCellsFromRow(String itemName, String itemColumn){
        int itemColumnIndex = this.findColumnIndex(itemColumn);
        return this.getCellsFromRow(itemName, itemColumnIndex);
    }
    
    
    /**
     * Find a rows by item name in the table
     * @param itemName
     * @param itemColumn
     * @return a list with the rows, a empty list if item name not has been found
     */
    public List<WebElement> findRowList(String itemName, String itemColumn){
        List<WebElement> rows = new ArrayList<>();
        int itemColumnIndex = this.findColumnIndex(itemColumn);
        if(itemColumnIndex >= 0)
        {
            List<WebElement> allRows = this.getRowsList();
            /** Debug code **/ Logger.getGlobal().log(Level.INFO, "All Rows Size: {0}", allRows.size());
            for(WebElement row : allRows){
                /** Debug code **/ Logger.getGlobal().info("sto cercando le celle...");
                List<WebElement> cells = row.findElements(cellTag);
                /** Debug code **/ Logger.getGlobal().log(Level.INFO, "Elemento: {0}; expected: " + itemName, cells.get(itemColumnIndex).getText());
                //If the content of the cell in the corresponding column equal the column name, return the row
                if(cells.get(itemColumnIndex).getText().equalsIgnoreCase(itemName)){
                    rows.add(row);
                    /** Debug code **/ Logger.getGlobal().info("Element added!!!");
                }
                /** Debug code **/ Logger.getGlobal().info("Element NOT added!!!");
            }
        }
        else
        {
            /** Debug code **/ Logger.getGlobal().info("One or all columns has not been found in the table.");
        }
        return rows;
    }
    
    
    /**
     * The number of columns
     * @return the number of columns
     */
    public int columnsNumber(){
        return this.getHeaderList().size();
    }
    
    
    /**
     * Find the index of the column by name (starting from 0)
     * @param columnName the name of the column
     * @return (-1) if column not found, (>=0) if the column found
     */
    public int findColumnIndex(String columnName){
        List<WebElement> headers = this.getHeaderList();
        int itemColumnIndex = -1;
        /** Debug code **/ Logger.getGlobal().log(Level.INFO, "Header Size: {0}", headers.size());
        for(int i = 0; i < headers.size(); i++){
            WebElement th = headers.get(i);
            /** Debug code **/ Logger.getGlobal().log(Level.INFO, "Column: {0}", th.getAttribute(innerText));
            if(th.getAttribute(innerText).equalsIgnoreCase(columnName)){
                itemColumnIndex = i;
                /** Debug code **/ Logger.getGlobal().log(Level.INFO, "Column found! {0}", itemColumnIndex);
                break;
            }
        }
        return itemColumnIndex;
    } 
    
    
    /** 
     * Looks for a cell by item column and data column name
     * @param itemName the string to search in the table
     * @param itemColumn the column in which to search the item
     * @param dataColumn the column in which to collect the data
     * @return a WebElement represent a cell, (null) if item name not has been found
     */
    public WebElement getCell(String itemName, String itemColumn, String dataColumn){
        //Find the data columns index
        int itemColumnIndex = this.findColumnIndex(itemColumn);
        int dataColumnIndex = this.findColumnIndex(dataColumn);
        return this.getCell(itemName, itemColumnIndex, dataColumnIndex);
    }
    
    
    /** 
     * Looks for a cell by item column index and data column index
     * @param itemName the string to search in the table
     * @param itemColumnIndex the column index in which to search the item
     * @param dataColumnIndex the column index in which to collect the data
     * @return a WebElement represent a cell, (null) if item name not has been found
     */
    public WebElement getCell(String itemName, int itemColumnIndex, int dataColumnIndex){
        //Find the row
        List<WebElement> cells = this.getCellsFromRow(itemName, itemColumnIndex);
        if ((cells != null) && (dataColumnIndex >= 0)){
            return cells.get(dataColumnIndex);
        }
        return null;
    }
    
        
    
    /** 
     * Looks for a cell by item column and data column and return the kebab menù
     * @param itemName the string to search in the table
     * @param itemColumn the column in which to search the item
     * @param dataColumn the column in which to find the kebab
     * @return a Kebab in the corresponding cell, (null) if item name not has been found
     */
    public Kebab getKebabOnTable(String itemName, String itemColumn, String dataColumn){
        WebElement cell = this.getCell(itemName, itemColumn, dataColumn);
        if (cell != null){
            Kebab kebab = new Kebab(cell, cell);
            return kebab;
        }
        return null;        
    }
    
    
    /** 
     * Looks for a cells by item column and data column and return the kebabs menù on a List
     * @param itemName the string to search in the table
     * @param itemColumn the column in which to search the item
     * @param kebabColumn the column in which to find the kebab
     * @return a Kebab in the corresponding cell, (null) if item name not has been found
     */
    public List<Kebab> getKebabsOnTable(String itemName, String itemColumn, String kebabColumn){
        List<Kebab> kebabs = new ArrayList<>();
        //Find the 
        List<WebElement> foundedRows = this.findRowList(itemName, itemColumn);
        if (foundedRows.isEmpty())
            return kebabs;
        //Find the data columns index
        int kebabColumnIndex = this.findColumnIndex(kebabColumn);
        
        for(WebElement row : foundedRows)
        {
            List<WebElement> rowCells = row.findElements(cellTag);
            WebElement cell = rowCells.get(kebabColumnIndex);
            Kebab kebab = new Kebab(cell, cell);
            kebabs.add(kebab);
        }
        
        return kebabs;        
    }
    
    
    
}//end class
