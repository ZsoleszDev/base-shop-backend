package hu.thesis.baseshop.authorization.controller;

import hu.thesis.baseshop.authorization.entity.Role;
import hu.thesis.baseshop.authorization.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class RoleController {

    @Autowired
    private RoleService roleService;

    @CrossOrigin(origins = "https://www.baseshop.hu")
    @PostMapping({"/createNewRole"})
    public Role createNewRole(@RequestBody Role role) {
        return roleService.createNewRole(role);
    }

}
