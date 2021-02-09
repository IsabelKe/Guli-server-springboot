package ca.itsd.guli.dto;


import lombok.*;

import java.io.Serializable;
import java.util.List;


/**
 * a customized category dto that will be returned to the client-side
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryDTO implements Serializable {
    private Integer id;//category id
    private Integer parentId;//main category id
    private String name;//category name
    private List<CategoryDTO> subCategories;//sub-categories of this category
}
