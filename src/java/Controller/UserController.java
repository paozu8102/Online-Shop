/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import Model.Role;

import DAO.UserDAO;
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
@WebServlet(name="UserController", urlPatterns={"/usercontrol"})
public class UserController extends HttpServlet {
   
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
        UserDAO c = new UserDAO();
        List<User> listU = c.getAllUser();
        request.setAttribute("listU", listU);
         List<Role> listR = c.getRole();
        request.setAttribute("listR", listR);
        HttpSession session = request.getSession();
        Boolean emailExists = (Boolean) session.getAttribute("emailExists");
       
        request.getRequestDispatcher("user-management.jsp").forward(request, response);
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
        processRequest(request, response);
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
         String userrole = request.getParameter("user-role");
String userstatus = request.getParameter("user-status");
UserDAO c = new UserDAO();
List<User> listU = null;

if ("all".equals(userrole) || "all".equals(userstatus)) {
    listU = c.getAllUser();
} else if ("Admin".equals(userrole)) {
    listU = c.getAllAdmin();
} else if ("Artist".equals(userrole)) {
    listU = c.getAllArtist();
} else if ("Customer".equals(userrole)) {
    listU = c.getAllCustomer();
} else if ("active".equals(userstatus)) {
    listU = c.getAllUserActive();
} else if ("blocked".equals(userstatus)) {
    listU = c.getAllUserBlocked();
}


request.setAttribute("listU", listU);
request.getRequestDispatcher("user-management.jsp").forward(request, response);
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
