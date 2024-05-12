package hu.thesis.baseshop.authorization.service;

import hu.thesis.baseshop.authorization.dao.RoleDao;
import hu.thesis.baseshop.authorization.dao.UserDao;
import hu.thesis.baseshop.authorization.entity.Role;
import hu.thesis.baseshop.authorization.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin jog");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Regisztrált felhasználó jog");
        roleDao.save(userRole);

        User adminUser = new User();
        adminUser.setUserName("zsoleszgepes@gmail.com");
        adminUser.setUserPassword(getEncodedPassword("Asder1234567"));
        adminUser.setUserFirstName("Zsolt");
        adminUser.setUserLastName("Olah");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);

        /* Teszt Admin User */
        User testAdminUser = new User();
        testAdminUser.setUserName("tesztadmin");
        testAdminUser.setUserPassword(getEncodedPassword("Tesztadmin2024"));
        testAdminUser.setUserFirstName("Admin");
        testAdminUser.setUserLastName("Teszt");
        Set<Role> testAdminRoles = new HashSet<>();
        testAdminRoles.add(adminRole);
        adminUser.setRole(testAdminRoles);
        userDao.save(testAdminUser);

        /* Teszt Regisztrált User */
        User user = new User();
        user.setUserName("tesztfelhasználó");
        user.setUserPassword(getEncodedPassword("Tesztfelhasználó2024"));
        user.setUserFirstName("Felhasználó");
        user.setUserLastName("Teszt");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setRole(userRoles);
        userDao.save(user);

    }

    public User registerNewUser(User user) {
        if (!Objects.equals(this.getEmailById(user.getUserName()), "létezik")){
            Role role = roleDao.findById("User").get();
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(role);
            user.setRole(userRoles);
            user.setUserPassword(getEncodedPassword(user.getUserPassword()));

            return userDao.save(user);
        }else{
            return user;
        }
    }

    public String getEmailById(String productId) {
        try {
            var user = userDao.findById(productId);
            var response = "nem létezik";
            if (!Objects.equals(user.toString(), "Optional.empty")){
                response = "létezik";
            }
            return response;
        }catch (EntityNotFoundException e){
            return "nem létezik error";
        }
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
