package ca.itsd.guli.repository;

import ca.itsd.guli.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * User repository that interacts with the database
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    /**
     * find a user by its login account
     * @param loginAcct login account of the user
     * @return an User object, or null if no user matches the loginAcct
     */
    @Query("from User where loginAccount=?1")
    User findUserByLogAccount(String loginAcct);
}
