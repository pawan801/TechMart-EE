package lk.jiat.techmart.ejb;

import jakarta.ejb.Stateless;
import lk.jiat.techmart.core.model.Product;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Stateless
public class ProductService {


    private static final List<Product> DB = new ArrayList<>();
    private static final AtomicLong ID_GEN = new AtomicLong(1);

    public ProductService() {
        if(DB.isEmpty()) {
            DB.add(new Product(ID_GEN.getAndIncrement(), "Laptop", "Gaming Laptop", new BigDecimal("1200.00"), "laptop.jpg", LocalDateTime.now(), 1L));
            DB.add(new Product(ID_GEN.getAndIncrement(), "Mouse", "Wireless Mouse", new BigDecimal("25.00"), "mouse.jpg", LocalDateTime.now(), 1L));
            DB.add(new Product(ID_GEN.getAndIncrement(), "Keyboard", "Mechanical Keyboard", new BigDecimal("75.00"), "keyboard.jpg", LocalDateTime.now(), 1L));
        }
    }

    public void addProduct(Product product) {
        product.setId(ID_GEN.getAndIncrement());
        if(product.getCreatedAt() == null){
            product.setCreatedAt(LocalDateTime.now());
        }
        DB.add(product);
    }

    public Product getProduct(Long id) {
        return DB.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Product> getAllProducts() {
        return DB;
    }

    public Product updateProduct(Product product) {
        for(int i=0; i<DB.size(); i++){
            if(DB.get(i).getId().equals(product.getId())){
                DB.set(i, product);
                return product;
            }
        }
        return null;
    }

    public void deleteProduct(Long id) {
        DB.removeIf(p -> p.getId().equals(id));
    }
}