package ca.itsd.guli.service;

import ca.itsd.guli.domain.Category;
import ca.itsd.guli.dto.CategoryDTO;
import ca.itsd.guli.util.ResultEntity;

import java.util.List;

/**
 * this interface defines all the category related methods
 */
public interface CategoryService {
    /**
     * get all the categories
     * @return a list of categories
     */
    ResultEntity<List<CategoryDTO>> getAllCategories();

    /**
     * find all the categories, or sub-categories for a main category id
     * @param parentId parent id of the sub-categories.
     *                 0: find all categories
     *                 Otherwise, find sub-categories for a main category id
     * @return a customized ResultEntity<List<CategoryDTO>>
     */
     ResultEntity<List<CategoryDTO>> getCategoriesByPid(Integer parentId);

    /**
     * add a new category
     * @param categoryDTO the new category
     * @return a customized ResultEntity<List<CategoryDTO>> after the adding operation
     */
    ResultEntity<List<CategoryDTO>> addCategory(CategoryDTO categoryDTO);

    /**
     * update a category or sub-category
     * @param categoryDTO category or sub-category that will be updated
     * @return a customized ResultEntity<List<CategoryDTO>> after the updating operation
     */
    ResultEntity<List<CategoryDTO>> updateCategory(CategoryDTO categoryDTO);
}
