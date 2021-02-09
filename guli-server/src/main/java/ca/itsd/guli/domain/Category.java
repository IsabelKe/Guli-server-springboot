package ca.itsd.guli.domain;


import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Category entity class, that will be mapped to category table in the MySQL database
 */
@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category {

    @Id//this is a primary key
    //primary key generating strategies
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer categoryId;
    @Column(name = "parent_id")
    //parent id of this category
    private Integer parentId;
    //category name
    @Column(name = "name", unique = true)
    private String name;

    /*
    mappedBy:the value of mappedBy is the name of the association-mapping attribute
    on the owning side.
    cascade = CascadeType.ALL

    fetch = FetchType.LAZY:
        to load the subCategoryList on demand when the getSubCategoryList() has been called.

     */
    @OneToMany(mappedBy = "category",
                cascade = CascadeType.ALL,
                fetch = FetchType.LAZY)
    private List<SubCategory> subCategoryList;


    /*
    one category can have many products
     */
    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Product> products;

    /*
    customized constructor
     */
    public Category(Integer parentId, String name) {
        this.parentId = parentId;
        this.name = name;
    }


}
