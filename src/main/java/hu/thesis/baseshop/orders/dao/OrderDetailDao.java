package hu.thesis.baseshop.orders.dao;

import hu.thesis.baseshop.authorization.entity.User;
import hu.thesis.baseshop.orders.entity.OrderDetail;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderDetailDao extends CrudRepository<OrderDetail, Integer> {
    public List<OrderDetail> findByUser(User user);
}
