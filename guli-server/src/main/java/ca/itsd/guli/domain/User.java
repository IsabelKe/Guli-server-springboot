package ca.itsd.guli.domain;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * User entity class that will be mapped to users table in the database
 */
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "login_acct")
    private String loginAccount;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_pswd")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "create_time")
    private String createTime;

    //a user may have many roles, a role may be assigned to many users
    @ManyToMany
    @JoinTable(
            name = "user_role",//the bridge table
            /*
            The joinColumn attribute will connect to the owner side of the relationship,
            name = "user_id" is the column name inside the bridge table
             */
            //
            joinColumns = @JoinColumn(name = "user_id"),
            /*
            and the inverseJoinColumn to the other side
            name = "role_id" is the column name inside the bridge table
             */
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;


}
