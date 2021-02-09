package ca.itsd.guli.repository;

import ca.itsd.guli.domain.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


/**
 * SubCategory repository that interacts with the database
 */
@Transactional
public interface SubCategoryRepository extends JpaSpecificationExecutor<SubCategory>, JpaRepository<SubCategory,Integer> {
    /**
     * find a sub-category by its name
     * @param categoryName name of the sub-category
     * @return a SubCategory or null if no sub-category matches
     */
    @Query(value = "from SubCategory where subCategoryName=?1")
    SubCategory findByCategoryName(String categoryName);

    /***
     * update a sub-category
     * @param parentId of this sub-category
     * @param id of the sub-category
     * @param categoryName name of the sub-category
     */
   @Modifying
   @Query(value="update sub_category  set sub_category_name=?3 where sub_id=?2 and parent_id=?1" ,
           nativeQuery = true)
    void updateSubCategory(Integer parentId, Integer id, String categoryName);
}
