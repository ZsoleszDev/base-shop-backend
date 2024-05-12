package hu.thesis.baseshop.orders.dao;

import hu.thesis.baseshop.authorization.entity.User;
import hu.thesis.baseshop.orders.entity.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDao extends CrudRepository<Cart,Integer> {
    public List<Cart> findByUser(User user);

}
