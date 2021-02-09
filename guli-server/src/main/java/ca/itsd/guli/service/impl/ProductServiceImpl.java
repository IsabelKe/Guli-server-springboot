package ca.itsd.guli.service.impl;


import ca.itsd.guli.domain.Product;
import ca.itsd.guli.dto.PageProductDTO;
import ca.itsd.guli.dto.ProductDTO;
import ca.itsd.guli.repository.ProductRepository;
import ca.itsd.guli.service.ProductService;
import ca.itsd.guli.util.ResultEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import javax.persistence.criteria.*;
import java.util.List;

/**
 *this class handles all business logic for all product related requests
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    private static ModelMapper modelMapper = new ModelMapper();


    /**
     * configure the modelMapper inside the constructor
     */
   public ProductServiceImpl()
   {
       configureMM();
   }

    /**
     * configures the modelMapper
     */
    private void configureMM()
    {
        //configure the fields from dto and fields from entity to be matched
        PropertyMap<Product, ProductDTO> propertyMap=
                new PropertyMap<Product, ProductDTO>() {
                    @Override
                    protected void configure() {
                        //match categoryId from dto to categoryId from entity
                        map().setCategoryId(source.getCategory().getCategoryId());
                        //match subId from dto to subcategoryId from entity
                        map().setSubId(source.getSubCategory().getSubcategoryId());
                    }
                };
        //check if the propertyMap exists or not
        TypeMap<Product, ProductDTO> typeMap=modelMapper.getTypeMap(Product.class, ProductDTO.class);
        if(typeMap==null)
        {
            //add the PropertyMap mapping to the ModelMapper instance
           modelMapper.addMappings(propertyMap);
           //fields from entity class and dto have to be strictly matched
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        }
    }

    /**
     *1. converts a list entities to a list dto
     *
     * @param
     */
    private PageProductDTO convert( Page<Product> page)
    {

        //1. converts a list entities to a list dto
        List<ProductDTO> dto= modelMapper.map(page.getContent(),
                                              new TypeToken<List<ProductDTO>>() {}.getType());

        //map this pageInfo to a PageProductDTO
      // PageProductDTO pDTO=modelMapper.map(page,PageProductDTO.class);
        PageProductDTO pDTO=new PageProductDTO();
        pDTO.setPageNum(page.getNumber()+1);
        pDTO.setPages(page.getTotalPages());
        pDTO.setPageSize(page.getNumberOfElements());
        pDTO.setTotal(page.getTotalElements());
        pDTO.setCategoryList(dto);
        return pDTO;
    }


    /**
     *get all products
     * @param pageNum page number of the products
     * @param pageSize the number of size in a page
     * @return a customized  ResultEntity<PageProductDTO>
     */
    @Override
    public ResultEntity<PageProductDTO> getAllProducts(Integer pageNum,Integer pageSize) {
        Pageable pageable=PageRequest.of(pageNum-1,pageSize);
        //find all the products
       Page<Product> page=productRepository.findAll(pageable);

       //convert page to a PageProductDTO
       if(page!=null && page.hasContent())
       {
           PageProductDTO pDTO=convert(page);
           return ResultEntity.successWithData(pDTO);
       }
       //no data, returns a message back to the client-side
       else {
           return ResultEntity.successWithoutData("No data found");
       }
    }

    /**
     *search products based on certain conditions
     *  @param pageNum page number of the products
     * @param pageSize the number of size in a page
     * @param searchType productName or desc
     * @param searchParam search parameters
     * @return @return a customized  ResultEntity<PageProductDTO>
     */
    @Override
    public ResultEntity<PageProductDTO> searchProducts(
            Integer pageNum, Integer pageSize, String searchType, String searchParam) {

        //create a pageable object
        Pageable pageable=PageRequest.of(pageNum-1,pageSize);
        //create the conditional query
        Specification<Product> specification = new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                /*
                searchType has to be productName or desc,
                otherwise, the following code needs to be changed to check what the searchType value is
                 */
                //get the compare field
                System.out.println("searchType--------------------------------"+searchType);
                Path<Object> field=root.get(searchType);
                //generate the query condition
                Predicate p=cb.like(field.as(String.class),"%"+searchParam+"%");
                return p;
            }
        };

        //search products
        Page<Product> page=productRepository.findAll(specification,pageable);
        //convert page to a PageProductDTO
        if(page!=null && page.hasContent())
        {
            PageProductDTO pDTO=convert(page);
            return ResultEntity.successWithData(pDTO);
        }
        //no data, returns a message back to the client-side
        else {
            return ResultEntity.successWithoutData("No data found");
        }
    }
}
