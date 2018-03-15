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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;

public class Utils {

    public void insertInField(WebElement webelement, String content) {
        webelement.click();
        webelement.clear();
        webelement.sendKeys(content);
    }

    public void selectSetByValue(WebElement webelement, String selectValue) {
        Select select = new Select(webelement);
        select.selectByVisibleText(selectValue);
    }

    public List fetchHeaderTitles(WebElement tableHeader) {

        List< WebElement> headerElements = tableHeader.findElements(By.tagName("th"));
        int rows_count = headerElements.size();

        List<String> fetchedHeaderTitles = new ArrayList<>();

        for (int i = 0; i < rows_count; i++) {
            System.out.println("Header is : " + headerElements.get(i).getText());
            fetchedHeaderTitles.add(headerElements.get(i).getText());
        }

        return fetchedHeaderTitles;

    }

    public int numberOfRowsInTable(WebElement tableBody) {
        List< WebElement> rows_table = tableBody.findElements(By.tagName("tr"));
        int rows_count = rows_table.size();
        System.out.println("number of rows: " + rows_count);
        return rows_count;
    }

    public int numberOfColumnsInTable(WebElement tableBody) {
        int columns_count = 0;

        List< WebElement> rows_table = tableBody.findElements(By.tagName("tr"));
        int rows_count = rows_table.size();

        for (int row = 0; row < rows_count; row++) {

            List< WebElement> cell = rows_table.get(row).findElements(By.tagName("td"));
            //To calculate no of columns (cells). In that specific row.
            columns_count = cell.size();

        }
        System.out.println("number of columns: " + columns_count);
        return columns_count;

    }

    public Boolean checkButtonPresenceByName(WebDriver driver, String buttonName) {
        String buttonLocator = String.format("//button[contains(.,'%s')]", buttonName);

        try {
            return driver.findElement(By.xpath(buttonLocator)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }

        //WebElement button = driver.findElement(By.xpath(buttonLocator));
    }

    public WebElement getButtonElementByName(WebDriver driver, String buttonName) {
        String buttonLocator = String.format("//button[contains(.,'%s')]", buttonName);
        return driver.findElement(By.xpath(buttonLocator));

    }

    public int selectKebabActionOnTable(WebElement tableHeader, WebElement tableBody, String ColumnTitle, String SearchValue, String Action) {
        int columns_count = 0;

        //Lista di tutte le celle del header
        List<WebElement> tableHeaderCells = tableHeader.findElements(By.tagName("th"));
        int numOfColumns = tableHeaderCells.size();

        List<String> tableHeadersTitles = new ArrayList<>();

        for (int i = 0; i < numOfColumns; i++) {
            tableHeadersTitles.add(tableHeaderCells.get(i).getText());
        }

        //indice della colonna che contiene il valore assegnato
        int indexOfColumn = tableHeadersTitles.indexOf(ColumnTitle);
        int indexOfActionsColumn = tableHeadersTitles.indexOf("Actions");

        List< WebElement> rows_table = tableBody.findElements(By.tagName("tr"));

        int rows_count = rows_table.size();

        for (int row = 0; row < rows_count; row++) {

            List< WebElement> cell = rows_table.get(row).findElements(By.tagName("td"));

            columns_count = cell.size();
            //restituisce le celle della colonna indicata in indexOfColumn
            if (cell.get(indexOfColumn).getText().trim().equalsIgnoreCase(SearchValue)) {

                //clicca il corrispondente kebab
                cell.get(indexOfActionsColumn).click();

                System.out.println(" the kebab has been found");

                WebElement ul = cell.get(indexOfActionsColumn).findElement(By.tagName("ul"));
                List<WebElement> li = ul.findElements(By.tagName("li"));

                List<String> rawavailableActionsStrings = new ArrayList<>();
                List<String> cleanavailableActionsStrings = new ArrayList<>();

                for (WebElement actionEl : li) {
                    String cleanActionString = new String();
                    System.out.println("azione: " + actionEl.getText());
                    rawavailableActionsStrings.add(actionEl.getText());

                    if (actionEl.getText().contains(" ")) {
                        cleanActionString = actionEl.getText().substring(0, actionEl.getText().indexOf(" ")).trim();
                        System.out.println("stringa ripulita: " + cleanActionString);
                    } else {
                        cleanActionString = actionEl.getText().trim();
                        System.out.println("stringa originale: " + cleanActionString);
                    }
                    cleanavailableActionsStrings.add(cleanActionString);

                }

                //Get the index of the available action corresponding to the given parameter
                int indexOfActionToTrigger = cleanavailableActionsStrings.indexOf(Action);

                System.out.println("Index is: " + indexOfActionToTrigger);

                if (indexOfActionToTrigger != -1) {
                    WebElement action = li.get(indexOfActionToTrigger);
                    action.click();
                    break;
                } else {
                    assert false : "The action " + Action + " has not been found in the list of actions";
                }

            } else {

                assert false : "The value " + SearchValue + " has not been found in the column " + ColumnTitle;
            }

            //System.out.println("I valori sono: " + cell.get(indexOfColumn).getText());
        }
        //System.out.println("number of columns: " + columns_count);
        return columns_count;

    }

    //WORK IN PROGRESS DA RIVEDERE E COMPLETARE
    public int firstColumnValues(WebElement tableBody) {
        int columns_count = 0;

        List< WebElement> rows_table = tableBody.findElements(By.tagName("tr"));
        int rows_count = rows_table.size();

        for (int row = 0; row < rows_count; row++) {

            List< WebElement> cell = rows_table.get(row).findElements(By.tagName("td"));
            for (WebElement we : cell) {
                we.findElement(By.tagName("a"));
            }

        }
        System.out.println("number of columns: " + columns_count);
        return columns_count;

    }

}//class close

