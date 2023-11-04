/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.PostDAO;
import Model.Post;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
@WebServlet(name="PostManagerController", urlPatterns={"/managepost"})
public class PostManagerController extends HttpServlet {
   
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
            out.println("<title>Servlet PostManagerController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PostManagerController at " + request.getContextPath () + "</h1>");
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
        String searchID = request.getParameter("searchID");
        String index = request.getParameter("index");
        if(index == null) {
            index = "1";
        }
        if(searchID == null) {
            searchID = "";
        }
        
        String statusid = request.getParameter("statusid");
        String posttypeid = request.getParameter("posttypeid");
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String sort = request.getParameter("sort");
        
        String sql = "";
        String order = "";
        
        if(statusid != null && !statusid.isEmpty()) {
            sql += "and statusid = " + statusid + " ";
        }
        if(posttypeid != null && !posttypeid.isEmpty()) {
            sql += "and posttype = " + posttypeid + " ";
        }
        if(from != null && !from.isEmpty()) {
            sql += "and date between '" + from + "' and GETDATE()";
        }
        if(to != null && !to.isEmpty()) {
            if(sql.contains("GETDATE")) {
                sql = sql.replace("GETDATE()", "'" + to + "'");
            }
            else sql += "and date between '' and '" + to + "'";
        }
        if(sort != null && !sort.isEmpty()) {
            order += sort.equals("1") ? "[view] asc, " : " [view] desc, ";
        }
        
        int count = postDAO.getTotalPost(searchID, sql, order);
        double endPage = Math.ceil(count * 1.0 / 9);
        ArrayList<Post> postList = postDAO.getAllPost(searchID, index, sql, order);
        ArrayList<Integer> posttypeList = postDAO.getAllPostType();
        request.setAttribute("postList", postList);
        if(endPage > 1) request.setAttribute("endPage", endPage);
        else request.setAttribute("endPage", 0);
        request.setAttribute("statusid", statusid);
        request.setAttribute("posttypeid", posttypeid);
        request.setAttribute("from", from);
        request.setAttribute("to", to);
        request.setAttribute("sort", sort);
        request.setAttribute("index", index);
        request.setAttribute("searchID", searchID);
        request.setAttribute("posttypeList", posttypeList);
        request.getRequestDispatcher("post-management.jsp").forward(request, response);
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
