package ca.itsd.guli.service;


import ca.itsd.guli.dto.PageProductDTO;;
import ca.itsd.guli.util.ResultEntity;


/**
 * this interface defines all the product related methods
 */
public interface ProductService {

    /**
     *get all products
     * @param pageNum page number of the products
     * @param pageSize the number of size in a page
     * @return a customized  ResultEntity<PageProductDTO>
     */
    ResultEntity<PageProductDTO>  getAllProducts(Integer pageNum,Integer pageSize);

    /**
     *search products based on certain conditions
     *  @param pageNum page number of the products
     * @param pageSize the number of size in a page
     * @param searchType productName or desc
     * @param searchParam search parameters
     * @return @return a customized  ResultEntity<PageProductDTO>
     */
    ResultEntity<PageProductDTO>  searchProducts(Integer pageNum,Integer pageSize,String searchType,String searchParam);
}
