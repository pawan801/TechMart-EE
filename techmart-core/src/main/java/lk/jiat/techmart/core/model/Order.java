package lk.jiat.techmart.core.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Order {

    private static final AtomicLong COUNTER = new AtomicLong(1);

    private Long id;
    private String customerName;
    private String customerEmail;
    private String orderDate;
    private BigDecimal totalAmount;
    private String status;
    private List<OrderItem> items;

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
    public String getOrderDate() {
        return orderDate;
    }
    public Order(){
        this.id = COUNTER.getAndIncrement();
        this.orderDate = orderDate;
        this.status = "PLACED";
        this.items = new ArrayList<>();
        this.totalAmount = BigDecimal.ZERO;
    }
   public void addItem(OrderItem item){
        this.items.add(item);
        this.totalAmount = this.totalAmount.add(item.getSubtotal());
   }
   public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getCustomerEmail() {
        return customerEmail;
    }
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public List<OrderItem> getItems() {
        return items;
    }

}
