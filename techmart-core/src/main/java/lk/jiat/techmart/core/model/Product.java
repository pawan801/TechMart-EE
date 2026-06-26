package lk.jiat.techmart.core.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Column(name="NAME" , nullable = false, length = 100)
    private String name;

    @Column(name="DESCRIPTION" , length = 500)
    private String description;

    @Column(name = "PRICE" , nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name="IMAGE_URL", length = 500)
    private String imageUrl;

    @Column(name="CREATED_AT", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    private Long categoryId;

    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
    }
    public Product() {
    }
    public Product(Long id, String name, String description, BigDecimal price, String imageUrl, LocalDateTime createdAt, Long categoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.categoryId = categoryId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }




}
