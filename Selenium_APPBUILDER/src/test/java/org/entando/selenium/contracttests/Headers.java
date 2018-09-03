package org.entando.selenium.contracttests;

import java.util.HashMap;

public class Headers extends HashMap<String,String> {
    public Headers(String name, String value){
        and(name,value);
    }
    public Headers and(String name, String value){
        super.put(name, value);
        return this;
    }
}
