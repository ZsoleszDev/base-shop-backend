package hu.thesis.baseshop.products.service;

import hu.thesis.baseshop.authorization.configuration.JwtRequestFilter;
import hu.thesis.baseshop.authorization.dao.UserDao;
import hu.thesis.baseshop.authorization.entity.User;
import hu.thesis.baseshop.orders.dao.CartDao;
import hu.thesis.baseshop.orders.entity.Cart;
import hu.thesis.baseshop.products.dao.ProductDao;
import hu.thesis.baseshop.products.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CartDao cartDao;

    public Product addNewProduct(Product product) {
        return productDao.save(product);
    }

    public List<Product> getAllProducts(){
        return (List<Product>) productDao.findAll();
    }

    public Product getProductDetailsById(Integer productId) {
        return productDao.findById(productId).get();
    }

    public void deleteProductDetails(Integer productId) {
        productDao.deleteById(productId);
    }

    public List<Product> getProductDetails(boolean isSingleProductCheckout, Integer productId){
        if (isSingleProductCheckout && productId != 0){
            List<Product> list = new ArrayList<>();
            Product product = productDao.findById(productId).get();
            list.add(product);
            return list;
        }else{
            String username = JwtRequestFilter.CURRENT_USER;
            User user = userDao.findById(username).get();
            List<Cart> carts = cartDao.findByUser(user);
            return carts.stream().map(x -> x.getProduct()).collect(Collectors.toList());
        }
    }

}
