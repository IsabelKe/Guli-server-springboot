package ca.itsd.guli.repository;

import ca.itsd.guli.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Role repository that interacts with the database
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Integer>, JpaSpecificationExecutor<Role> {

    /**
     * add a new role
     * @param roleName role name of the new role
     * @param time the role creating time
     */
    @Modifying
    @Query(value = "insert into roles(role_name,create_time) values(:roleName,:time)", nativeQuery = true)
    @Transactional
     void addRole(String roleName,String time);
}
