package ca.itsd.guli.repository;

import ca.itsd.guli.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Category repository that interacts with the database
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer>, JpaSpecificationExecutor<Category> {
    /**
     * find categories based on a parent id
     * @param parentId of this category. 0 means find all categories.
     *                 Otherwise, find all sub-categories for this parent id
     * @return a list of categories or sub-categories
     */
    @Query("from Category where parentId=?1")
     List<Category> findCategoryById(Integer parentId);

    /**
     * find a category by its name
     * @param categoryName category name of the category
     * @return a category or null if no category matches with the name
     */
    @Query("from Category where name=?1")
    Category findCategoryByName(String categoryName);

    /**
     * update a category
     * @param id of the category that will be updated
     * @param newName the new category name
     */
    @Modifying
    @Transactional
    @Query("update Category set name=?2 where id=?1")
    void updateCategory(Integer id,String newName);
}
