/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.CommentDAO;
import DAO.OrderDAO;
import DAO.ProductDAO;
import DAO.accountDAO;
import Model.Comment;
import Model.Order;
import Model.Product;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author Acer
 */
@WebServlet(name = "Statistics", urlPatterns = {"/statistic"})
public class Statistics extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Statistics</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Statistics at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO c = new ProductDAO();
        accountDAO acAO = new accountDAO();
        OrderDAO orderDAO = new OrderDAO();
        CommentDAO cM = new CommentDAO();
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        ArrayList<String> listCountAccount = acAO.getTotalProductByStatus(u.getUserID());
        LinkedHashMap<String, String> listTotal = new LinkedHashMap<>();
        LinkedHashMap<String, String> listTotalRevenue = new LinkedHashMap<>();
        List<Order> list;
        List<Product> listP;
        List<Comment> listM;
         LinkedHashMap<String, Integer> listOrder = new LinkedHashMap<>();
       LocalDate currentDate = LocalDate.now();
        
        // Lấy tháng từ ngày hiện tại
        int currentMonth = currentDate.getMonthValue();
        for (int i = 4; i >= 0; i--) {
            listOrder.put("Month " + (currentMonth - i), orderDAO.getOrderbyMonth(currentMonth-i, u.getUserID()));
        }
        
        
        listM= cM.getAllCommentPrd();
        listP = c.getAllProductNoIndex(u.getUserID());
        
        int total = c.getTotalProductCountByUserId(u.getUserID());
        int totalorder = c.getTotalOrderProductCountByUserId(u.getUserID());
        int totalprice = c.getTotalPriceByUserId(u.getUserID());
        request.setAttribute("listM", listM);
       
        request.setAttribute("listCountAccount", listCountAccount);
        request.setAttribute("listCountAccount", listCountAccount);
        request.setAttribute("listTotal", listTotal);
        request.setAttribute("totalR", totalprice);
        request.setAttribute("totalO", totalorder);
        request.setAttribute("totalP", total);
        request.setAttribute("listP", listP);
        request.setAttribute("listTotalRevenue", listTotalRevenue);
        request.getRequestDispatcher("saler-dashboard.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
