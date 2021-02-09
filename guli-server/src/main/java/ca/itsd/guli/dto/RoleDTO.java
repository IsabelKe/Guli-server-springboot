package ca.itsd.guli.dto;


import lombok.*;


import java.io.Serializable;
import java.util.List;


/**
 * A customized dto for the role class.
 * It formats the role object that will be returned to the client-side
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleDTO implements Serializable {
    private Integer roleId;//role id
    private String roleName;//role name
    private String createTime;//role creating time
    private String authTime;//when manges permissions of this role
    private String authName;//who manges permissions of this role
    private List<String> menus;//a list of permissions/menus this user has
}
