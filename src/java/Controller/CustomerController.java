/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Role;

import DAO.UserDAO;
import Model.Account;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author admin
 */
@WebServlet(name = "CustomerController", urlPatterns = {"/customercontrol"})
public class CustomerController extends HttpServlet {

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
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        User u = (User) session.getAttribute("user");
        int uid = u.getUserID();
        if (a.getRoleID() != 2) {
            return;
        }
        String indexPage = request.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";

        }
        int index = Integer.parseInt(indexPage);
        UserDAO c = new UserDAO();
        int count = c.getTotalCustomer(uid);
        int endPage = count / 9;
        if (count % 9 != 0) {
            endPage++;
        }

        String userstatus = request.getParameter("userstatus");
        String sortname = request.getParameter("sortname");
        String city = request.getParameter("city");
        
        List<User> listU = null;
int customerCount = c.getTotalCustomer(uid);

        if ("all".equals(userstatus)) {
            listU = c.getAllCustomer(uid, index);
            request.setAttribute("endP", endPage);
            request.setAttribute("tag", index);
        } else if ("active".equals(userstatus)) {
            listU = c.getAllCustomerActive(uid);
             customerCount = listU.size();
        } else if ("blocked".equals(userstatus)) {
            listU = c.getAllCustomerBlock(uid);
             customerCount = listU.size();
            
        } else if (sortname != null) {
            listU = c.getCustomerNameSort(uid,sortname);
             customerCount = listU.size();
        } else if (city != null) {
            city = city.replace("+", " ");
            listU = c.getAllCustomerCity(uid, city);
             customerCount = listU.size();
        } else {
            listU = c.getAllCustomer(uid, index);
            request.setAttribute("endP", endPage);
            request.setAttribute("tag", index);
        }
        request.setAttribute("listU", listU);
 request.setAttribute("total", customerCount);
        request.getRequestDispatcher("customer-management.jsp").forward(request, response);

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
        processRequest(request, response);
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
