package lk.jiat.techmart.ejb;

import jakarta.ejb.Stateless; // <<<< මේක තමයි Magic Key එක
import lk.jiat.techmart.core.model.CartItem;
import lk.jiat.techmart.core.model.Order;
import lk.jiat.techmart.core.model.OrderItem;
import lk.jiat.techmart.core.service.OrderService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Stateless(name = "OrderServiceBean")
public class OrderServiceBean implements OrderService {
    private final List<Order> orderDb = Collections.synchronizedList(new ArrayList<>());
    @Override
    public Order placeOrder(List<CartItem> cart, String customerName, String customerEmail) {
        if (cart == null || cart.isEmpty()) {
            throw new IllegalArgumentException("Cart is empty");
        }
        Order order = new Order();
        order.setCustomerName(customerName);
        order.setCustomerEmail(customerEmail);
        BigDecimal total = BigDecimal.ZERO;

        for (CartItem cartItem : cart) {
            OrderItem orderItem = new OrderItem(
                    cartItem.getProduct().getId(),
                    cartItem.getProduct().getName(),
                    cartItem.getQuantity(),
                    cartItem.getProduct().getPrice()
            );
            order.addItem(orderItem);
            total = total.add(orderItem.getSubtotal());
        }
        order.setTotalAmount(total);
        orderDb.add(order);

        System.out.println(">> FAKE ORDER SAVED : ID =" + order.getId() + ", Total=RS " + order.getTotalAmount() + ", Customer=" + customerName);
        return order;
    }
    @Override
    public List<Order> findAll() {
        return new ArrayList<>(orderDb);
    }

    @Override
    public Order findById(Long id) {
        return orderDb.stream().filter(o -> o.getId().equals(id)).findFirst().orElse(null);
    }
}