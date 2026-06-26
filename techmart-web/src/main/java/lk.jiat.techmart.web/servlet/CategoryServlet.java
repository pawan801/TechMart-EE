package lk.jiat.techmart.web.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.jiat.techmart.ejb.CategoryService;

import java.io.IOException;

@WebServlet("/`categories")
public class CategoryServlet extends HttpServlet {

    @EJB
    private CategoryService categoryService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        req.setAttribute("categories", categoryService.getAllCategories());
        req.getRequestDispatcher("/WEB-INF/Views/categories.jsp").forward(req, resp);
    }
}
