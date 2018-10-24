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

import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

/**
 * This class perform a test to publish/unpublish a page in the page tree
 * 
 * @version 1.00
 */
public class SeleniumTestExecutionListener extends AbstractTestExecutionListener{

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        testContext.getApplicationContext().getBean(TestScope.class).reset();
    }
    
}
