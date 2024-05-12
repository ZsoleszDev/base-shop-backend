package hu.thesis.baseshop.products.dao;

import hu.thesis.baseshop.products.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends CrudRepository<Product, Integer> {
}