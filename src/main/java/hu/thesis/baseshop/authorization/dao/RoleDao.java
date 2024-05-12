package hu.thesis.baseshop.authorization.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import hu.thesis.baseshop.authorization.entity.Role;

@Repository
public interface RoleDao extends CrudRepository<Role, String> {

}
