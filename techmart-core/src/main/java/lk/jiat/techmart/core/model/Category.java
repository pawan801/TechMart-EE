package lk.jiat.techmart.core.model;

import java.time.LocalDateTime;

public class Category {

    private Long id;
    private String name;
    private String description;
    private LocalDateTime createAt;

    public Category(){}

    public Category(Long id, String name, String description, LocalDateTime createAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createAt = createAt;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public LocalDateTime getCreateAt() {
        return createAt;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }
}
