package lk.jiat.techmart.core.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartItem implements Serializable {

    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal(){
        if(this.product == null || this.product.getPrice()==null){
            return BigDecimal.ZERO;
        }
        return this.product.getPrice().multiply(BigDecimal.valueOf(this.quantity));
    }

}
