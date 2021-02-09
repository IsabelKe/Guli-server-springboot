package ca.itsd.guli.controller;


import ca.itsd.guli.dto.RoleDTO;
import ca.itsd.guli.service.RoleService;
import ca.itsd.guli.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * RestController to handle all role related requests
 */
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping(value = "/api/v1/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * get all roles
     * @return a customized ResultEntity<List<RoleDTO>>
     */
    @GetMapping("")
    public ResultEntity<List<RoleDTO>> getAllRoles()
    {
        return roleService.findAllRoles();
    }

    /**
     *add a new role
     * @param role that will be added
     * @return a customized ResultEntity<List<RoleDTO>> if the adding is successful
     */
    @PostMapping("")
    public ResultEntity<List<RoleDTO>> addRole(@RequestBody RoleDTO role)
    {
        System.out.println(role.getRoleName()+"=================");
       return roleService.addRole(role.getRoleName());
    }

    /**
     * update a role
     * @param role that will be updated
     * @return a customized ResultEntity<List<RoleDTO>> if the adding is successful
     */
    @PutMapping("")
    public ResultEntity<List<RoleDTO>> updateRole(@RequestBody RoleDTO role)
    {
        return roleService.updateRole(role);
    }
}
