package ca.itsd.guli.controller;


import ca.itsd.guli.dto.CategoryDTO;
import ca.itsd.guli.service.CategoryService;
import ca.itsd.guli.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Category RestController
 *
 */
//configure the cross origin
@CrossOrigin(origins = "*",maxAge = 3600)
/*
    1. create restful web services
 *  2. take care of mapping request data to the defined request handler methods
 */
@RestController
//the api endpoints for category
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * get the categories or the sub-categories of a main category
     * @param parentId
     * @return
     */
    @GetMapping("")
    public ResultEntity<List<CategoryDTO>> getAllCategories(
            @RequestParam(value = "parentId", required = true) Integer parentId) {
        return categoryService.getCategoriesByPid(parentId);
    }


    /**
     * add a new category under this parent category
     *
     * @return the new category
     */
    @PostMapping("")
    public ResultEntity<List<CategoryDTO>> addCategory( @RequestBody CategoryDTO category)
    {
        return categoryService.addCategory(category);
    }

    /**
     * update a category according to the categoryid
     * @return
     */
    @PutMapping("")
    public ResultEntity<List<CategoryDTO>> updateCategory(
            @RequestBody CategoryDTO category)
    {
        return categoryService.updateCategory(category);
    }
}
