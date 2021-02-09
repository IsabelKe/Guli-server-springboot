package ca.itsd.guli.service.impl;

import ca.itsd.guli.domain.SubCategory;
import ca.itsd.guli.repository.SubCategoryRepository;
import ca.itsd.guli.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * this class handles all the business logic for sub-category related requests
 */
@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    /**
     * find all sub-categories
     * @return a list of sub-categories
     */
    @Override
    public List<SubCategory> findAllSubcategories() {

        return subCategoryRepository.findAll();
    }
}
