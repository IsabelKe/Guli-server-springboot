package ca.itsd.guli.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 *represents resources(menus) that can be accessed by different roles
 */
@Entity//indicate this class is a JPA entity.
// used to specify the name of the database table
// that should be mapped to this entity.
@Table(name = "menu")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Menu {
    //this column is a primary key
    @Id
    //defines the primary key generation strategy
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Integer menuId;
    //menu name
    @Column(name = "menu_name")
    private String menuName;
    //menu create time
    @Column(name = "create_time")
    private Date createTime;

    /*
    a menu will be accessed by many different roles
     */
    @ManyToMany(mappedBy = "menus",fetch = FetchType.LAZY)
    private List<Role> roles;

}
