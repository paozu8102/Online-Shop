/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.ProductDAO;
import Model.Account;
import Model.Cart;
import Model.Product;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

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
    HttpSession session = request.getSession();  
    Account acc = (Account) session.getAttribute("account");
 String pid = request.getParameter("pid");
        String quantity_raw = request.getParameter("num");
         ProductDAO dao = new ProductDAO();
        Product p = dao.getProductByID(pid);
    Cookie[] arr = request.getCookies();
    
    String txt = "";
    if (arr != null) {
        for (Cookie o : arr) {
            if (o.getName().equals("cart")) {
                txt += o.getValue();
            }
        }
    }

    Cart t = new Cart(); 
    
    
    try {

                int quantity = Integer.parseInt(quantity_raw);
            int numStore=p.getQuantity();
                if (quantity ==1 && t.getQuantityById(txt, pid)<numStore) {
                    txt = t.addQuantityToProduct(txt, pid, quantity);
                } else if(quantity == -1 && t.getQuantityById(txt,pid)>1){
                     txt = t.addQuantityToProduct(txt, pid, quantity);
                }else if(quantity == -1 && t.getQuantityById(txt,pid)<=1) {
                    txt = t.removeProductinCart(txt, pid);

                } else{
                  
                }
                
            } catch (Exception e) {
            }
    if (!txt.equals("")) {
            Cookie c = new Cookie("cart", txt);
            c.setMaxAge(365 * 24 * 60 * 60);

            response.addCookie(c);
        }
    
    
    LinkedHashMap<Product, Integer> cartlist = t.getCart(txt);
    request.setAttribute("cartlist", cartlist);

    // Pagination parameters
    int recordsPerPage = 10; // Number of records per page (you can adjust this)
    int tag = 1; // Default page number
    if (request.getParameter("index") != null) {
        tag = Integer.parseInt(request.getParameter("index"));
    }

    // Calculate the starting record index
    int startIndex = (tag - 1) * recordsPerPage;

    // Calculate the ending record index
    int endIndex = Math.min(startIndex + recordsPerPage, cartlist.size());

    // Create a sublist for the current page
    List<Map.Entry<Product, Integer>> cartlistPage = new ArrayList<>(cartlist.entrySet())
                                                        .subList(startIndex, endIndex);

    request.setAttribute("cartlistPage", cartlistPage);
    request.setAttribute("tag", tag);

    // Calculate the number of pages for pagination
    int endP = (int) Math.ceil((double) cartlist.size() / recordsPerPage);
    request.setAttribute("endP", endP);
    request.getRequestDispatcher("cart.jsp").forward(request, response);
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
        String pid = request.getParameter("pid");

        Cart t = new Cart();

        //Lấy cookie
        Cookie[] arr = request.getCookies();
        String txt = "";
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("cart")) {
                    txt += o.getValue();
                    //Xóa cookie
                    o.setMaxAge(0);
                    response.addCookie(o);
                }
            }
        }
       
            txt = t.removeProductinCart(txt, pid);

       
//Tao cookie mới
        if (!txt.equals("")) {
            Cookie c = new Cookie("cart", txt);
            c.setMaxAge(365 * 24 * 60 * 60);

            response.addCookie(c);
        }
         LinkedHashMap<Product, Integer> cartlist = t.getCart(txt);
    request.setAttribute("cartlist", cartlist);

    // Pagination parameters
    int recordsPerPage = 10; // Number of records per page (you can adjust this)
    int tag = 1; // Default page number
    if (request.getParameter("index") != null) {
        tag = Integer.parseInt(request.getParameter("index"));
    }

    // Calculate the starting record index
    int startIndex = (tag - 1) * recordsPerPage;

    // Calculate the ending record index
    int endIndex = Math.min(startIndex + recordsPerPage, cartlist.size());

    // Create a sublist for the current page
    List<Map.Entry<Product, Integer>> cartlistPage = new ArrayList<>(cartlist.entrySet())
                                                        .subList(startIndex, endIndex);

    request.setAttribute("cartlistPage", cartlistPage);
    request.setAttribute("tag", tag);

    // Calculate the number of pages for pagination
    int endP = (int) Math.ceil((double) cartlist.size() / recordsPerPage);
    request.setAttribute("endP", endP);
    request.getRequestDispatcher("cart.jsp").forward(request, response);
        
        
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
