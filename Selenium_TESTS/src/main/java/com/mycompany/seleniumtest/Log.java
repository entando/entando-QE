/**
 * This class contains all logging methods for the project.  
 * 
 * Note: Requires log4j2.xml configuration file.
 * 
 * Note: Log4j2 configuration folder must be added to any test case etc. run configurations
 * for logging to work.
 * 
 * @author mswanton
 */

package com.mycompany.seleniumtest;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Log {
    private static final Logger logger = LogManager.getLogger(Log.class
            .getName());

    public static void startTestCase(String testCaseName) {
        logger.info("");
        logger.info("********** START **********");
        logger.info("Test Case: " + testCaseName);
        logger.info("***************************");
    }

    public static void endTestCase() {
        logger.info("*********** END ***********");
        logger.info("");
    }

    public static void trace(String message) {
        logger.trace(message);
    }

    public static void debug(String message) {
        logger.debug(message);
    }

    public static void info(String message) {
        logger.info(message);
    }

    public static void warn(String message) {
        logger.warn(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void fatal(String message) {
        logger.fatal(message);
    }

}
