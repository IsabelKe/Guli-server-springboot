package ca.itsd.guli.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * a customized user dto that will be returned to the client-side
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
    private Integer id;//user id
    private String loginAccount;//login account
    private String userName;//user name
    private String email;//email
    private String createTime;//creating time
    private List<String> roleNames;//a list of role names
}
