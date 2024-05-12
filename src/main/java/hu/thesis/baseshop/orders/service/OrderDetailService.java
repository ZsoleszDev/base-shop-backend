package hu.thesis.baseshop.orders.service;

import hu.thesis.baseshop.authorization.configuration.JwtRequestFilter;
import hu.thesis.baseshop.authorization.dao.UserDao;
import hu.thesis.baseshop.authorization.entity.User;
import hu.thesis.baseshop.orders.dao.CartDao;
import hu.thesis.baseshop.orders.dao.OrderDetailDao;
import hu.thesis.baseshop.orders.entity.Cart;
import hu.thesis.baseshop.orders.entity.OrderDetail;
import hu.thesis.baseshop.orders.entity.OrderInput;
import hu.thesis.baseshop.orders.entity.OrderProductQuantity;
import hu.thesis.baseshop.products.dao.ProductDao;
import hu.thesis.baseshop.products.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailService {

    private static final String ORDER_PLACED = "Megrendelve";

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CartDao cartDao;

    public void placeOrder(OrderInput orderInput, boolean isSingleProductCheckout){
        List<OrderProductQuantity> productQuantityList = orderInput.getOrderProductQuantitiesList();
        for (OrderProductQuantity o: productQuantityList){

            Product product = productDao.findById(o.getProductId()).get();

            String currentUser = JwtRequestFilter.CURRENT_USER;
            User user = userDao.findById(currentUser).get();

            OrderDetail orderDetail = new OrderDetail(
                    orderInput.getFullName(),
                    orderInput.getFullAddress(),
                    orderInput.getContactNumber(),
                    orderInput.getOrderShippingMethod(),
                    ORDER_PLACED, orderInput.getPayment() ,o.getQuantity(),product.getProductDiscountedPrice() * o.getQuantity(),product,user
            );

            if (!isSingleProductCheckout){
                List<Cart> carts = cartDao.findByUser(user);
                carts.stream().forEach(x -> cartDao.deleteById(x.getCartId()));
            }

            orderDetailDao.save(orderDetail);
        }
    }

    public List<OrderDetail> getOrderDetails() {
        String currentUser = JwtRequestFilter.CURRENT_USER;
        User user = userDao.findById(currentUser).get();

        return orderDetailDao.findByUser(user);
    }

    public List<OrderDetail> getAllOrderDetails(){
        List<OrderDetail> orderDetails = new ArrayList<>();
        orderDetailDao.findAll().forEach(
                x -> orderDetails.add(x)
        );

        return orderDetails;
    }

    public void markOrderAsDelivered(Integer orderId) {
        OrderDetail orderDetail = orderDetailDao.findById(orderId).get();

        if (orderDetail != null){
            orderDetail.setOrderStatus("Kiszállítva");
            orderDetailDao.save(orderDetail);
        }

    }

}
