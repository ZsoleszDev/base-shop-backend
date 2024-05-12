package hu.thesis.baseshop.authorization.dao;

import hu.thesis.baseshop.authorization.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User, String> {
}
