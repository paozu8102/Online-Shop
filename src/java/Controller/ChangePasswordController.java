/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.accountDAO;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mai Vu Bao
 */
@WebServlet(name="ChangePasswordController", urlPatterns={"/ChangePassword"})
public class ChangePasswordController extends HttpServlet {
   
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
            out.println("<title>Servlet ChangePasswordController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePasswordController at " + request.getContextPath () + "</h1>");
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
        request.getRequestDispatcher("change-user-password.jsp").forward(request, response);
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
        String user = request.getParameter("email");
        String passOld = request.getParameter("pass");
        String pass = request.getParameter("newPassword");
        String repass = request.getParameter("confirmPassword");
        accountDAO adb = new accountDAO();
        Account account = null;
                HttpSession session = request.getSession(false);
        User user2 = (User) session.getAttribute("user");
        request.setAttribute("user", user2);

        // Check if user is logged in
        if (user2 == null) {
            request.setAttribute("mess", "User not logged in.");
            request.getRequestDispatcher("change-user-password.jsp").forward(request, response);
            return;
        }

        // Update user to use the email from the session
        user = user2.getEmail();

        try {
            account = adb.checkAccountExistByUserPass(user, passOld);
        } catch (Exception ex) {
            Logger.getLogger(ChangePasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (account == null) {
            request.setAttribute("mess", "No password input yet or wrong password!");
            request.getRequestDispatcher("change-user-password.jsp").forward(request, response);
            return;
        }

        if (pass.length() < 8) {
            request.setAttribute("mess", "New password must be at least 8 characters!");
            request.getRequestDispatcher("change-user-password.jsp").forward(request, response);
            return;
        }

        if (!pass.equals(repass)) {
            request.setAttribute("mess", "Password does not match!");
            request.getRequestDispatcher("change-user-password.jsp").forward(request, response);
            return;
        }

        // Passwords match, update the password
        try {
            adb.updatePassword(pass, user);
            request.setAttribute("passwordChanged", true);
            request.getRequestDispatcher("change-user-password.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ChangePasswordController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("mess", "An error occurred while updating the password.");
            request.getRequestDispatcher("change-user-password.jsp").forward(request, response);
        }
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
