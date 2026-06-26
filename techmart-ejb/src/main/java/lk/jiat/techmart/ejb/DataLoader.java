package lk.jiat.techmart.ejb;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.DependsOn;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import lk.jiat.techmart.core.model.Product;
import java.math.BigDecimal;

@Startup
//@Singleton
@DependsOn("ProductService")
public class DataLoader {

    @Inject
    private ProductService productService;

    @PostConstruct
    public void init() {
        if (productService.getAllProducts().isEmpty()) {
            Product p1 = new Product();
            p1.setName("Laptop");
            p1.setPrice(new BigDecimal("1200.00"));
            p1.setDescription("Gaming Laptop");
            p1.setImageUrl("laptop.jpg");
            productService.addProduct(p1);

            Product p2 = new Product();
            p2.setName("Mouse");
            p2.setPrice(new BigDecimal("25.00"));
            p2.setDescription("Wireless Mouse");
            p2.setImageUrl("mouse.jpg");
            productService.addProduct(p2);

            Product p3 = new Product();
            p3.setName("Keyboard");
            p3.setPrice(new BigDecimal("75.00"));
            p3.setDescription("Mechanical Keyboard");
            p3.setImageUrl("keyboard.jpg");
            productService.addProduct(p3);
        }
    }
}