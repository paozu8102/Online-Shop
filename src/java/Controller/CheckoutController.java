/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.OrderDAO;
import Model.Cart;
import Model.Product;
import Model.User;
import java.io.PrintWriter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;


import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "CheckoutController", urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {

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
            out.println("<title>Servlet CheckoutController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckoutController at " + request.getContextPath() + "</h1>");
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
        Cart t = new Cart();
       
        HttpSession session = request.getSession();
        String[] selectedItems = request.getParameterValues("selectedItems");
       
    User user = (User) session.getAttribute("user");
        

       if(user!=null){
           request.setAttribute("user", user);
      
        //Lay sản phẩm sẽ mua  
        String txt = "";
        LinkedHashMap<Product, Integer> pList = new LinkedHashMap<>();
      Cookie[] arr = request.getCookies();
            // Lấy các sản phẩm trong cart
            if (arr != null) {
                for (Cookie o : arr) {
                    if (o.getName().equals("cart")) {
                        txt += o.getValue();

                    }
                    // Xóa cookie check out
                    if (o.getName().equals("checkout")) {
                        o.setMaxAge(0);
                        response.addCookie(o);
                    }
                }
            }
            // Trường hợp chọn các ô checkbox
            if (selectedItems != null && selectedItems.length > 0) {
                txt = t.getQuantityBypid(txt, selectedItems);
            }

            pList = t.getCart(txt);

          
        
        //Tạo cookie checkout
        Cookie c = new Cookie("checkout", txt);
        c.setMaxAge(2 * 24 * 60 * 60);

        response.addCookie(c);

		String txt1 = "";
		if (arr != null) {
			for (Cookie o : arr) {
				if (o.getName().equals("cart")) {
					txt1 += o.getValue();

				}
			}
		}
		
		LinkedHashMap<Product, Integer> cartlist = t.getCart(txt);
		request.setAttribute("cartlist", cartlist);
        request.setAttribute("pList", pList);
       
        request.getRequestDispatcher("checkout.jsp").forward(request, response);
  }
      else{
          response.sendRedirect("login.jsp");
          
      }
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

        String address = request.getParameter("address");
        String phonenumber = request.getParameter("phone");      
        String totalPrice_raw = request.getParameter("total");
        String name = request.getParameter("customername");
  String payment = request.getParameter("payment");
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
if("cod".equals(payment)){
        try{
          
           
            od.addOrder(user.getUserID(), totalPrice, name, address, phonenumber, "COD");

         
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
}else {
      try{
          
           
                   od.addOrder(user.getUserID(), totalPrice, name, address, phonenumber, "Online");
         
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
}

       response.sendRedirect("order-complete.jsp");

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
