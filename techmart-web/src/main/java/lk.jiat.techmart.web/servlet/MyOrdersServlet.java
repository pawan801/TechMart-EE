package lk.jiat.techmart.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.techmart.core.model.Order;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/orders")
public class MyOrdersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Order> orders = getDummyOrders();
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("WEB-INF/Views/orders.jsp").forward(req,resp);
    }
    private List<Order> getDummyOrders(){

        List<Order> dummyList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        Order o1 = new Order();
        o1.setId(1L);
        o1.setCustomerName("Kamal Perera");
        o1.setCustomerEmail("kamal@gmail.com");
        o1.setTotalAmount(new BigDecimal(15500.00));
        o1.setOrderDate(formatter.format(LocalDateTime.now().minusDays(2)));

        Order o2 = new Order();
        o1.setId(2L);
        o1.setCustomerName("Nimali Silva");
        o1.setCustomerEmail("Nimali@gmail.com");
        o1.setTotalAmount(new BigDecimal(8200.000));
        o1.setOrderDate(formatter.format(LocalDateTime.now().minusDays(5)));
        dummyList.add(o2);

        return dummyList;

    }

}
