package ca.itsd.guli.service;

import ca.itsd.guli.dto.RoleDTO;
import ca.itsd.guli.util.ResultEntity;

import java.util.List;

/**
 * this interface defines all the role related methods
 */
public interface RoleService {


    /**
     * get all roles
     * @return a customized  ResultEntity<List<RoleDTO>>
     */
    ResultEntity<List<RoleDTO>> findAllRoles();

    /**
     * update a role
     * @param roleDTO contains the new role information
     * @return  a customized  ResultEntity<List<RoleDTO>> if the updating is successful,
     * or an error message
     */
    ResultEntity<List<RoleDTO>> updateRole(RoleDTO roleDTO);

    /**
     * add a new role
     * @param roleName the new role name
     * @return a customized  ResultEntity<List<RoleDTO>> if the adding is successful,
     * or an error message
     */
    ResultEntity<List<RoleDTO>> addRole(String roleName);
}
