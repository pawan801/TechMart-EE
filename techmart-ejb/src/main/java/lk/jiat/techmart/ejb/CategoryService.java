package lk.jiat.techmart.ejb;

import jakarta.ejb.Stateless;
import lk.jiat.techmart.core.model.Category;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Stateless
public class CategoryService {

    private static final List<Category> DB = new ArrayList<>();
    private static final AtomicLong ID_GEN = new AtomicLong(1);

    public CategoryService(){
        if(DB.isEmpty()) {
            DB.add(new Category(ID_GEN.getAndIncrement(), "Laptops", "Gaming & Office Laptop", LocalDateTime.now()));
            DB.add(new Category(ID_GEN.getAndIncrement(), "Accessories", "Mouse, Keyboard, Headset", LocalDateTime.now()));


        }
    }

    public List<Category> getAllCategories(){
        return new ArrayList<>(DB);
    }

    public void addCategory(Category c){
        c.setId(ID_GEN.getAndIncrement());
        c.setCreateAt(LocalDateTime.now());
        DB.add(c);
    }

    public Category getCategoryById(Long id){
        return DB.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }

    public void deleteCategory(Long id){
        DB.removeIf(c -> c.getId().equals(id));
    }

//    public List<Category> getAllCategories(){return DB;}
//    public void addCategory(Category c){c.setId(ID_GEN.getAndIncrement()) ; c.setCreateAt(LocalDateTime.now());DB.add(c);}
}
