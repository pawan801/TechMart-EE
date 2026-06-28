package lk.jiat.techmart.web.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.jiat.techmart.core.model.CartItem;
import lk.jiat.techmart.core.model.Order;
import lk.jiat.techmart.core.service.OrderService;

import java.io.IOException;
import java.util.List;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

//    @EJB(lookup = "java:global/TechMart-EE/techmart-ejb/OrderServiceBean!lk.jiat.techmart.core.service.OrderService")
//    @EJB(mappedName = "OrderServiceBean")
//    private OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        HttpSession session = req.getSession();
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if(cart == null ||cart.isEmpty()){
            resp.sendRedirect(req.getContextPath() + "/cart");
            return;
        }

        req.getRequestDispatcher("/WEB-INF/Views/checkout.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        HttpSession session = req.getSession(false);
        if(session == null){
            resp.sendRedirect(req.getContextPath() + "/cart");
            return;
        }
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if(cart == null || cart.isEmpty()){
            req.setAttribute("error", "Your cart is empty");
            req.getRequestDispatcher("/cart.jsp").forward(req, resp);
            return;
        }
        String name = req.getParameter("customerName");
        String email = req.getParameter("customerEmail");

        if(name == null || name.isBlank() || email == null || email.isBlank()){
            req.setAttribute("error", "name and Email are required");
            req.getRequestDispatcher("/WEB-INF/Views/checkout").forward(req, resp);
            return;
        }

//        Order savedOrder = orderService.placeOrder(cart, name, email);
        session.removeAttribute("cart");
//        resp.sendRedirect(req.getContextPath() + "/order-success.jsp?orderId=" + savedOrder.getId());

    }

}
