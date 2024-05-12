package hu.thesis.baseshop.products.dao;

import hu.thesis.baseshop.authorization.entity.User;
import hu.thesis.baseshop.orders.entity.Cart;
import hu.thesis.baseshop.products.entity.Opinions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpinionsDao extends CrudRepository<Opinions, Integer> {
}
