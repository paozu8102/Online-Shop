/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.DAO;
import DAO.UserDAO;
import Model.Account;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Acer
 */
@WebServlet(name="LoginController", urlPatterns={"/login"})
public class LoginController extends HttpServlet {
   
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
            out.println("<title>Servlet Login</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath () + "</h1>");
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
        response.sendRedirect("login.jsp");
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
        String e = request.getParameter("email");
        String p = request.getParameter("password");
        String r=request.getParameter("rem");
        DAO ad = new DAO();
        UserDAO ud= new UserDAO();
        Account c = ad.getAccount(e, p);
        int userID = ud.cusAccountExist(e, p);
        try {
            
                  if (c == null) {
            request.setAttribute("mess", "Wrong user name or password!");
            String er = "username: " + e + " and password: " + p + " don't exsited!";
            request.setAttribute("error", er);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }else if(c.getStatus()== 0){
                request.setAttribute("mess", "Your Account have been banned");
            String er = "Please contact us for get more infomation";
            request.setAttribute("error", er);
            request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
            HttpSession session = request.getSession(true);
            User user = (new UserDAO()).getUser(userID);
            session.setAttribute("user", user);
            session.setAttribute("acc", c);
            session.setMaxInactiveInterval(7200);
Cookie cookie = new Cookie("rem", r);
if (r == null || r.isEmpty()) {
    cookie.setMaxAge(0);
} else {
    cookie.setMaxAge(24 * 60 * 60);  // 1 day in seconds
}
response.addCookie(cookie);
if(c.getRoleID() == 2){
    response.sendRedirect("statistic");
}if(c.getRoleID() == 1){
    response.sendRedirect("admin");
}else{
    response.sendRedirect("home");
}
        }
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Exception", e);
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
    private boolean checkPass(String pass, String confpass) {
        if (pass.length() >= 6) {
            if (pass.equals(confpass)) {
                return true;
            }
        }
        return false;
    }
    private boolean checkUserName(String userName){
        if(userName.length() == 0){
            System.out.println("You must input email");
        }else{
            return true;
        }
     return false;
    }
}
