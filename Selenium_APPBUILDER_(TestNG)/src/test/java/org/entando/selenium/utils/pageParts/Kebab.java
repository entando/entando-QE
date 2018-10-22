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
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 *  This class represents the drop-down menu present in every item (row) of a table
 */
public class Kebab {
    private WebElement allActions;
    private WebElement clickable;
    /*
    Ways to find elements
    */
    By clickableTag = By.tagName("button");
    By allActionsTag = By.tagName("ul");
    By actionTag = By.tagName("li");


    public Kebab(){
    }

    public Kebab(WebElement clickable, WebElement allActions){
        this.clickable = clickable.findElement(clickableTag);
        this.allActions = allActions.findElement(allActionsTag);
    }

    public WebElement getClickable() {
        return clickable;
    }

    public void setClickable(WebElement clickable) {
        this.clickable = clickable.findElement(clickableTag);
    }
    
    public WebElement getAllActionsMenu() {
        return allActions;
    }
    
    public void setActions(WebElement allActions) {
        this.allActions = allActions.findElement(allActionsTag);
    }
    
    /**
     * A List of menù actions
     * @return List of menù actions
     */
    public List<WebElement> getActionsList() {
        List<WebElement> actionsList = allActions.findElements(actionTag);
        return actionsList;
    }
    
    /**
     * A String List of menù actions
     * @return List of menù actions
     */
    public List<String> getActionsStringList() {
        List<WebElement> actionsList = this.getActionsList();
        List<String> actionsStringList = new ArrayList<>();
        actionsList.forEach((action) -> {
            actionsStringList.add(action.getText());
        });
        return actionsStringList;
    }
    
    
    /**
     * Find the action in the kebab menù
     * @param actionName the action name to find
     * @return the action element
     */
    public WebElement getAction(String actionName){
        return allActions.findElement(By.linkText(actionName));
    }
}
