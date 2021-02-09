package ca.itsd.guli.dto;


import lombok.*;
import java.util.List;

/**
 *a customized product dto that has the current page number, page size, total items,
 * total pages and a list of categories for a specific page number.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageProductDTO {
    private Integer pageNum;//the page number
    private Integer pageSize;//page size
    private Long total;//total items
    private Integer pages;//pages
   private List<ProductDTO> categoryList;//categories
}
