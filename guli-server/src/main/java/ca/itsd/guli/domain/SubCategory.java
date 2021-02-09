package ca.itsd.guli.domain;


import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * SubCategory entity class that will be mapped to sub_category
 * in the database
 */
@Entity
@Table(name = "sub_category")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SubCategory {
    @Id//mark this column is a primary key
    //primary key generating strategies
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_id")
    private Integer subcategoryId;
    //sub-category name
    @Column(name = "sub_category_name", unique = true)
    private String subCategoryName;


    @ManyToOne()// a category may not have a subcategory
    /*
    @JoinColumn defines this entity has a foreign key parent_id, it corresponding to
    the id of category entity
     */
    @JoinColumn(name ="parent_id",referencedColumnName = "category_id")
    private Category category;

    @OneToMany(
            mappedBy = "subCategory",
            //cascade =CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    //a subcategory may have many products
    private List<Product> products;

    /*
    customized constructor
     */
    public SubCategory(String subCategoryName, Category category) {
        this.subCategoryName = subCategoryName;
        this.category = category;
    }
}
