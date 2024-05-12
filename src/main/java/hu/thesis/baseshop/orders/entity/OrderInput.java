package hu.thesis.baseshop.orders.entity;

import java.util.List;

public class OrderInput {

    private String fullName;
    private String fullAddress;
    private String contactNumber;
    private String orderShippingMethod;
    private String payment;
    private List<OrderProductQuantity> orderProductQuantitiesList;

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<OrderProductQuantity> getOrderProductQuantitiesList() {
        return orderProductQuantitiesList;
    }

    public void setOrderProductQuantitiesList(List<OrderProductQuantity> orderProductQuantitiesList) {
        this.orderProductQuantitiesList = orderProductQuantitiesList;
    }

    public String getOrderShippingMethod() {
        return orderShippingMethod;
    }

    public void setOrderShippingMethod(String orderShippingMethod) {
        this.orderShippingMethod = orderShippingMethod;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }
}
