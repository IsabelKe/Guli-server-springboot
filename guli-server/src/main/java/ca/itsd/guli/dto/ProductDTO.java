package ca.itsd.guli.dto;



import lombok.*;

/**
 * a customized product dto that will be returned to the client-side
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Integer productId;//product id
    private String productName;//product name
    private String imgs;//product pictures
    private String  desc;//product description
    private double  price;//product price
    private String  detail;//product detail
    private Integer  status;//product status
    private Integer categoryId;//category id of this product
    private Integer subId;//sub-category id of this product
//    private Category category;
//    private SubCategory subCategory;
}
