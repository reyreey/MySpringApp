package ir.reyreey.myspringexample.controller.customization;

import jakarta.validation.constraints.NotBlank;

/**
 * @author : Reyreey
 * @mailto : dehghan.reyhaneh179@gmail.com
 * @created : 8/2/2024, Friday
 **/
public class UserAuthenticationInfo {
    @NotBlank(message = "username cannot be blank!")
    private String username;
    @NotBlank(message = "password cannot be blank!")
    private String password;

    public UserAuthenticationInfo() {
    }

    public UserAuthenticationInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
