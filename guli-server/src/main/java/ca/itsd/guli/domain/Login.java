package ca.itsd.guli.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * A login class that has username and password and a list of role names
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Login {
    private String username;//login username
    private String password;//login password
    private List<String> roleNames;//a list of role names

    //customized the constructor
    public Login(String username, List<String> roleNames) {
        this.username = username;
        this.roleNames = roleNames;
    }
}
