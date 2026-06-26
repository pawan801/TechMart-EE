
package lk.jiat.techmart.web.servlet;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.techmart.core.model.Category;
import lk.jiat.techmart.ejb.CategoryService;
import lk.jiat.techmart.ejb.ProductService;
import lk.jiat.techmart.core.model.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    @EJB
    private ProductService productService;

    @EJB
    private CategoryService categoryService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        01

//               resp.setContentType("application/json");
//                resp.setCharacterEncoding("UTF-8");
//
//                resp.setHeader("Access-Control-Allow-Origin", "*");
//                resp.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
//                resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
//
//                try {
//                    List<Product> products = productService.getAllProducts();
//
//                    List<Map<String, Object>> productData = products.stream()
//                            .map(p -> {
//                                Map<String, Object> map = new HashMap<>();
//                                map.put("id", p.getId());
//                                map.put("name", p.getName());
//                                map.put("price", p.getPrice()); // BigDecimal directly යයි
//                                map.put("description", p.getDescription());
//                                map.put("image_url", p.getImageUrl()); // ඔයා ඉල්ලපු format එක
//                                map.put("created_at", p.getCreatedAt() != null ? p.getCreatedAt().toString() : null);
//                                return map;
//                            })
//                            .collect(Collectors.toList());
//
//                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
//                    String json = gson.toJson(productData);
//
//                    resp.getWriter().write(json);
//                    resp.setStatus(HttpServletResponse.SC_OK);
//
//                }catch(Exception e){
//                    resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//                    Map<String, String> error = new HashMap<>();
//                    error.put("error", "Database error :" + e.getMessage());
//                    resp.getWriter().write(new Gson().toJson(error));
//                    e.printStackTrace();
//                }

//        02

//                  String action  = req.getParameter("action");
//                  String idParam =  req.getParameter("id");
//
//                  if("delete".equals(action) && idParam != null){
//
//                      productService.deleteProduct(Long.parseLong(idParam));
//                      resp.sendRedirect("products");
//                      return;
//
//                  }
//
//                  if("edit".equals(action) && idParam != null){
//                      Product p = productService.getProduct(Long.parseLong(idParam));
//                      req.setAttribute("product", p);
//                      req.getRequestDispatcher("/WEB-INF/Views/Product-form.jsp");
//                      return;
//                  }
//
//                  if("new".equals(action)){
//                      req.setAttribute("categories", categoryService.getAllCategories());
//                      req.setAttribute("product", new Product());
//                      req.getRequestDispatcher("/WEB-INF/Views/Product-form.jsp").forward(req, resp);
//                      return;
//
//                  }
//
//                  req.setAttribute("products", productService.getAllProducts());
//                  req.getRequestDispatcher("/WEB-INF/Views/products.jsp").forward(req,resp);


        String action = req.getParameter("action");
        if("new".equals(action)){
            req.setAttribute("categories", categoryService.getAllCategories());
            req.setAttribute("product", new Product());
            req.getRequestDispatcher("/WEB-INF/Views/Product-form.jsp").forward(req, resp);
            return;
        }
        if("edit".equals(action)){
            Long id = Long.parseLong(req.getParameter("id"));
            req.setAttribute("product", productService.getProduct(id)); // getProduct -> getProductById
            req.setAttribute("categories", categoryService.getAllCategories());
            req.getRequestDispatcher("/WEB-INF/Views/Product-form.jsp").forward(req, resp);
            return;
        }
        if("delete".equals(action)){
            productService.deleteProduct(Long.parseLong(req.getParameter("id")));
            resp.sendRedirect(req.getContextPath() + "/products");
            return;
        }


        req.setAttribute("products", productService.getAllProducts());
        req.getRequestDispatcher("/WEB-INF/Views/products.jsp").forward(req, resp);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        01

//        String idStr = req.getParameter("id");
//        String name = req.getParameter("name");
//        String desc = req.getParameter("description");
//        String priceStr = req.getParameter("price");
//        String imageUrl = req.getParameter("imageUrl");
//
//        Product p = new Product();
//        p.setName(name);
//        p.setDescription(desc);
//        p.setPrice(new BigDecimal(priceStr));
//        p.setImageUrl(imageUrl);
//
//        if(idStr != null  && !idStr.isEmpty()){
//            p.setId(Long.parseLong(idStr));
//            productService.updateProduct(p);
//
//        }else {
//            productService.addProduct(p);
//        }
//
//        resp.sendRedirect("products");


//        02

//            req.setCharacterEncoding("UTF-8");
//            String idParam = req.getParameter("id");
//            String name = req.getParameter("name");
//            String description = req.getParameter("description");
//            BigDecimal price = new BigDecimal(req.getParameter("price"));
//            String imageUrl = req.getParameter("imageUrl");
//            Long categoryId = Long.parseLong(req.getParameter("categoryId"));
////
//            Product p;
//            if (idParam != null && !idParam.isEmpty()) {
//                p = productService.getProduct(Long.parseLong(idParam));
//                p.setName(name);
//                p.setDescription(description);
//                p.setPrice(price);
//                p.setImageUrl(imageUrl);
//                productService.updateProduct(p);
//                p.setCategoryId(categoryId);
//            } else {
//                p = new Product();
//                p.setName(name);
//                p.setDescription(description);
//                p.setPrice(price);
//                p.setImageUrl(imageUrl);
//                productService.addProduct(p);
//                p.setCategoryId(categoryId);
//            }
//            resp.sendRedirect("products");


        Long id = req.getParameter("id") == null || req.getParameter("id").isEmpty() ? null : Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String desc = req.getParameter("description");
        BigDecimal price = new BigDecimal(req.getParameter("price"));
        String img = req.getParameter("imageUrl");
        Long categoryId = Long.parseLong(req.getParameter("categoryId"));

        Product p = new Product(id, name, desc, price, img, LocalDateTime.now(), categoryId);

        if(id == null){
            productService.addProduct(p);
        } else {
            productService.updateProduct(p);
        }
        resp.sendRedirect(req.getContextPath() + "/products");

        }



}