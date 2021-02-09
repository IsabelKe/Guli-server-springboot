package ca.itsd.guli.controller;



import ca.itsd.guli.dto.PageProductDTO;
import ca.itsd.guli.service.ProductService;
import ca.itsd.guli.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * RestController to handle all product related requests
 */
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;


    /**
     * get all products
     * @param pageNum of the products that will be returned
     * @param pageSize the number of products for this page
     * @return a customized ResultEntity<PageProductDTO>
     */
    @GetMapping("")
    public ResultEntity<PageProductDTO> getAllProducts(
            @RequestParam(name = "pageNum", required = true) Integer pageNum,
            @RequestParam(name = "pageSize", required = true) Integer pageSize
            )
    {
        return productService.getAllProducts(pageNum,pageSize);
    }


    /**
     * find products that meet the conditions
     * @param pageNum of the products that will be returned
     * @param pageSize the number of products for this page
     * @param productName the product name
     * @param desc description of products
     * @return a customized ResultEntity<PageProductDTO>
     */
    @GetMapping("/search")
    public ResultEntity<PageProductDTO> searchProducts(
            @RequestParam(name = "pageNum", required = true) Integer pageNum,
            @RequestParam(name = "pageSize", required = true) Integer pageSize,
            @RequestParam(name = "productName", required =false) String productName,
            @RequestParam(name = "desc", required =false) String desc)
    {
        String searchType, searchParam;
        if(productName==null ||productName.isEmpty())
        {
            searchType="desc";
            searchParam=desc;
        }
        else
        {
            searchType="productName";
            searchParam=productName;
        }
//        System.out.println(pageNum+"=========="+pageSize);
//        System.out.println(searchType+"=========="+searchParam);
        return productService.searchProducts(pageNum,pageSize,searchType,searchParam);
    }
}
