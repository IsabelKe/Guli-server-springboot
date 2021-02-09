package ca.itsd.guli.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * product class
 */
@Entity
@Table(name="product")
@NoArgsConstructor
@Data
public class Product implements Serializable{

    @Id//mark this column is the primary key
    //primary key generating strategies
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;
    //product name
    @Column(name = "product_name")
    private String productName;
    @Column(name = "imgs")
    //product pictues
    private String imgs;
    //product description
    @Column(name = "p_desc")
    private String  desc;
    //product price
    @Column(name = "price")
    private double  price;
    //product details
    @Column(name = "detail")
    private String  detail;
    //product status: 0 is for sell, 1 is not for sell
    @Column(name = "status")
    private Integer  status;

    /*
    a product belongs to one category
     */
    @ManyToOne()
    @JoinColumn(name = "c_id",referencedColumnName = "category_id")
    private Category category;

    /*
   a product belongs to one sub-category
    */
    @ManyToOne()
    @JoinColumn(name = "sub_id",referencedColumnName = "sub_id")
    private SubCategory subCategory;
}
