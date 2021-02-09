package ca.itsd.guli.service.impl;

import ca.itsd.guli.domain.Category;
import ca.itsd.guli.dto.CategoryDTO;
import ca.itsd.guli.domain.SubCategory;
import ca.itsd.guli.repository.CategoryRepository;
import ca.itsd.guli.repository.SubCategoryRepository;
import ca.itsd.guli.service.CategoryService;
import ca.itsd.guli.util.ResultEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * this class handles all the business logic for category related requests
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SubCategoryRepository subCategoryRepository;


    /**
     * get all the categories
     * @return a list of categories
     */
    @Override
    public ResultEntity<List<CategoryDTO>> getAllCategories() {
        return getCategoriesByPid(0);
    }

    /**
     * find all the categories, or sub-categories for a main category id
     * @param parentId parent id of the sub-categories.
     *                 0: find all categories
     *                 Otherwise, find sub-categories for a main category id
     * @return a customized ResultEntity<List<CategoryDTO>>
     */
    @Override
    public ResultEntity<List<CategoryDTO>> getCategoriesByPid(Integer parentId) {
        //get the main categories
        List<Category> categories = categoryRepository.findCategoryById(0);
        //return this rCategories to the client side
        List<CategoryDTO> rCategories=new ArrayList<>();
        //check if there are categories
        if (categories.isEmpty()) {
            return ResultEntity.successWithoutData("No categories have been found!");
        }

            List<SubCategory> subCategories = null;
            //the client side is requesting sub-categories
            if (parentId != 0) {
                for (Category c:
                        categories) {
                    if(c.getCategoryId()==parentId)
                    {
                        subCategories=c.getSubCategoryList();
                    }
                }
               //check if there are sub-categories under this category
               if(subCategories==null)
               {
                   return ResultEntity.successWithoutData("No sub-categories have been found!");
               }
               else
               {
                   //convert this sub-categories to categories object
                   for(int i=0;i<subCategories.size();i++)
                   {
                       SubCategory cb=subCategories.get(i);
                       List<CategoryDTO> subDTOs=new ArrayList<>();
                       //only returns id,parentId,categoryName to the server
                       CategoryDTO c=new CategoryDTO(cb.getSubcategoryId(),
                               cb.getCategory().getCategoryId(),
                               cb.getSubCategoryName(),subDTOs);
                       rCategories.add(c);
                   }
               }
            }
            //return the main categories
            else {
                generateCategoryDTO(categories,rCategories);
            }
        return ResultEntity.successWithData(rCategories);
    }


    /**
     * generate categories to a list of categoryDTO
     * @param categories that will be converted to DTOs
     * @param rCategories a list of categoryDTO
     */
    private void generateCategoryDTO(List<Category> categories, List<CategoryDTO> rCategories)
    {
        for(int i=0;i<categories.size();i++)
        {
            Category cb=categories.get(i);
            //find subcategories for this category
            List<SubCategory> subs=cb.getSubCategoryList();
            //store the subDTOs
            List<CategoryDTO> subDTOs=new ArrayList<>();
           //check if the subcategories exist
            if(subs.size()>0)
            {
                for(int j=0;j<subs.size();j++)
                {
                    SubCategory s=subs.get(j);
                    CategoryDTO sDTO=new CategoryDTO(s.getSubcategoryId(),
                            s.getCategory().getCategoryId(),s.getSubCategoryName(),new ArrayList<>());
                    subDTOs.add(sDTO);
                }
            }
            //only returns id,parentId,categoryName to the server
            CategoryDTO c=new CategoryDTO(cb.getCategoryId(),cb.getParentId(),cb.getName(),subDTOs);
            rCategories.add(c);
        }
    }

    /**
     * add a new category
     * @param categoryDTO the new category
     * @return a customized ResultEntity<List<CategoryDTO>> after the adding operation
     */
    @Override
    public ResultEntity<List<CategoryDTO>> addCategory(CategoryDTO categoryDTO ) {
        //create the modelMapper to map a Category to a CategoryDTO
        ModelMapper modelMapper = new ModelMapper();
        //get the main category id
        Integer parentId=categoryDTO.getParentId();
        //get the name
        String categoryName=categoryDTO.getName();
        CategoryDTO dto;

        //1.add a main category
        if(parentId==0)
        {
            Category c=new Category(parentId,categoryName);
            categoryRepository.save(c);
            //find the newly added Category object
            Category newCategory=categoryRepository.findCategoryByName(categoryName);
            //convert the entity to dto
             dto=modelMapper.map(newCategory,CategoryDTO.class);
        }
        //2.add a sub-category to this parent id
        else
        {
            //1. find this main category
            Optional<Category> c=categoryRepository.findById(parentId);
            Category main=c.get();//returns a Category object
           //2.create a new sub-category
            SubCategory sc=new SubCategory();
            sc.setSubCategoryName(categoryName);
            //3.connect the main category and the new sub-category
            sc.setCategory(main);
            main.getSubCategoryList().add(sc);
            //add the new category under the parent id
            subCategoryRepository.save(sc);
            //find the newly added subcategory
            SubCategory newSub=subCategoryRepository.findByCategoryName(categoryName);
            dto=modelMapper.map(newSub,CategoryDTO.class);
        }
        return getAllCategories();
    }


    /**
     * update a category or sub-category
     * @param categoryDTO category or sub-category that will be updated
     * @return a customized ResultEntity<List<CategoryDTO>> after the updating operation
     */
    @Override
    public ResultEntity<List<CategoryDTO>> updateCategory(CategoryDTO categoryDTO) {
        //get the parentId, id and categoryName
        int parentId=categoryDTO.getParentId();
        int id=categoryDTO.getId();
        String categoryName=categoryDTO.getName();
        //update main category
        if(parentId==0)
        {
            categoryRepository.updateCategory(id,categoryName);
        }
        //update sub-category
        else
        {
            subCategoryRepository.updateSubCategory(parentId,id,categoryName);
        }
        return getAllCategories();
    }
}
