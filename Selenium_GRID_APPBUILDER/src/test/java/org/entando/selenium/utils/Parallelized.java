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

import org.junit.runners.Parameterized;
import org.junit.runners.model.RunnerScheduler;
 
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
 
public class Parallelized extends Parameterized {
 
    private static class ThreadPoolScheduler implements RunnerScheduler {
        private ExecutorService executor;
 
        //You can set number of parallel threads in this method.
        //I set 5 and our grid will run 5 parallel test execution.
        public ThreadPoolScheduler() {
            String threads = System.getProperty("junit.parallel.threads", "5");
            int numThreads = Integer.parseInt(threads);
            executor = Executors.newFixedThreadPool(numThreads);
        }
 
        //@Override
        @Override
        public void finished() {
            executor.shutdown();
            try {
                executor.awaitTermination(10, TimeUnit.MINUTES);
            } catch (InterruptedException exc) {
                throw new RuntimeException(exc);
            }
        }
 
        //@Override
        @Override
        public void schedule(Runnable childStatement) {
            executor.submit(childStatement);
        }
    }
 
    public Parallelized(Class<?> klass) throws Throwable {
        super(klass);
        setScheduler(new ThreadPoolScheduler());
    }
}