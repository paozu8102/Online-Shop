/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.OrderDAO;
import DAO.UserDAO;
import Model.Account;
import Model.Cart;
import Model.Order;
import Model.ProOrder;
import Model.Product;
import Model.Setting;
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
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author admin
 */
@WebServlet(name="MyOrderServlet", urlPatterns={"/myorder"})
public class MyOrderServlet extends HttpServlet {
   
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
  HttpSession session = request.getSession();
  OrderDAO o = new OrderDAO();
        Account a = (Account) session.getAttribute("acc");
        User user = (User) session.getAttribute("user");
           String from = request.getParameter("from");
        String to = request.getParameter("to");
          String txtSearch = request.getParameter("txt");
        if (a.getRoleID() != 3) {
    return;
}
int id = user.getUserID();
        String indexPage = request.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";

        }
        List<ProOrder> list;
        int index = Integer.parseInt(indexPage);
        OrderDAO c = new OrderDAO();
        int count = c.getTotalMyOrder(user.getUserID());
        int endPage = count / 9;
        if (count % 9 != 0) {
            endPage++;
        }
         if (txtSearch != null) {
          list = o.getMyOrderSearch(txtSearch, id);
        } else if(from != null && to != null){
            list = o.filterMyOrderByDate(id, from, to);
             request.setAttribute("from", from);
              request.setAttribute("to", to);
        }
        else {

            list = c.getMyOrder(index, id );
            request.setAttribute("endP", endPage);
            request.setAttribute("tag", index);
        }

         	Cookie[] arr = request.getCookies();
		String txt = "";
		if (arr != null) {
			for (Cookie co : arr) {
				if (co.getName().equals("cart")) {
					txt += co.getValue();

				}
			}
		}
		Cart t = new Cart();
		LinkedHashMap<Product, Integer> cartlist = t.getCart(txt);
		request.setAttribute("cartlist", cartlist);
        request.setAttribute("listO", list);

        request.getRequestDispatcher("myorder.jsp").forward(request, response);

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
    String oid = request.getParameter("oid");
    int id = 0; // Default value
   
    // Your code here
        try {
            id = Integer.parseInt(oid);
            OrderDAO c = new OrderDAO();
            c.CancelOrder(id);
        } catch (NumberFormatException e) {
            // Handle the case where the "oid" parameter is not a valid integer
            // You can log an error or provide a response indicating the invalid input.
        
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
