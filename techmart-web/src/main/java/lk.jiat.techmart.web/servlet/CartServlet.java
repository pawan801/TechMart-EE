package lk.jiat.techmart.web.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.jiat.techmart.core.model.CartItem;
import lk.jiat.techmart.core.model.Product;
import lk.jiat.techmart.ejb.ProductService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;



@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @EJB
    private ProductService productService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);

         String action = req.getParameter("action");
         HttpSession session = req.getSession(true);

          List<CartItem> cart =  (List<CartItem>) session.getAttribute("cart");

          if(cart == null){
              cart = new ArrayList<>();
              session.setAttribute("cart", cart);
          }

          if("add".equals(action)){
              addToCart(req, cart);
              resp.sendRedirect(req.getContextPath() + "/cart");
              return;
          }
          if("remove".equals(action)){
              removeFromCart(req, cart);
              resp.sendRedirect(req.getContextPath()+ "/cart");
              return;
          }

          req.setAttribute("cart", cart);
          req.setAttribute("cartTotal", calculateTotal(cart));

          req.getRequestDispatcher("/WEB-INF/Views/cart.jsp").forward(req, resp);

    }

    private void addToCart(HttpServletRequest req, List<CartItem> cart){
          Long id = Long.parseLong(req.getParameter("id"));
          Product p =productService.getProduct(id);
          if (p == null) return;

          for(CartItem item : cart){
              if(item.getProduct().getId().equals(id)){
                  item.setQuantity(item.getQuantity() + 1);
                  return ;
              }
          }
          cart.add(new CartItem(p, 1));
    }

    private void removeFromCart(HttpServletRequest req, List<CartItem>cart){
        Long id = Long.parseLong( req.getParameter("id"));
        cart.removeIf(item -> item.getProduct().getId().equals(id));
    }

    private BigDecimal calculateTotal(List<CartItem> cart){
        return cart.stream().map(CartItem::getSubtotal)
                .reduce(BigDecimal.ZERO, java.math.BigDecimal::add);
    }

}
