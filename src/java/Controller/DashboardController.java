/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.CategoryDAO;
import DAO.CommentDAO;
import DAO.OrderDAO;
import DAO.PostDAO;
import DAO.accountDAO;
import Model.Category;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 *
 * @author Admin
 */
@WebServlet(name="DashboardController", urlPatterns={"/admin"})
public class DashboardController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DashboardController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DashboardController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        PostDAO postDAO = new PostDAO();
        CommentDAO commentDAO = new CommentDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        accountDAO acAO = new accountDAO();
        OrderDAO orderDAO = new OrderDAO();
        
        ArrayList<String> listCountAccount = acAO.getTotalAccountByStatus();
        
        ArrayList<String> listStatus = postDAO.getTotalPostByStatus();
        ArrayList<Category> listCategory = categoryDAO.getTopCategory();
        LinkedHashMap<String, String> listTotal = new LinkedHashMap<>();
        String filter = request.getParameter("filter");
        if(filter == null || filter.isEmpty() ||  filter.equals("1")) {
            filter = "1";
            listTotal = orderDAO.getTotalOrderByWeek();
        }
        else if(filter.equals("2")) {
            listTotal = orderDAO.getTotalOrderByMonth();
        } 
        else if (filter.equals("3")) {
            listTotal = orderDAO.getTotalOrderBy3Month();
        } 
        else if (filter.equals("4")) {
            listTotal = orderDAO.getTotalOrderBy6Month();
        } 
        else if (filter.equals("5")) {
            listTotal = orderDAO.getTotalOrderByYear();
        }
        
        
        
        request.setAttribute("listStatus", listStatus);
        request.setAttribute("listCategory", listCategory);
        request.setAttribute("listCountAccount", listCountAccount);
        request.setAttribute("listCountAccount", listCountAccount);
        request.setAttribute("listTotal", listTotal);
        request.setAttribute("filter", filter);
        
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
