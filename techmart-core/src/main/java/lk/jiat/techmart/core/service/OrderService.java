package lk.jiat.techmart.core.service;

import jakarta.ejb.Remote;
import lk.jiat.techmart.core.model.CartItem;
import lk.jiat.techmart.core.model.Order;

import java.util.List;

@Remote
public interface OrderService {

         Order placeOrder(List<CartItem> cart, String customerName, String customerEmail);

         List<Order> findAll();

         Order findById(Long id);
}
