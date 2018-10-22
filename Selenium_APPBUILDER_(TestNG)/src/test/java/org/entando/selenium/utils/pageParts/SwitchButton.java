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

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * This class represent a Switch Button
 * 
 * @version 1.01
 */
public class SwitchButton {
    
    public String switchOffTag = "switch-off";
    public String switchOnTag = "switch-on";
    
    public WebElement switchButton;
    
    public SwitchButton(WebElement switchButton) {
        this.switchButton = switchButton;
    }
        
    public void setOn(){
        if (switchButton.getAttribute("class").contains(switchOffTag))
        {
            switchButton.click();
        }
    }
    
    public void setOff(){
        if (switchButton.getAttribute("class").contains(switchOnTag))
        {
            switchButton.click();
        }
    }
    
    public boolean isOn(){
        if (switchButton.getAttribute("class").contains(switchOnTag))
        {
            return true;
        }
        return false;
    }
    
}
