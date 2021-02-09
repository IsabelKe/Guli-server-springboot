package ca.itsd.guli.domain;



import lombok.*;
import javax.persistence.*;
import java.util.List;

/**
 * represent a user role
 */
@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Role {

    @Id//this is a primary key
    //primary key generating strategies
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId;
    //role name
    @Column(name = "role_name")
    private String roleName;
    //role creating time
    @Column(name = "create_time")
    private String createTime;
    //authenticating time
    @Column(name = "auth_time")
    private String authTime;
    //who manages resources of this role
    @Column(name = "auth_name")
    private String authName;

    //a user may have many roles, a role may be assigned to many users
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    //one role could access to many resources, a resource could be assigned to many roles
   //@JsonIgnore
    /*
     * In a bidirectional relationship, the @ManyToMany annotation is defined in both entities
     * but only one entity can own the relationship.
     * Role is the owner of this this bidirectional relationship
     */
    @ManyToMany(
//            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    //@JoinTable annotation defines the join table between two entities
    @JoinTable(
            name = "role_menu",//the bridge table name
            joinColumns = @JoinColumn(name = "role_id"),//connect to the owner side
            inverseJoinColumns =  @JoinColumn(name = "menu_id")//connect to the other side
    )
    private List<Menu> menus;
}
