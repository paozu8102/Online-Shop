/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.OrderDAO;
import DAO.ProductDAO;
import Model.ProOrder;
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
 * @author Acer
 */
@WebServlet(name="ManageOrder", urlPatterns={"/manageorder"})
public class ManageOrder extends HttpServlet {
   
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
            out.println("<title>Servlet ManageOrder</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageOrder at " + request.getContextPath () + "</h1>");
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
        OrderDAO o = new OrderDAO();
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
                
        List<ProOrder> list;
        String indexPage = request.getParameter("index");
                String from = request.getParameter("from");
        String to = request.getParameter("to");
        String sortprice = request.getParameter("sortprice");
        String txtSearch = request.getParameter("txt");
        int id  = u.getUserID();
        if (indexPage == null) {
            indexPage = "1";

        }

        int index = Integer.parseInt(indexPage);
        int count = o.getTotalOrderCountByUserId(id);
        int endPage = count / 6;
        if (count % 6 != 0) {
            endPage++;
        }
       if ("all".equals(sortprice)) {
            list = o.getAllOrder(index, id);
            request.setAttribute("endP", endPage);
            request.setAttribute("tag", index);
        }else if (txtSearch != null) {
          list = o.getAllOrderSearch(txtSearch, id);
        }else if ("asc".equals(sortprice)) {
            list = o.getAllOrderASC(id);
        }else if ("desc".equals(sortprice)) {
            list = o.getAllOrderDES(id);
        } else if(from != null && to != null){
            list = o.filterOrderbyDate(id, from, to);
             request.setAttribute("from", from);
              request.setAttribute("to", to);
        }
        else {

            list = o.getAllOrder(index, u.getUserID());
            request.setAttribute("endP", endPage);
            request.setAttribute("tag", index);
        }
        session.setAttribute("listO", list);
        request.getRequestDispatcher("order-management.jsp").forward(request, response);
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
        String oid = request.getParameter("oid");
    int id = 0; // Default value

    if (oid != null && !oid.isEmpty()) {
        try {
            id = Integer.parseInt(oid);
            OrderDAO c = new OrderDAO();
            c.CancelOrder(id);
        } catch (NumberFormatException e) {
            // Handle the case where the "oid" parameter is not a valid integer
            // You can log an error or provide a response indicating the invalid input.
        }
    }

    // Redirect to the appropriate page (whether cancellation succeeded or not).
    response.sendRedirect("myorder");
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
