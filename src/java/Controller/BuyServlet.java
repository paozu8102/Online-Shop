/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import DAO.ProductDAO;
import Model.Cart;

import Model.Product;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 *
 * @author admin
 */
@WebServlet(name="BuyServlet", urlPatterns={"/buy"})
public class BuyServlet extends HttpServlet {
   
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
    String pid = request.getParameter("id");
        String quantity = "1";
        Cookie[] cart = request.getCookies();
        Cart t = new Cart();
        String txt = "";
        //Lấy giá trị cookie nếu có
        if (cart != null) {
            for (Cookie o : cart) {
                if (o.getName().equals("cart")) {
                    txt += o.getValue();
                    o.setMaxAge(0);
                    response.addCookie(o);
                }
            }
        }
        //Nối thêm giá trị mới
        ProductDAO dao = new ProductDAO();
        Product p = dao.getProductByID(pid);
        int numStore=p.getQuantity();
        if((t.getQuantityById(txt, pid)<numStore)){
               if (txt.isEmpty()) {
                txt = pid + ":" + quantity;
            } else {
                txt = txt + "/" + pid + ":" + quantity;
            }
        }

       //Xử lí phần tử trùng lặp và thêm quantity
        txt = t.processString(txt);
      
        //Add cookie
        Cookie c = new Cookie("cart", txt);
        c.setMaxAge(365 * 24 * 60 * 60);

        response.addCookie(c);
        response.sendRedirect("shop");
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
