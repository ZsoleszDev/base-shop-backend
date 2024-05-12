package hu.thesis.baseshop.products.entity;

import javax.persistence.*;

@Entity
@Table(name = "opinions_model")
public class Opinions {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Integer opinionId;
    private Integer productId;
    private String productName;
    private String opinion;
    private String userName;
    private String userUserName;
    private Integer value;

    public String getUserUserName() {
        return userUserName;
    }

    public void setUserUserName(String userUserName) {
        this.userUserName = userUserName;
    }

    public Integer getOpinionId() {
        return opinionId;
    }

    public void setOpinionId(Integer opinionId) {
        this.opinionId = opinionId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
