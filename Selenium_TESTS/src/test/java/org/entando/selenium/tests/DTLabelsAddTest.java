/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entando.selenium.tests;

import org.entando.selenium.pages.DTLabelsAndLanguagesPage;
import org.entando.selenium.utils.FunctionalTest;
import org.entando.selenium.utils.Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author leobel
 */
public class DTLabelsAddTest extends FunctionalTest {
    
    @Autowired
    DTLabelsAndLanguagesPage dTLabelsAndLanguagesPage;
    
    @Autowired
    Utils util;
    
    @Test
    public void test(){
        login();
        goTo("Configuration", "Labels and Languages");
        
        String labelCode = "test";
        String label = "test";
        
        dTLabelsAndLanguagesPage.getLabelsTab().click();
        util.waitUntilIsVisible(driver, dTLabelsAndLanguagesPage.getLabelsTable());
        int size = util.getTableCount(dTLabelsAndLanguagesPage.getLabelsTable());
        
        dTLabelsAndLanguagesPage.getAddLabelButton().click();
    }
    
}
