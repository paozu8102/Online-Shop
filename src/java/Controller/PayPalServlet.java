/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.OrderDAO;
import DAO.ProductDAO;
import DAO.accountDAO;
import Model.Account;
import Model.Cart;
import Model.Order;
import Model.OrderDetail;
import Model.PaymentServices;
import Model.ProOrder;
import Model.Product;
import Model.User;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;


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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name = "PayPalServlet", urlPatterns = {"/paypalcheckout"})
public class PayPalServlet extends HttpServlet {

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
     
        String address = request.getParameter("address");
        String phonenumber = request.getParameter("phone");      
        String totalPrice_raw = request.getParameter("total");
        String name = request.getParameter("customername");

        Cart t = new Cart();
        OrderDAO od = new OrderDAO();

        Cookie[] arr = request.getCookies();
        
         HttpSession session = request.getSession();
      
        User user = (User) session.getAttribute("user");
          LinkedHashMap<Product, Integer> pList = new LinkedHashMap<>();
        
        // Xóa các product đã mua trong cart
            
        if (arr != null) {
              String cart = "";
              String checkout = "";
            for (Cookie o : arr) {
                if (o.getName().equals("cart")) {
                    cart += o.getValue();

                    o.setMaxAge(0);
                    response.addCookie(o);

                }
                if (o.getName().equals("checkout")) {
                    checkout += o.getValue();
                    o.setMaxAge(0);
                    response.addCookie(o);
                }
                pList = t.getCart(checkout);
            }
            
            cart = t.removeDuplicateElements(cart, checkout);

            if (!cart.isEmpty()) {
                Cookie c = new Cookie("cart", cart);
                c.setMaxAge(365 * 24 * 60 * 60);

                response.addCookie(c);
            }

        }   double totalPrice = Double.parseDouble(totalPrice_raw);

        try{
          
           
            od.addOrder(user.getUserID(), totalPrice, name, address, phonenumber);  
         
            int o = od.getOrderIdLatest();
            
            for (Map.Entry<Product, Integer> entry : pList.entrySet()) {
                Product p = entry.getKey();
                int quantity = entry.getValue();
                double price = p.getPriceSale()*quantity;
                
               od.addOrderDetails(o,p.getProductID(),price,quantity);
                od.updateProductQuantity(p.getProductID(), quantity);
            }
             
             
        
        }catch (Exception e) {
    
    e.printStackTrace(); 
    
}
            

       response.sendRedirect("order-complete.jsp");

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
     processRequest(request, response);
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
