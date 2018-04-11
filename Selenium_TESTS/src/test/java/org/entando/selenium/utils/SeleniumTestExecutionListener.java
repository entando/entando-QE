/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entando.selenium.utils;

import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

/**
 *
 * @author leobel
 */
public class SeleniumTestExecutionListener extends AbstractTestExecutionListener{

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        testContext.getApplicationContext().getBean(TestScope.class).reset();
    }
    
}
