package org.entando.selenium.smoketests;

public enum SmokeTestUser {
    ADMIN("admin", "adminadmin"),
    SMOKE_TEST_USER("smoketestuser", "adminadmin");

    SmokeTestUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
