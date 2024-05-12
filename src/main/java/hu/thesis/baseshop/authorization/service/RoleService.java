package hu.thesis.baseshop.authorization.service;

import hu.thesis.baseshop.authorization.dao.RoleDao;
import hu.thesis.baseshop.authorization.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Role createNewRole(Role role) {
        return roleDao.save(role);
    }
}