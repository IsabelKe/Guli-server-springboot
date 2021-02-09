package ca.itsd.guli.service;

import ca.itsd.guli.domain.SubCategory;


import java.util.List;


/**
 * this interface defines all the sub-category related methods
 */
public interface SubCategoryService {
    /**
     * find all sub-categories
     * @return a list of sub-categories
     */
     List<SubCategory> findAllSubcategories();
}
