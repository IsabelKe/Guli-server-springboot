package ca.itsd.guli;

import ca.itsd.guli.domain.Category;
import ca.itsd.guli.domain.SubCategory;
import ca.itsd.guli.repository.CategoryRepository;
import ca.itsd.guli.repository.ProductRepository;
import ca.itsd.guli.repository.SubCategoryRepository;
import ca.itsd.guli.service.CategoryService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class GuliApplicationTests {

   @Autowired
   private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private ProductRepository productRepository;


    @Test
    public void testCategory()
    {
        //add a sub-category to the parent category
        //1.find the main category
        Optional<Category> c=categoryRepository.findById(1);
        Category main;
        if(c!=null)
        {
           main=c.get();//returns a Category object

            //2.connect the main category and the new sub-category
            SubCategory sc=new SubCategory();
            sc.setSubCategoryName("Non-fiction");
            sc.setCategory(main);
            main.getSubCategoryList().add(sc);

            //add the new category to the main category 1
            subCategoryRepository.save(sc);
        }

    }

    @Test
    public void testProduct()
    {
        System.out.println(productRepository.findAll().size()) ;
    }
}
