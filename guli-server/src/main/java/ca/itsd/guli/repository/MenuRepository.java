package ca.itsd.guli.repository;

import ca.itsd.guli.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Menu repository that interacts with the database
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu,Integer>, JpaSpecificationExecutor<Menu> {

    /**
     * find a menu by its name
     * @param menuName name of the menu
     * @return a menu object or null if not menu matches the name
     */
    @Query(value = "from Menu where menuName=?1")
    Menu findMenuByName(String menuName);
}
