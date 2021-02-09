package ca.itsd.guli.service.impl;

import ca.itsd.guli.domain.Menu;
import ca.itsd.guli.domain.Role;
import ca.itsd.guli.dto.RoleDTO;
import ca.itsd.guli.repository.MenuRepository;
import ca.itsd.guli.repository.RoleRepository;
import ca.itsd.guli.service.RoleService;
import ca.itsd.guli.util.ResultEntity;
import ca.itsd.guli.util.TimeUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 *this class handles all business logic for all role related requests
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private MenuRepository menuRepository;


    /**
     * get all roles
     * @return a customized  ResultEntity<List<RoleDTO>> that contains roles
     */
    @Override
    public ResultEntity<List<RoleDTO>> findAllRoles() {
        //call the repository method to find all roles
        List<Role> roles=roleRepository.findAll();
        //check if there roles returned
        if(roles==null || roles.isEmpty())
        {
            return ResultEntity.successWithoutData("No roles");
        }
        //roles exist, convert the list to dtos
        ModelMapper modelMapper=new ModelMapper();
        //Role,RoleDTO has to be in the same order as they are inside the modelMapper.map method
        PropertyMap<Role,RoleDTO> propertyMap=new PropertyMap<Role, RoleDTO>() {
            @Override
            protected void configure() {
                skip(source.getMenus(),destination.getMenus());
            }
        };
        modelMapper.addMappings(propertyMap);

      List<RoleDTO> roleDTOS=modelMapper.map(roles,new TypeToken<List<RoleDTO>>() {}.getType());
        for (int i = 0; i < roles.size(); i++) {
        List<String> menus=new ArrayList<>();
            for (Menu menu:roles.get(i).getMenus()
                 ) {
                menus.add(menu.getMenuName());
            }
           roleDTOS.get(i).setMenus(menus);
        }
        //return the data back to the client-side
        return ResultEntity.successWithData(roleDTOS);
    }

    /**
     * update a role
     * @param roleDTO contains the new role information
     * @return  a customized  ResultEntity<List<RoleDTO>> if the updating is successful,
     * or an error message
     */
    @Override
    public ResultEntity<List<RoleDTO>> updateRole(RoleDTO roleDTO) {
        //get the roleId
        Integer roleId=roleDTO.getRoleId();
        //find this role
        Role role=roleRepository.findById(roleId).get();
        //get the authName
        String authName=roleDTO.getAuthName();
        //generate the authorization time
        String currentTime=TimeUtil.getCurrentTime();
        role.setAuthName(authName);
        role.setAuthTime(currentTime);

        //get the new menus
        List<Menu> newMenus=new ArrayList<>();
        for(int i=0;i<roleDTO.getMenus().size();i++)
        {
            String name=roleDTO.getMenus().get(i);
            Menu menu=menuRepository.findMenuByName(name);
            newMenus.add(menu);
        }
        //connect this role with new menus
        role.setMenus(newMenus);
        try {
            //update this role
            roleRepository.save(role);
            return findAllRoles();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return ResultEntity.failed("Failed to update this role!");
        }

    }

    /**
     * add a new role
     * @param roleName the new role name
     * @return a customized  ResultEntity<List<RoleDTO>> if the adding is successful,
     * or an error message
     */
    @Override
    public ResultEntity<List<RoleDTO>> addRole(String roleName) {
        //valid the roleName
        if(roleName==null || roleName.isEmpty())
        {
            return ResultEntity.failed("Please enter a valid role name");
        }
        //get the current time
        String currentTime= TimeUtil.getCurrentTime();
        try {
            //add this new role
            roleRepository.addRole(roleName, currentTime);
            return findAllRoles();
        }
        catch (Exception e) {
            return ResultEntity.failed("Cannot add this role name");
        }
    }
}
