package hu.thesis.baseshop.orders.service;

import hu.thesis.baseshop.authorization.configuration.JwtRequestFilter;
import hu.thesis.baseshop.authorization.dao.UserDao;
import hu.thesis.baseshop.authorization.entity.User;
import hu.thesis.baseshop.orders.dao.CartDao;
import hu.thesis.baseshop.orders.entity.Cart;
import hu.thesis.baseshop.products.dao.ProductDao;
import hu.thesis.baseshop.products.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    public Cart addToCart(Integer productId) {
        Product product = productDao.findById(productId).get();
        String username = JwtRequestFilter.CURRENT_USER;
        User user = null;
        if (username != null){
            user = userDao.findById(username).get();
        }

        List<Cart> cartList = cartDao.findByUser(user);
        List<Cart> filteredList = cartList.stream().filter(x -> x.getProduct().getProductId() == productId).collect(Collectors.toList());

        if (filteredList.size() > 0) {
            return null;
        }
        if (product != null && user != null) {
            Cart cart = new Cart(product, user);
            return cartDao.save(cart);
        }
        return null;
    }

    public List<Cart> getCartDetails(){
        String username = JwtRequestFilter.CURRENT_USER;
        User user = userDao.findById(username).get();
        return cartDao.findByUser(user);
    }

    public void deleteCartItem(Integer cartId) {
        cartDao.deleteById(cartId);
    }

}
