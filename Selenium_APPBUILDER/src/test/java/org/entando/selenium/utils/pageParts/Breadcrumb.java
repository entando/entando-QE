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
 * This class represents the breadcrumb
 * 
 * @version 1.01
 */
public class Breadcrumb {
    public WebElement breadcrumb;
    /*
    Ways to find elements
    */
    private static final By crumbTag = By.tagName("li");

    public Breadcrumb(WebElement breadcrumb) {
        this.breadcrumb = breadcrumb;
    }
    
    
    public List<WebElement> getCrumbsList(){
        return  breadcrumb.findElements(crumbTag);
    }
    
    
    public List<String> getCrumbsNameList(){
        List<WebElement> crumbList = this.getCrumbsList();
        List<String> crumbNameList = new ArrayList<>();
        crumbList.forEach((crumb) -> {
            crumbNameList.add(crumb.getText());
        });
        return crumbNameList;
    }
    
    
    public String getLastCrumb(){
        List<WebElement> crumbList = this.getCrumbsList();
        return crumbList.get(crumbList.size()-1).getText();
    }
    
    
    public int getNumberOfCrumbs(){
        return this.getCrumbsList().size();
    }
    
}//end class
